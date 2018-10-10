package com.cochef.mapgen.gen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import com.cochef.mapgen.data.GPSLocation;
import com.cochef.mapgen.data.MapBusLine;
import com.cochef.mapgen.data.MapIntersection;
import com.cochef.mapgen.data.MapRoadSection;

/**
 * @author mcy
 * 
 */
public class RoadSectionGen {

	private static ArrayList<MapRoadSection> newRSs = null;

	/**
	 * �Ӵ����·�ڼ��Ϻ���·���ϵõ�·�μ����б�������·�����и�����·�����·�ڼ��Ϻ�·�μ��Ͻ��г�ʼ������
	 * 
	 * @param newIntsecs
	 * @param mapBusLines
	 * @return �õ�·�μ����б�
	 */
	public static ArrayList<MapRoadSection> getMapRoadSections(
			ArrayList<MapIntersection> newIntsecs,
			ArrayList<MapBusLine> mapBusLines) {
		if (newRSs == null) {
			newRSs = new ArrayList<MapRoadSection>();
			for (MapBusLine line : mapBusLines) {
				// �ȶԱ�xys�и������newIntsecs�и���·�ڵľ��룬���С����ֵ������Ϊ��·���ڸ���·��
				// LinkedHashMap�����ݴ�վ������
				LinkedHashMap<MapIntersection, Double> itsctsMap = new LinkedHashMap<MapIntersection, Double>();
				StringTokenizer tokens = new StringTokenizer(line.getXys(), ";");
				while (tokens.hasMoreElements()) {
					String xyToken = (String) tokens.nextElement();
					GPSLocation tempGPS = GPSLocation.parseGpsLocation(xyToken);
					// ������NewIntersection�Ⱦ���
					for (MapIntersection intSec : newIntsecs) {
						double dist = tempGPS
								.realDistance(intSec.getLocation());
						if (dist < 80) { // ��ʱ��80Ϊ����
							if (itsctsMap.containsKey(intSec)) {
								// �Ѱ�����·��
								// ����ǰ�ľ����֮ǰ����ľ���С��ɾ��֮ǰ�ģ�����ǰֵ�������
								if (dist < itsctsMap.get(intSec)) {
									itsctsMap.remove(intSec);
									if (!isTooClose(itsctsMap, intSec, line)) {
										itsctsMap.put(intSec, dist);
									}
								}
							} else {
								if (!isTooClose(itsctsMap, intSec, line)) {
									itsctsMap.put(intSec, dist);
								}
							}
						}
					}
				}

				Iterator<Entry<MapIntersection, Double>> iter = itsctsMap
						.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<MapIntersection, Double> entry = (Map.Entry<MapIntersection, Double>) iter
							.next();
					line.addItsct(entry.getKey());
				}

				// ����·�õ���·���б�������õ�·���б�������Ӧ��List��
				ArrayList<MapIntersection> lineIntsecs = line.getItscts();
				MapIntersection preIntsec = lineIntsecs.get(0);
				MapIntersection curIntsec = null;
				for (int i = 1; i < lineIntsecs.size(); i++) {
					curIntsec = lineIntsecs.get(i);
					MapRoadSection tempNewRoadSection = new MapRoadSection(
							preIntsec, curIntsec);
					// �ȴ���newRSs���ٴ�newRSs�еõ���������Ӧ��line�У���֤����line��NewRoadSection�����һ��
					addMapRS(tempNewRoadSection);
					int tempIndex = newRSs.indexOf(tempNewRoadSection);
					line.addRoadSection(newRSs.get(tempIndex));
					preIntsec = curIntsec;
				}
			}
		}

