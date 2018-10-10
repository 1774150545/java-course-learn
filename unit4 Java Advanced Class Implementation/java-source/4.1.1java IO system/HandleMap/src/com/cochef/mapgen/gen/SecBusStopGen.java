package com.cochef.mapgen.gen;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.cochef.mapgen.data.GPSLocation;
import com.cochef.mapgen.data.MapBusLine;
import com.cochef.mapgen.data.MapBusStop;
import com.cochef.mapgen.data.MapIntersection;
import com.cochef.mapgen.data.MapRoadSection;
import com.cochef.mapgen.util.GeometryCalcu;

/**
 * @author mcy
 * 
 */
public class SecBusStopGen {

	private static ArrayList<MapBusStop> secBusStops = null;

	/**
	 * �õ����е�ӳ��վ�㼯��
	 * 
	 * @param lines
	 *            ���е���·�ļ���
	 * @return ���е�ӳ��վ�㼯��
	 */
	public static ArrayList<MapBusStop> getSecStops(ArrayList<MapBusLine> lines) {
		if (secBusStops == null) {
			secBusStops = new ArrayList<MapBusStop>();
			for (MapBusLine line : lines) { // ȡ��һ����·���д���
				ArrayList<MapBusStop[]> stops = line.getBusStops();
				ArrayList<MapIntersection> itscts = line.getItscts();
				int stopsPos = 0;
				int itsctPos = 0;
				Double[] intXys = line.getLineItsctXYS(itscts.get(0));
				GPSLocation curLocation = new GPSLocation(intXys[0], intXys[1]);
				MapRoadSection preRoadSection = null;
				MapRoadSection curRoadSection = null;
				MapRoadSection nextRoadSection = line.getRoadSections().get(0);
				int rdSecSize = line.getRoadSections().size();
				StringTokenizer tokens = new StringTokenizer(line.getXys(), ";");

				// �ҵ���һ��intersection��xys�ж�Ӧ��λ�ã������λ�ú����վ��
				boolean intsecFound = false;
				boolean hasMoreIntsec = true;
				while (tokens.hasMoreElements()) {
					GPSLocation tempLocation = GPSLocation
							.parseGpsLocation((String) tokens.nextElement());

					// ��ǰ�����͵�ǰ·��ָ�����
					if (curLocation.equals(tempLocation)) {
						itsctPos++;
						if (itsctPos < itscts.size()) {
							intXys = line.getLineItsctXYS(itscts.get(itsctPos));
							curLocation = new GPSLocation(intXys[0], intXys[1]);
							preRoadSection = curRoadSection;
							curRoadSection = nextRoadSection;
							nextRoadSection = itsctPos < rdSecSize ? line
									.getRoadSections().get(itsctPos) : null;
							intsecFound = true;
						} else {
							hasMoreIntsec = false;
						}
					}
					if (stops.get(stopsPos)[0].getLocation().equals(
							tempLocation)) {
						if (intsecFound
								&& stops.get(stopsPos)[0].getLocation().equals(
										tempLocation)) {
							addMappingStop(stops, stopsPos, curRoadSection,
									preRoadSection, nextRoadSection, line);
						}
						stopsPos++;
					}
					if (!hasMoreIntsec) {
						break;
					}
				}
			}
		}

		return secBusStops;
	}

