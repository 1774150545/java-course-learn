package com.cochef.mapgen.data;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author zhengzhen
 * 
 */
public class MapBusLine {

	private String lineName;
	private boolean direction;
	private String xys;
	private ArrayList<MapBusStop[]> busStops; // MapBusStop[0]Ϊԭֵ��MapBusStop[1]Ϊ��ֵ
	private ArrayList<MapRoadSection> roadSections;
	private ArrayList<MapIntersection> itscts;
	private ArrayList<MapStopSection> stopSections;

	public MapBusLine(String initLineName, String initXYS) {
		lineName = initLineName;
		xys = initXYS;
		busStops = new ArrayList<MapBusStop[]>();
		roadSections = new ArrayList<MapRoadSection>();
		itscts = new ArrayList<MapIntersection>();
		stopSections = new ArrayList<MapStopSection>();
	}

	/**
	 * @return the lineName
	 */
	public String getLineName() {
		return lineName;
	}

	/**
	 * @param lineName
	 *            the lineName to set
	 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/**
	 * @return the direction
	 */
	public boolean isDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public void addBusLocation(MapBusStop[] nbs) {
		if (!busStops.contains(nbs)) {
			busStops.add(nbs);
		}
	}

	public void addRoadSection(MapRoadSection nrs) {
		if (!roadSections.contains(nrs)) {
			roadSections.add(nrs);
		}
	}

	public void addItsct(MapIntersection nItsct) {
		if (nItsct != null && !itscts.contains(nItsct)) {
			itscts.add(nItsct);
		}
	}

	public ArrayList<MapBusStop[]> getBusLoction() {
		return busStops;
	}

	/**
	 * @return the busStops
	 */
	public ArrayList<MapBusStop[]> getBusStops() {
		return busStops;
	}

	/**
	 * @return the roadSections
	 */
	public ArrayList<MapRoadSection> getRoadSections() {
		return roadSections;
	}

	/**
	 * @return the itscts
	 */
	public ArrayList<MapIntersection> getItscts() {
		return itscts;
	}

	/**
	 * @return the xys
	 */
	public final String getXys() {
		return xys;
	}

	/**
	 * @return the stopSections
	 */
	public ArrayList<MapStopSection> getStopSections() {
		return stopSections;
	}

	/**
	 * ��վ��μ��������վ��
	 * 
	 * @param mss
	 *            Ҫ��ӵ�վ��
	 * @return true�������ӳɹ���false��û�гɹ�
	 */
	public boolean addStopSection(MapStopSection mss) {
		if (mss != null && !stopSections.contains(mss)) {
			return stopSections.add(mss);
		}
		return false;
	}

	/**
	 * �õ������intersection��xys�ж�Ӧ�ĵ��xy���꣬�÷������鴫���intersection�Ƿ��ڸ���·��
	 * 
	 * @param newItsct
	 *            Ҫ���ҵ�intersection
	 * @return 
	 *         �����intersection��xys�ж�Ӧ�ĵ��xy���꣬����ڸ���·��intersection�б����޴���ĸý���ڣ��򷵻�null
	 */
	public Double[] getLineItsctXYS(MapIntersection newItsct) {
		if (!itscts.contains(newItsct)) {
			return null;
		}
		StringTokenizer tokens = new StringTokenizer(xys, ";");
		Double[] xy = getXYSWithItsct(newItsct);
		return xy;
	}

