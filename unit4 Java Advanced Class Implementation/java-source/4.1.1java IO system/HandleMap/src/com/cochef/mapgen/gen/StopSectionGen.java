package com.cochef.mapgen.gen;

import java.util.ArrayList;

import com.cochef.mapgen.data.MapBusLine;
import com.cochef.mapgen.data.MapBusStop;
import com.cochef.mapgen.data.MapStopSection;

/**
 * @author mcy
 * 
 */
public class StopSectionGen {

	private static ArrayList<MapStopSection> stopSections;

	/**
	 * ��ȡվ��μ���,վ�����ֱ����ͨ��ԭʼվ������
	 * 
	 * @param lines
	 *            ������·�ļ���
	 * @return վ��μ���
	 */
	public static ArrayList<MapStopSection> getMapStopSections(
			ArrayList<MapBusLine> lines) {
		if (stopSections == null) {
			stopSections = new ArrayList<MapStopSection>();
			for (MapBusLine mapBusLine : lines) {
				ArrayList<MapBusStop[]> stopPares = mapBusLine.getBusStops();
				MapBusStop[] preStopPare = stopPares.get(0);
				MapBusStop[] curStopPare = null;
				int stopSize = stopPares.size();
				for (int i = 1; i < stopSize; i++) {
					curStopPare = stopPares.get(i);
					double secLength = getStopSecLen(preStopPare[0],
							curStopPare[0], mapBusLine);
					MapStopSection newMapStopSection = new MapStopSection(
							preStopPare[0], stopPares.get(i)[0], mapBusLine,
							secLength);
					newMapStopSection.setLine(mapBusLine);
					mapBusLine.addStopSection(newMapStopSection);
					stopSections.add(newMapStopSection);
					preStopPare = curStopPare;
				}
			}
		}

		return stopSections;
	}

	/**
	 * �õ�����վ��֮��ľ���
	 * 
	 * @param first
	 *            ��һ��վ��
	 * @param second
	 *            �ڶ���վ��
	 * @param theLine
	 *            վ�����ڵ���·
	 * @return ����վ��֮��ľ���
	 */
	private static double getStopSecLen(MapBusStop first, MapBusStop second,
			MapBusLine theLine) {
		return theLine.getLength(first.getLocation(), second.getLocation());
	}

}