	/**
	 * �Դ�����¾�վ�㼯���е�ָ��Ԫ�����վ��ӳ�䣬�����ó�վ��·�ε����ӹ�ϵ
	 * 
	 * @param stops
	 *            �¾�վ�㼯��
	 * @param stopsPos
	 *            ָ����Ҫ���ӳ��վ��ֵ��վ��Ԫ����stops�е�λ��
	 * @param curRoadSection
	 *            Ҫ���ӳ��վ��Ķ������ڵ�·��
	 * @param line
	 *            ��������Ӧ����·���������������Ϣ
	 */
	public static void addMappingStop(ArrayList<MapBusStop[]> stops,
			int stopsPos, MapRoadSection curRoadSection,
			MapRoadSection preRoadSection, MapRoadSection nextRoadSection,
			MapBusLine line) {
		ArrayList<MapBusStop> secStops = curRoadSection.getSecBusStops();
		MapBusStop[] stopPare = stops.get(stopsPos);
		boolean notFound = true;
		for (MapBusStop mapBusStop : secStops) {
			if (mapBusStop.getStopName().equals(stopPare[0].getStopName())) {
				stopPare[1] = mapBusStop;
				stopPare[0].setRoadSection(stopPare[1].getRoadSection());
				notFound = false;
				break;
			}
		}
		if (notFound) { // �ڵ�ǰ·����û�и�վ���ӳ����
			MapRoadSection sectionStopIn = curRoadSection;
			MapBusStop tempStop = getMappingStop(stopPare[0], curRoadSection
					.getFirst().getLocation(), curRoadSection.getSecond()
					.getLocation());
			if (tempStop == null && preRoadSection != null) { // �޷�ӳ�䵽��ǰ·�Σ�����ӳ�䵽ǰһ·��
				sectionStopIn = preRoadSection;
				tempStop = getMappingStop(stopPare[0], preRoadSection
						.getFirst().getLocation(), preRoadSection.getSecond()
						.getLocation());
			}
			if (tempStop == null && nextRoadSection != null) { // �޷�ӳ�䵽ǰһ·�Σ�����ӳ�䵽��һ·��
				sectionStopIn = nextRoadSection;
				tempStop = getMappingStop(stopPare[0], nextRoadSection
						.getFirst().getLocation(), nextRoadSection.getSecond()
						.getLocation());
			}
			if (tempStop == null) { // �޷�ӳ�䵽��ǰ��ǰ���������Σ���ӳ�䵽��ǰ·�ε�����Ķ˵�
				sectionStopIn = curRoadSection;
				GPSLocation stopLoc = stopPare[0].getLocation();
				if (stopLoc.realDistance(curRoadSection.getFirst()
						.getLocation()) > stopLoc.realDistance(curRoadSection
						.getSecond().getLocation())) {
					tempStop = new MapBusStop(stopPare[0].getStopName(),
							curRoadSection.getSecond().getLocation());
				} else {
					tempStop = new MapBusStop(stopPare[0].getStopName(),
							curRoadSection.getFirst().getLocation());
				}
			}

			// ���ý���վӳ�����·����
			notFound = true;
			secStops = sectionStopIn.getSecBusStops();
			for (MapBusStop mapBusStop : secStops) {
				if (mapBusStop.getStopName().equals(stopPare[0].getStopName())) {
					notFound = false;
					tempStop = mapBusStop;
					break;
				}
			}
			if (notFound) {
				sectionStopIn.addBusStop(tempStop);
			}
			stopPare[1] = tempStop;

			// ��վ��·�ι���
			stopPare[0].setRoadSection(sectionStopIn);
			stopPare[1].setRoadSection(sectionStopIn);

			if (!secBusStops.contains(tempStop)) {
				secBusStops.add(tempStop);
			}
		}
	}

	/**
	 * ���վ��stop���߶��ϵ�ӳ��վ��
	 * 
	 * @param stop
	 *            Ҫӳ���վ��
	 * @param first
	 *            ȷ���߶εĵ�һ����
	 * @param second
	 *            ȷ���߶εĵڶ�����
	 * @return վ��stop���߶��ϵ�ӳ��վ��
	 */
	private static MapBusStop getMappingStop(MapBusStop stop,
			GPSLocation first, GPSLocation second) {
		MapBusStop tempStop = null;
		Double[] mapping = GeometryCalcu.getSegmentMapPoint(stop.getLocation(),
				first, second);
		if (mapping != null) {
			GPSLocation newLoc = new GPSLocation(mapping[0], mapping[1]);
			tempStop = new MapBusStop(stop.getStopName(), newLoc);
		}

		return tempStop;
	}

	public static void main(String[] args) {
		MapBusStop testStop = getMappingStop(new MapBusStop(null,
				new GPSLocation(108.922634, 34.227066)), new GPSLocation(
				108.945751, 34.198053), new GPSLocation(108.946604, 34.200143));
		System.out.println(testStop);
	}
}
