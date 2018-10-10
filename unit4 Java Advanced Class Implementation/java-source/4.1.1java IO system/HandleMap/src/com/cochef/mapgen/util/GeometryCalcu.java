package com.cochef.mapgen.util;

import com.cochef.mapgen.data.GPSLocation;

/**
 * 
 */

/**
 * @author mcy
 * 
 */
public class GeometryCalcu {

	/**
	 * ������������ȷ����ֱ�ߵķ��� ax + by + c = 0 �����������������������ľ�γ���ڼ���ʱ��ת��Ϊ��Ӧ�Ĵ������
	 * 
	 * @param loc1st
	 *            ��һ����ľ�γ��
	 * @param loc2nd
	 *            �ڶ�����ľ�γ��
	 * 
	 * @return ��������Ԫ�ص�Double�����飬�ֱ��Ƿ����е�a��b��c
	 */
	public static Double[] getEquPara(GPSLocation loc1st, GPSLocation loc2nd) {
		Double[] equPara = new Double[3];
		double[] xy1 = GPST.BLToGauss(loc1st.getGPSX(), loc1st.getGPSY());
		double[] xy2 = GPST.BLToGauss(loc2nd.getGPSX(), loc2nd.getGPSY());

		if (Math.abs(xy1[1] - xy2[1]) < 0.00001) {
			equPara[0] = 0.0;
			equPara[1] = 1.0;
			equPara[2] = -xy1[1];
		} else {
			equPara[0] = 1.0;
			equPara[1] = (xy2[0] - xy1[0]) / (xy1[1] - xy2[1]);
			equPara[2] = -xy1[0] - equPara[1] * xy1[1];
		}
		return equPara;
	}

	/**
	 * �жϵ��Ƿ����߶ε�һ���߶ξ����ڣ����߶�Ϊ�����ߵ�һ�����������ڣ����γ�Ϊ�߶γ��ȣ���Ϊ�������ֵ��2����
	 * 
	 * @param point
	 *            ��
	 * @param endpoint1st
	 *            �߶εĵ�һ���˵�
	 * @param endpoint2nd
	 *            �߶εĵڶ����˵�
	 * @param radio
	 *            �ж��Ƿ��ٽ�����ֵ
	 * @return true, ����㵽�߶εľ�����һ������ֵ��; false, ����һ����Χ��
	 */
	public static boolean isNearRDLine(GPSLocation point,
			GPSLocation endpoint1st, GPSLocation endpoint2nd, double radio) {
		// ��γ��ת�������
		double[] xy = GPST.BLToGauss(point.getGPSX(), point.getGPSY());
		double[] xy2 = GPST.BLToGauss(endpoint1st.getGPSX(),
				endpoint1st.getGPSY());
		double[] xy3 = GPST.BLToGauss(endpoint2nd.getGPSX(),
				endpoint2nd.getGPSY());
		double x = xy[0];
		double y = xy[1];
		double x2 = xy2[0];
		double x3 = xy3[0];

		if (distP2L(point, endpoint1st, endpoint2nd) < radio) { // ��ֱ�ߵľ�������ֵ��
			Double[] abc = getEquPara(endpoint1st, endpoint2nd);
			double a1 = abc[0];
			double b1 = abc[1];
			double c1 = abc[2];

			// ���߶�����ֱ�ߵľ�����point�Ĵ���
			double a2 = b1;
			double b2 = -a1;
			double c2 = a1 * y - b1 * x;

			// ��ֱ�ߵĽ���
			Double[] newXY = getIntSect(a1, b1, c1, a2, b2, c2);
			if ((newXY[0] - x2) * (newXY[0] - x3) <= 1e-6) { // ���߶η�Χ��
				return true;
			}
		}
		return false;
	}

	/**
	 * �õ�����ֱ���ϵ�ӳ���
	 * 
	 * @param point
	 *            ��
	 * @param endpoint1st
	 *            ȷ��ֱ�ߵĵ�һ����
	 * @param endpoint2nd
	 *            ȷ��ֱ�ߵĵڶ�����
	 * @return ����ֱ���ϵ�ӳ���ľ�γ��
	 */
	public static Double[] getLineMapPoint(GPSLocation point,
			GPSLocation endpoint1st, GPSLocation endpoint2nd) {
		// ��γ��ת�������
		double[] xy = GPST.BLToGauss(point.getGPSX(), point.getGPSY());
		double x = xy[0];
		double y = xy[1];

		Double[] abc = getEquPara(endpoint1st, endpoint2nd);
		double a1 = abc[0];
		double b1 = abc[1];
		double c1 = abc[2];

		// ���߶�����ֱ�ߵľ�����point�Ĵ���
		double a2 = b1;
		double b2 = -a1;
		double c2 = a1 * y - b1 * x;

		// ��ֱ�ߵĽ���
		Double[] newXY = getIntSect(a1, b1, c1, a2, b2, c2);
		double[] _x = GPST.GaussToBL(newXY[0], newXY[1]);
		newXY[0] = _x[0];
		newXY[1] = _x[1];

		return newXY;
	}

