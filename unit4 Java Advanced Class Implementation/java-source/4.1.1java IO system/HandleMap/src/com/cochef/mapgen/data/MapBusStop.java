package com.cochef.mapgen.data;

/**
 * վ������ģ�͵ĳ���
 * 
 * @author zhengzhen
 */
public class MapBusStop {
	private String stopName;
	private GPSLocation location;
	private MapRoadSection roadSection;

	/**
	 * ���캯��
	 * 
	 * @param initStopID
	 *            վ��IDҪ���õĳ�ֵ
	 * @param initStopName
	 *            վ������Ҫ���õĳ�ֵ
	 */
	public MapBusStop(String initStopName, GPSLocation initGpsLocation) {
		stopName = initStopName;
		location = initGpsLocation;
	}

	/**
	 * ���캯��
	 * 
	 * @param initStopID
	 *            վ��IDҪ���õĳ�ֵ
	 * @param initStopName
	 *            վ������Ҫ���õĳ�ֵ
	 * @param initCoordX
	 *            վ�㾭��
	 * @param initCoordY
	 *            վ��γ��
	 * @param initBusLines
	 *            վ�����ڵ�·�ߵ��б�
	 */
	public MapBusStop(int initStopID, String initStopName, float initCoordX,
			float initCoordY) {
		stopName = initStopName;
		location = new GPSLocation(initCoordX, initCoordY);
	}

	/**
	 * ���վ������
	 * 
	 * @return վ������
	 */
	public String getStopName() {
		return stopName;
	}

	/**
	 * ����վ������
	 * 
	 * @param stopName
	 *            Ҫ���õ�վ������
	 */
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}

	/**
	 * ���վ�㾭��
	 * 
	 * @return վ�㾭��
	 */
	public double getCoordX() {
		return this.location.getGPSX();
	}

	/**
	 * ����վ�㾭��
	 * 
	 * @param coordX
	 *            Ҫ���õ�վ�㾭��
	 */
	public void setCoordX(float coordX) {
		this.location.setGPSX(coordX);
	}

	/**
	 * ���վ��γ��
	 * 
	 * @return վ��γ��
	 */
	public double getCoordY() {
		return this.location.getGPSY();
	}

	/**
	 * ����վ��γ��
	 * 
	 * @param coordY
	 *            Ҫ���õ�վ��γ��
	 */
	public void setCoordY(float coordY) {
		this.location.setGPSY(coordY);
	}

	/**
	 * 
	 * ���վ��λ��
	 * 
	 * @return վ�����ڵ�λ��
	 */
	public GPSLocation getLocation() {
		return location;
	}

	/**
	 * 
	 * ����վ��λ��
	 * 
	 * @param ����վ�����ڵ�λ��
	 */
	public void setLocation(GPSLocation location) {
		this.location = location;
	}

	/**
	 * @return the roadSection
	 */
	public MapRoadSection getRoadSection() {
		return roadSection;
	}

	/**
	 * @param roadSection
	 *            the roadSection to set
	 */
	public void setRoadSection(MapRoadSection roadSection) {
		this.roadSection = roadSection;
	}

	public String toString() {
		return stopName + "-" + location;
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
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((stopName == null) ? 0 : stopName.hashCode());
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
		MapBusStop other = (MapBusStop) obj;
		if (location == null) {
			if (other.location != null) {
				return false;
			}
		} else if (!location.equals(other.location)) {
			return false;
		}
		return true;
	}

}