		return newRSs;
	}

	private static boolean isTooClose(
			LinkedHashMap<MapIntersection, Double> itsctsMap,
			MapIntersection intSec, MapBusLine line) {
		boolean isTooClose = false;
		GPSLocation gps = new GPSLocation(line.getXYSWithItsct(intSec));
		Iterator<Entry<MapIntersection, Double>> iter = itsctsMap.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<MapIntersection, Double> entry = (Map.Entry<MapIntersection, Double>) iter
					.next();
			GPSLocation temp = new GPSLocation(line.getXYSWithItsct(entry
					.getKey()));
			if (gps.equals(temp)) {
				isTooClose = true;
				break;
			}
		}
		return isTooClose;
	}

	/**
	 * ���ظ������·��
	 * 
	 * @param nrs
	 */
	private static void addMapRS(MapRoadSection nrs) {
		if (!newRSs.contains(nrs)) {
			newRSs.add(nrs);
		}
	}

	public static void initAllStreetNames(ArrayList<MapRoadSection> mpSecs) {
		for (MapRoadSection mapRoadSection : mpSecs) {
			mapRoadSection.initStreetName();
		}
	}

	/**
	 * ��ʼ������·�εĳ���
	 * 
	 * @param lines
	 *            ������·�ļ���
	 */
	public static void initAllRoadLength(ArrayList<MapBusLine> lines) {
		// ��ʼ�����в�����·�����˵㴦��·��
		for (MapBusLine mbl : lines) {
			for (MapRoadSection mrs : mbl.getRoadSections()) {
				if (mrs.getLength() > 0) {
					continue;
				} else if (!mrs.getFirst().equals(mbl.getItscts().get(0))
						&& !mrs.getSecond()
								.equals(mbl.getItscts().get(
										mbl.getItscts().size() - 1))) {
					mrs.setLength(getRoadSecLen(mrs, mbl, true));
				}
			}
		}
		// ��ʼ����������·�˵㴦��·��
		for (MapBusLine mbl : lines) {
			for (MapRoadSection mrs : mbl.getRoadSections()) {
				if (mrs.getLength() > 0) {
					continue;
				} else if (mrs.getFirst().equals(mbl.getItscts().get(0))
						|| mrs.getSecond()
								.equals(mbl.getItscts().get(
										mbl.getItscts().size() - 1))) {
					mrs.setLength(getRoadSecLen(mrs, mbl, false));
				}
			}
		}
	}

	/**
	 * �õ�·�εĳ���
	 * 
	 * @param roadSec
	 *            ·��
	 * @param theLine
	 *            ·��������·
	 * @param isInside
	 *            ��·���Ƿ�������·���ڲ���true����·���ڲ���false����·�ζ˵㴦
	 * @return ·�εĳ���
	 */
	public static double getRoadSecLen(MapRoadSection roadSec,
			MapBusLine theLine, boolean isInside) {
		GPSLocation firstLoc = null;
		GPSLocation secondLoc = null;
		Double[] firstXY = null;
		Double[] secondXY = null;
		if (isInside) {
			firstXY = theLine.getLineItsctXYS(roadSec.getFirst());
			secondXY = theLine.getLineItsctXYS(roadSec.getSecond());
		} else {
			String[] xysLoc = theLine.getXys().split(";");
			if (roadSec.getFirst().equals(theLine.getItscts().get(0))) { // ��·�ο�ʼ�˵㴦
				String[] xyStr = xysLoc[0].split(",");
				firstXY = new Double[2];
				firstXY[0] = Double.parseDouble(xyStr[0]);
				firstXY[1] = Double.parseDouble(xyStr[1]);
				secondXY = theLine.getLineItsctXYS(roadSec.getSecond());
			} else if (roadSec.getSecond().equals(
					theLine.getItscts().get(theLine.getItscts().size() - 1))) { // ��·�ν�β�˵㴦
				firstXY = theLine.getLineItsctXYS(roadSec.getFirst());
				String[] xyStr = xysLoc[xysLoc.length - 1].split(",");
				secondXY = new Double[2];
				secondXY[0] = Double.parseDouble(xyStr[0]);
				secondXY[1] = Double.parseDouble(xyStr[1]);
			}
		}

		firstLoc = new GPSLocation(firstXY[0], firstXY[1]);
		secondLoc = new GPSLocation(secondXY[0], secondXY[1]);

		return theLine.getLength(firstLoc, secondLoc);
	}
}