	/**
	 * �õ������߶��ϵ�ӳ���
	 * 
	 * @param point
	 *            ��
	 * @param endpoint1st
	 *            ȷ���߶εĵ�һ����
	 * @param endpoint2nd
	 *            ȷ���߶εĵڶ�����
	 * @return �����߶��ϵ�ӳ���ľ�γ�ȣ���ӳ��㲻���߶��ϣ��򷵻�null
	 */
	public static Double[] getSegmentMapPoint(GPSLocation point,
			GPSLocation endpoint1st, GPSLocation endpoint2nd) {
		// ��ֱ�ߵĽ���
		Double[] newXY = getLineMapPoint(point, endpoint1st, endpoint2nd);
		// ��γ��ת�������
		double x = GPST.BLToGauss(newXY[0], newXY[1])[0];

		double x2 = GPST
				.BLToGauss(endpoint1st.getGPSX(), endpoint1st.getGPSY())[0];
		double x3 = GPST
				.BLToGauss(endpoint2nd.getGPSX(), endpoint2nd.getGPSY())[0];
		if ((x - x2) * (x - x3) > 1e-6) {
			newXY = null;
		}

		return newXY;
	}

	/**
	 * ��㵽ֱ�ߵľ��룬ֱ������������ȷ��
	 * 
	 * @param point
	 *            ��
	 * @param endpoint1st
	 *            ȷ��ֱ�ߵĵ�һ����
	 * @param endpoint2nd
	 *            ȷ��ֱ�ߵĵڶ�����
	 * @return �㵽ֱ�ߵľ��룬��λΪm
	 */
	public static double distP2L(GPSLocation point, GPSLocation endpoint1st,
			GPSLocation endpoint2nd) {
		Double[] abc = getEquPara(endpoint1st, endpoint2nd);
		double[] xy = GPST.BLToGauss(point.getGPSX(), point.getGPSY());
		double x = xy[0];
		double y = xy[1];
		double dist = Math.abs(abc[0] * x + abc[1] * y + abc[2])
				/ Math.sqrt(abc[0] * abc[0] + abc[1] * abc[1]);
		return dist;
	}

	/**
	 * �㵽�߶εľ��룬�߶�
	 * 
	 * @param point
	 *            ��
	 * @param endpoint1st
	 *            ȷ��ֱ�ߵĵ�һ����
	 * @param endpoint2nd
	 *            ȷ��ֱ�ߵĵڶ�����
	 * @return �㵽�߶εľ��룬��λΪm������㲻���߶�Ϊ�����ߵľ������򷵻�Double.MAX_VALUE
	 */
	public static double distP2Sec(GPSLocation point, GPSLocation endpoint1st,
			GPSLocation endpoint2nd) {
		Double[] mapPoint = getLineMapPoint(point, endpoint1st, endpoint2nd);
		double mapX = GPST.BLToGauss(mapPoint[0], mapPoint[1])[0];
		double x2 = GPST
				.BLToGauss(endpoint1st.getGPSX(), endpoint1st.getGPSY())[0];
		double x3 = GPST
				.BLToGauss(endpoint2nd.getGPSX(), endpoint2nd.getGPSY())[0];
		if ((mapX - x2) * (mapX - x3) < 1e-6) { // ���߶η�Χ��
			return distP2L(point, endpoint1st, endpoint2nd);
		} else {
			return Double.MAX_VALUE;
		}

	}

	/**
	 * ������ֱ�߱�׼��������ֱ�߽���
	 * 
	 * @param a1
	 *            ��һֱ�߱�׼���̵�һ����
	 * @param b1
	 *            ��һֱ�߱�׼���̵ڶ�����
	 * @param c1
	 *            ��һֱ�߱�׼���̵�������
	 * @param a2
	 *            �ڶ�ֱ�߱�׼���̵�һ����
	 * @param b2
	 *            �ڶ�ֱ�߱�׼���̵ڶ�����
	 * @param c2
	 *            �ڶ�ֱ�߱�׼���̵�������
	 * @return ����·������ֱ�߽��������ꣻ����·��ƽ�У��򷵻�null
	 */
	public static Double[] getIntSect(double a1, double b1, double c1,
			double a2, double b2, double c2) {
		Double[] itsct = null;
		double fenmu = a1 * b2 - a2 * b1;
		if (!(Math.abs(fenmu) < 1e-6)) {
			itsct = new Double[2];
			itsct[0] = (b1 * c2 - b2 * c1) / fenmu;
			itsct[1] = (c1 * a2 - c2 * a1) / fenmu;
		}
		return itsct;
	}

}
