package com.cochef.mapgen.gen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.management.loading.MLet;

import com.cochef.mapgen.data.GPSLocation;
import com.cochef.mapgen.data.MapBusLine;
import com.cochef.mapgen.data.MapBusStop;
import com.cochef.mapgen.data.MapDataCollection;
import com.cochef.mapgen.data.MapIntersection;
import com.cochef.mapgen.data.MapRoadSection;
import com.cochef.mapgen.util.GeometryCalcu;

public class LoadBusLine {

	private static ArrayList<MapBusLine> mapBusLines = null;
	private static ArrayList<MapBusStop> mapBusStops = null;

	/**
	 * ��ȡ���е���·���󣬸���·�����е�xys��վ�㼯�ϸ�ֵ
	 * 
	 * @return the newIntersections
	 */
	public static ArrayList<MapBusLine> getMapBusLines() {
		if (mapBusLines == null) {
			mapBusLines = new ArrayList<MapBusLine>();
			mapBusStops = new ArrayList<MapBusStop>();
			InputStreamReader read;
			try {
				read = new InputStreamReader(new FileInputStream(new File(
						"busline.txt")), "UTF-8");
				BufferedReader input = new BufferedReader(read);
				StringTokenizer tokens;

				String msg = input.readLine();
				MapBusLine bs;
				MapBusStop busStop;

				while (msg != null) {
					msg = getLineName(msg);
					bs = new MapBusLine(msg, input.readLine());
					msg = input.readLine();

					tokens = new StringTokenizer(msg, ";");
					while (tokens.hasMoreTokens()) {

						String name = tokens.nextToken();
						String xys = tokens.nextToken();
						busStop = new MapBusStop(name, new GPSLocation(
								Double.parseDouble(xys.substring(0,
										xys.indexOf(","))),
								Double.parseDouble(xys.substring(xys
										.indexOf(",") + 1))));
						// ��վ�������ӵ����������У���֤վ������������
						if (!mapBusStops.contains(busStop)) {
							mapBusStops.add(busStop);
						}
						// ��ȡվ����ӳ��վ����ɵ�����
						MapBusStop[] nbs = new MapBusStop[2];
						nbs[0] = mapBusStops.get(mapBusStops.indexOf(busStop));
						nbs[1] = null;
						bs.addBusLocation(nbs);
					}
					initDirection(bs);
					mapBusLines.add(bs);
					msg = input.readLine();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mapBusLines;
	}

	private static void initDirection(MapBusLine mbl) {
		boolean notContained = true;
		for (MapBusLine blIt : mapBusLines) {
			if (blIt.getLineName().equals(mbl.getLineName())) {
				notContained = false;
				break;
			}
		}
		mbl.setDirection(notContained);
	}

	public static ArrayList<MapBusStop> getOrigStops() {
		return mapBusStops;
	}

	/**
	 * �Դ������·��ʼ����directionֵ����ȥ����·�����е���ʼ�����ֹ��
	 * 
	 * @param lines
	 *            Ҫ��ʼ��direction���޸�lineName����·����
	 */
	public static void initAllLineName(ArrayList<MapBusLine> lines) {
		for (int i = 0; i < lines.size(); i++) {
			MapBusLine lineI = lines.get(i);
			lineI.setLineName(getLineName(lineI.getLineName()));
		}
	}

	private static String getLineName(String lineName) {
		int indexEnd = lineName.indexOf("(");
		if (indexEnd >= 0) {
			lineName = lineName.substring(0, indexEnd);
		}
		return lineName;
	}

	/**
	 * �����·��β�ĳ���û����ӵ�·�Σ�����û��ӳ�䵽��·�ε���Ӧվ�����ӳ��
	 * 
	 * @param lines
	 *            Ҫ�������·�ļ���
	 * @param rdSecs
	 *            ����·�εļ���
	 */
	public static void addHeadRearSectionStops(ArrayList<MapBusLine> lines,
			ArrayList<MapRoadSection> rdSecs) {
		for (MapBusLine mbline : lines) {
			ArrayList<MapBusStop[]> stops = mbline.getBusStops();
			MapBusStop[] firstStop = stops.get(0);
			MapBusStop[] lastStop = stops.get(stops.size() - 1);
			if (firstStop[1] == null) { // û����Ӧ��ӳ�䣬˵��û�и�·��
				// TODO ���Ҹ���ӵ�·�Σ�Ȼ����ӵ���β������վ�����ӳ��
				addSecStop(mbline, 1, rdSecs);
			}
			if (lastStop[1] == null) {
				addSecStop(mbline, -1, rdSecs);
			}
		}
	}

	/**
	 * ���ݴ���Ĳ���������������Ӵ������·ȱ�ٵ�RoadSection����Ӧվ���ӳ��
	 * 
	 * @param mbline
	 *            Ҫ���·�κ�վ��ӳ�����·
	 * @param step
	 *            �����ķ���1������-1�Ƿ���
	 * @param rdSecs
	 *            ����·��������·�εļ���
	 */
	private static void addSecStop(MapBusLine mbline, int step,
			ArrayList<MapRoadSection> rdSecs) {
		ArrayList<MapBusStop[]> stops = mbline.getBusStops();
		MapBusStop[] firstStop = null;
		MapRoadSection firstSec = null;
		MapIntersection outsideEnd = null;
		if (step == 1) {
			firstStop = stops.get(0);
			firstSec = mbline.getRoadSections().get(0);
			outsideEnd = firstSec.getFirst();
		} else if (step == -1) {
			firstStop = stops.get(stops.size() - 1);
			firstSec = mbline.getRoadSections().get(
					mbline.getRoadSections().size() - 1);
			outsideEnd = firstSec.getSecond();
		} else {
			// TODO �׳��쳣����ʾ�����������
			System.out.println("�����������LoadBusLine.addSecStop");
			return;
		}
		double minDistSame = Double.MAX_VALUE, minDistOps = Double.MAX_VALUE;
		MapRoadSection closeSecSame = null, closeSecOps = null;

		for (MapRoadSection mrs : rdSecs) {
			boolean equ2ndEnd = mrs.getSecond().equals(outsideEnd);
			boolean equ1stEnd = mrs.getFirst().equals(outsideEnd);
			if (equ2ndEnd || equ1stEnd) {
				double dist = GeometryCalcu.distP2Sec(firstStop[0]
						.getLocation(), mrs.getFirst().getLocation(), mrs
						.getSecond().getLocation());
				if (dist < minDistSame) { // ͬ����·��
					if (step == 1 && equ2ndEnd || step == -1 && equ1stEnd) {
						minDistSame = dist;
						closeSecSame = mrs;
					}
				}
				if (dist < minDistOps) { // ������·��
					if (step == 1 && equ1stEnd || step == -1 && equ2ndEnd) {
						minDistOps = dist;
						closeSecOps = mrs;
					}
				}
			}
		}
		MapRoadSection newSection = null;
		MapIntersection intsec = null;
		if (minDistSame < 50) {
			newSection = closeSecSame;
			// System.out.println("�����·��");
			intsec = (step == 1) ? newSection.getFirst() : newSection
					.getSecond();
		} else if (minDistOps < 50) { // ����һ����·�Σ����ҵ���·�η���
			newSection = new MapRoadSection(closeSecOps.getSecond(),
					closeSecOps.getFirst());
			intsec = (step == 1) ? newSection.getFirst() : newSection
					.getSecond();
			// System.out.println("���������·��");
		} else { // �˵�Ϊ��վ�������е�һ·�εĵ�һ�˵�
			// ���һ���µ�Intersection
			// �޷��ҵ���intersection���ڵ�·�����֣���Ϊnull
			intsec = new MapIntersection(firstStop[0].getLocation(), null);
//			System.out
//					.println("�޷��ҵ���intersection���ڵ�·�����֣���Ϊnull��֮���ֶ��������LoadBusLine��");

			// ��DataCollection������µ�intersection
			if (!MapDataCollection.getItscts().contains(intsec)) {
				MapDataCollection.getItscts().add(intsec); // ����µ�intersection
			} else {
				intsec = MapDataCollection.getItscts().get(
						MapDataCollection.getItscts().indexOf(intsec));
			}

			newSection = (step == 1) ? new MapRoadSection(intsec,
					firstSec.getFirst()) : new MapRoadSection(
					firstSec.getSecond(), intsec);
		}

		// ��datacollection����Ӹ���·��
		if (!MapDataCollection.getRdSections().contains(newSection)) {
			MapDataCollection.getRdSections().add(newSection);
		}

		if (step == 1) {
			if (!mbline.getItscts().contains(intsec)) {
				mbline.getItscts().add(0, intsec);
			}
			mbline.getRoadSections().add(0, newSection);
		} else {
			if (!mbline.getItscts().contains(intsec)) {
				mbline.getItscts().add(intsec);
			}
			mbline.addRoadSection(newSection);
		}
		// ����ʼ�����ɸ�δӳ���վ��ӳ�䵽��·��
		int pos = (step == 1) ? 0 : stops.size() - 1;
		MapRoadSection preSection = (step == 1) ? null : mbline
				.getRoadSections().get(mbline.getRoadSections().size() - 2);
		MapRoadSection nextSection = (step == 1) ? mbline.getRoadSections()
				.get(1) : null;
		for (int i = pos; stops.get(i)[1] == null; i += step) {
			SecBusStopGen.addMappingStop(stops, i, newSection, preSection,
					nextSection, mbline);
		}
	}
}