	/**
	 * �õ������intersection��xys�ж�Ӧ�ĵ��xy���꣬�÷��������鴫���intersection�Ƿ��ڸ���·��
	 * 
	 * @param newItsct
	 *            Ҫ���ҵ�intersection
	 * @return �����intersection��xys�ж�Ӧ�ĵ��xy����
	 */
	public Double[] getXYSWithItsct(MapIntersection newItsct) {
		StringTokenizer tokens = new StringTokenizer(xys, ";");
		Double[] xy = new Double[2];
		double minDist = Double.MAX_VALUE;
		while (tokens.hasMoreElements()) {
			String tokenStr = (String) tokens.nextElement();
			String[] xyStr = tokenStr.split(",");
			GPSLocation tempLocation = new GPSLocation(
					Double.parseDouble(xyStr[0]), Double.parseDouble(xyStr[1]));
			double tempDist = tempLocation.realDistance(newItsct.getLocation());
			if (tempDist < minDist) {
				minDist = tempDist;
				xy[0] = Double.parseDouble(xyStr[0]);
				xy[1] = Double.parseDouble(xyStr[1]);
			}
		}

		return xy;
	}

	/**
	 * ���ô����·�εĳ���
	 * 
	 * @param mrs
	 *            Ҫ���ó��ȵ�·��
	 * @return true��������óɹ���false���������·��Ϊ�ջ���·�����˵���ͬ
	 */
	// public boolean setRdSectionLen(MapRoadSection mrs) {
	// if (!roadSections.contains(mrs)) {
	// return false;
	// }
	// double length = 0;
	// Double[] firstXY = getItsctXYInXys(mrs.getFirst());
	// Double[] secondXY = getItsctXYInXys(mrs.getSecond());
	// GPSLocation firstLoc = new GPSLocation(firstXY[0], firstXY[1]);
	// GPSLocation secondLoc = new GPSLocation(secondXY[0], secondXY[1]);
	// length = getLength(firstLoc, secondLoc);
	// if (length == 0) {
	// return false;
	// }
	// mrs.setLength(length);
	// return true;
	// }

	/**
	 * �õ���xys������֮��ľ���
	 * 
	 * @param first
	 * @param second
	 * @return ��xys������֮��ľ���
	 */
	public double getLength(GPSLocation firstLoc, GPSLocation secondLoc) {
		double length = 0;
		// ·����β��xys�ж�Ӧͬһ��ֵ
		if (firstLoc.equals(secondLoc)) {
			return 0;
		}

		GPSLocation preLoc = null;
		StringTokenizer tokens = new StringTokenizer(xys, ";");
		// �ҵ�·�ε�һ���˵���xys�ж�Ӧ��ֵ
		while (tokens.hasMoreElements()) {
			String tokenStr = (String) tokens.nextElement();
			String[] xyStr = tokenStr.split(",");
			GPSLocation tempLoc = new GPSLocation(Double.parseDouble(xyStr[0]),
					Double.parseDouble(xyStr[1]));
			if (tempLoc.equals(firstLoc)) {
				preLoc = tempLoc;
				break;
			}
		}
		// ѭ�������㳤��
		while (tokens.hasMoreElements()) {
			String tokenStr = (String) tokens.nextElement();
			String[] xyStr = tokenStr.split(",");
			GPSLocation tempLoc = new GPSLocation(Double.parseDouble(xyStr[0]),
					Double.parseDouble(xyStr[1]));
			length += preLoc.realDistance(tempLoc);
			preLoc = tempLoc;
			if (tempLoc.equals(secondLoc)) {
				break;
			}
		}

		return length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (direction ? 1231 : 1237);
		result = prime * result
				+ ((lineName == null) ? 0 : lineName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MapBusLine other = (MapBusLine) obj;
		if (direction != other.direction) {
			return false;
		}
		if (lineName == null) {
			if (other.lineName != null) {
				return false;
			}
		} else if (!lineName.equals(other.lineName)) {
			return false;
		}
		return true;
	}

	public String toString() {
		String str = "\nLINE NAME: " + lineName;
		str += "\nXYS:\n\t" + xys;
		str += "\nBUS STOPS:\n\t";
		for (MapBusStop[] stopPair : busStops) {
			str += "original: " + stopPair[0] + " | new: " + stopPair[1] + ";";
		}
		str += "\nINTERSECTIONS: \n\t" + itscts;
		str += "\nROADSECTION: \n\t" + roadSections;

		return str;
	}
}
