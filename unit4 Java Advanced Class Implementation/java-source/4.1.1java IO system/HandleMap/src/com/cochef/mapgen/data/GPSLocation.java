package com.cochef.mapgen.data;

import com.cochef.mapgen.util.GPST;

/**
 * 
 */

/**
 * ��γ��λ��
 * 
 * @author zhengzhen
 */
public class GPSLocation {

	/**
	 * ����
	 */
	private double locationX;

	/**
	 * γ��
	 */
	private double locationY;

	/**
	 * ���캯��������GPSLocation����
	 * 
	 * @param initLocationX
	 *            ����
	 * @param initLocationY
	 *            γ��
	 */
	public GPSLocation(double initLocationX, double initLocationY) {
		locationX = initLocationX;
		locationY = initLocationY;
	}
	
	public GPSLocation(Double[] xy) {
		locationX = xy[0];
		locationY = xy[1];
	}

	/**
	 * �õ�����
	 * 
	 * @return ����
	 */
	public double getGPSX() {
		return locationX;
	}

	/**
	 * ���þ���
	 * 
	 * @param newLocationX
	 *            Ҫ���õľ���ֵ
	 */
	public void setGPSX(double newLocationX) {
		this.locationX = newLocationX;
	}

	/**
	 * �õ�γ��
	 * 
	 * @return γ��
	 */
	public double getGPSY() {
		return locationY;
	}

	/**
	 * ����γ��
	 * 
	 * @param newLocationY
	 *            Ҫ���õ�γ��ֵ
	 */
	public void setGPSY(double newLocationY) {
		this.locationY = newLocationY;
	}

	/**
	 * �����㣨������γ����Ϣ������������֮��ľ���
	 * 
	 * @param point1
	 *            ��һ����
	 * @param point2
	 *            �ڶ�����
	 * @return �����������֮��ľ���
	 */
	public static double realDistance(GPSLocation point1, GPSLocation point2) {
		double lon1 = point1.getGPSX();
		double lat1 = point1.getGPSY();
		double lon2 = point2.getGPSX();
		double lat2 = point2.getGPSY();

		return getDistance(lon1, lat1, lon2, lat2);
	}

	/**
	 * ����ö���ʹ������֮��ľ���
	 * 
	 * @param point
	 *            ����ĵ�
	 * @return ����ĵ�������ߵľ���
	 */
	public double realDistance(GPSLocation point) {
		double lon = point.getGPSX();
		double lat = point.getGPSY();

		return getDistance(locationX, locationY, lon, lat);
	}

	/**
	 * �������龭γ�ȣ�������֮��ľ���
	 * 
	 * @param lon1
	 *            ��һ����ľ���
	 * @param lat1
	 *            ��һ�����γ��
	 * @param lon2
	 *            �ڶ�����ľ���
	 * @param lat2
	 *            �ڶ������γ��
	 * @return ������֮��ľ���
	 */
	private static double getDistance(double lon1, double lat1, double lon2,
			double lat2) {
		// TODO Auto-generated method stub
		double[] xy1 = GPST.BLToGauss(lon1, lat1);
		double[] xy2 = GPST.BLToGauss(lon2, lat2);
		return Math.sqrt((xy2[0] - xy1[0]) * (xy2[0] - xy1[0])
				+ (xy2[1] - xy1[1]) * (xy2[1] - xy1[1]));
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
		long temp;
		temp = Double.doubleToLongBits(locationX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(locationY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		GPSLocation other = (GPSLocation) obj;
		if (Double.doubleToLongBits(locationX) != Double
				.doubleToLongBits(other.locationX)) {
			return false;
		}
		if (Double.doubleToLongBits(locationY) != Double
				.doubleToLongBits(other.locationY)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return locationX + "," + locationY;
	}

	public static GPSLocation parseGpsLocation(String gpsString) {
		String[] strXY = gpsString.split(",");
		GPSLocation tempLocation = new GPSLocation(
				Double.parseDouble(strXY[0]), Double.parseDouble(strXY[1]));
		return tempLocation;
	}
	
	public static void main(String[] args) {
		GPSLocation loc1 = GPSLocation.parseGpsLocation("108.86276,34.278769");
		GPSLocation loc2 = GPSLocation.parseGpsLocation("108.858407,34.273071");
		System.err.println(loc1.realDistance(loc2));
		
	}

}
