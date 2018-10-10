package com.cochef.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Vector;

import com.cochef.mapgen.data.GPSLocation;
import com.cochef.mapgen.data.MapDataCollection;
import com.cochef.mapgen.data.MapRoadSection;
import com.cochef.mapgen.util.GeometryCalcu;

/**
 * @author mcy
 * 
 */
public class Parser {

	private static BusSqlWrite busSqlWriter;
	private static SubSqlWrite subSqlWriter;
	private static ArrayList<GPSLocation> stopLocations;

	public static void main(String[] args) {
		try {
			Parser parser = new Parser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Parser() throws Exception {
		busSqlWriter = new BusSqlWrite();
		subSqlWriter = new SubSqlWrite();
		stopLocations = new ArrayList<GPSLocation>();
		String busFolder = "buslineInfo/";// ������·��Ϣ�ļ�·��
		String subFolder = "sublineInfo/";// ������·��Ϣ�ļ�·��
		OutputStreamWriter writerSQL = new OutputStreamWriter(
				new FileOutputStream(new File("insert.sql")), "UTF-8");// д��sqls�������ݿ�����ļ�
		busAssociate(busFolder, writerSQL);
		subAssociate(subFolder, writerSQL);
		writerSQL.close();
	}

	private void subAssociate(String subFolder, OutputStreamWriter writerSQL)
			throws Exception {

		OutputStreamWriter writerHanSub = new OutputStreamWriter(
				new FileOutputStream(new File("HanjingliSub.txt")), "UTF-8");// д��hanjingli�������ɵ�ͼ�õ��ļ�
		OutputStreamWriter writerHan2Sub = new OutputStreamWriter(
				new FileOutputStream(new File("Hanjingli2Sub.txt")), "UTF-8");// д��hanjingli2�������ɵ�ͼ�õ��ļ�
		OutputStreamWriter writerZhengSub = new OutputStreamWriter(
				new FileOutputStream(new File("subline.txt")), "UTF-8");// д��busline������������վ���������Դ�ļ�

		String subFileNames[] = new File(subFolder).list();
		for (int i = 0; i < subFileNames.length; i++) {
			subSqlGen(subFolder + subFileNames[i], writerHanSub, writerHan2Sub,
					writerZhengSub, writerSQL);
		}
		writerHanSub.close();
		writerHan2Sub.close();
		writerZhengSub.close();

		// MapDataCollection data = new MapDataCollection();??
		// subSqlWriter.insertIntosubstop_in_rdsection(data,writerSQL);
	}

	private void busAssociate(String busFolder, OutputStreamWriter writerSQL)
			throws Exception {

		OutputStreamWriter writerHanBus = new OutputStreamWriter(
				new FileOutputStream(new File("HanjingliBus.txt")), "UTF-8");// д��hanjingli�������ɵ�ͼ�õ��ļ�
		OutputStreamWriter writerHan2Bus = new OutputStreamWriter(
				new FileOutputStream(new File("Hanjingli2Bus.txt")), "UTF-8");// д��hanjingli2�������ɵ�ͼ�õ��ļ�
		OutputStreamWriter writerZhengBus = new OutputStreamWriter(
				new FileOutputStream(new File("busline.txt")), "UTF-8");// д��busline������������վ���������Դ�ļ�

		String busFileNames[] = new File(busFolder).list();
		for (int i = 0; i < busFileNames.length; i++) {
			busSqlGen(busFolder + busFileNames[i], writerHanBus, writerHan2Bus,
					writerZhengBus, writerSQL);
		}
		writerHanBus.close();
		writerHan2Bus.close();
		writerZhengBus.close();

		MapDataCollection data = new MapDataCollection();
		busSqlWriter.InsertAbs(data, writerSQL);
	}

	private void subSqlGen(String sourcePath,
			OutputStreamWriter writerHanjingli,
			OutputStreamWriter writerHanjingli2,
			OutputStreamWriter writerZheng, OutputStreamWriter writerSQL)
			throws Exception {

		InputStreamReader read = new InputStreamReader(new FileInputStream(
				new File(sourcePath)), "UTF-8");
		BufferedReader reader = new BufferedReader(read);// ��ԭʼ��Ϣ�ļ���ȡ��Ϣ����
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(
						new File("tempfiles/sub/"
								+ sourcePath.substring(sourcePath
										.lastIndexOf("/") + 1))), "UTF-8");// д�������õ��м��ļ�

		InputStreamReader read2 = new InputStreamReader(
				new FileInputStream(
						new File("tempfiles/sub/"
								+ sourcePath.substring(sourcePath
										.lastIndexOf("/") + 1))), "UTF-8");// ���м��ļ���ȡ��Ϣ����
		BufferedReader reader2 = new BufferedReader(read2);

		String temp = "";

		// �����淶�����ݸ�ʽ
		if ((temp = reader.readLine()) != null) {

			temp = temp.replace("\",", "\"\n").replace("},", "}\n")
					.replace("],", "]\n").replace(":[", ":\n[")
					.replace("\"xys\":\"", "\"xys\":\"\n").replace(";", "\n");
			writer.write(temp);
		}
		reader.close();
		writer.close();

		boolean xysPassed = false;// ��"xys":"�Ѷ���ǣ�����ָʾ����ս�xy������������ֶ��ɡ�name��ָʾ����·����վ����
		int xysCounter = 0;
		boolean direction = true;// �����У�true����
		boolean pay_way = true;// �ܷ�ˢ��,true��ˢ
		boolean uppay_way = true;
		String total_price = "";// ���Ʊ�ۣ���Ʊ��
		String uptotal_price = "";
		String basic_price = "";// �𲽼�
		String line_id = "";// ��·���ƣ���916·(��������վ--������)
		String line_idUp = "";// ������·����
		String stop_name = "", stop_coord_x = "", stop_coord_y = "";// վ��������
		String stopXYs = "";// վ���꼯���ַ���
		String stopNames = "";
		String itsctXYs = "";// �ս����꼯���ַ���
		String itsct_coord_x = "", itsct_coord_y = "";// �ս�����
		String start_time = "", end_time = "";// ��ĩ�෢��ʱ��
		String firstStopX = "";
		String firstStopY = "";
		String itsctXYsUp = "";// ����itsctXYs���ʱ��������itsctXYs
		String itsctXYsDown = "";
		String savedDownStopXYs = "";// stopXYs���ʱ����stopXYs
		String savedUpStopXYs = "";
		String upStartTime = "", downStartTime = "";// �������װ෢��ʱ��
		String upEndTime = "", downEndTime = "";// ������ĩ�෢��ʱ��
		String savedUpStopNames = "";

		while ((temp = reader2.readLine()) != null) {
			// ��ȡstop.stop_nameֵ
			if (temp.startsWith("\"name\"") && !xysPassed) {
				stop_name = temp.substring(8, temp.length() - 1);
				stopNames += stop_name + " ";
			}
			// ��ȡstop.stop_coord_x,stop.stop_coord_yֵ
			if (temp.startsWith("\"xy\"") && !temp.contains("s")) {
				temp = temp.substring(6, temp.length() - 2);
				if (temp.endsWith("\""))// ���һ��վ����"}]��β��������վ���"}��β��ȣ��Ӻ����ȥ�����ַ��󣬻�����һ��"
					temp = temp.substring(0, temp.length() - 1);
				stop_coord_x = temp.split(",")[0];
				stop_coord_y = temp.split(",")[1];
				stopXYs += stop_coord_x + "," + stop_coord_y + " ";
			}
			if (!stop_name.equals("") && !stop_coord_x.equals("")
					&& !stop_coord_y.equals("")) {
				stop_name = "";// ����
				stop_coord_x = "";
				stop_coord_y = "";
			}
			if (temp.contains("ic_card")) {
				if (temp.endsWith("0\""))
					pay_way = false;
				else
					pay_way = true;
			}
			if (temp.contains("basic_price")) {
				String s = temp.split(":")[1];
				basic_price = s.substring(1, s.length() - 1);
			}
			if (temp.contains("total_price")) {
				String s = temp.split(":")[1];
				total_price = s.substring(1, s.length() - 1);
			}
			// ����ս�xy����
			if (temp.equals("\"xys\":\"")) {
				xysPassed = true;
				xysCounter++;
			}
			// ��ȡintersection.itsct_coord_x��intersection.itsct_coord_yֵ
			if (xysPassed && temp.contains(",") && !temp.startsWith("\"xy\"")) {

				itsct_coord_x = temp.split(",")[0];
				itsct_coord_y = temp.split(",")[1];
				if (itsct_coord_y.endsWith("\"")) {
					itsct_coord_y = itsct_coord_y.substring(0,
							itsct_coord_y.length() - 1);
				}
				itsctXYs += itsct_coord_x + "," + itsct_coord_y + " ";
			}
			if (temp.contains("end_time")) {
				end_time = temp.substring(12, temp.length() - 1);
				if (direction) {
					upEndTime = end_time;
				} else {
					downEndTime = end_time;
				}
			}
			// ��ȡbusline.line_idֵ
			if (temp.startsWith("\"name\"") && xysPassed) {
				line_id = temp.substring(8, temp.length() - 1);
			}
			// ��ȡbusline.summer_first��busline.summer_last,busline.winter_first��busline.winter_lastֵ
			if (temp.contains("start_time")) {
				start_time = temp.substring(14, temp.length() - 1);
				if (direction) {
					upStartTime = start_time;
				} else {
					downStartTime = start_time;
				}
			}
			if (temp.contains("commutation_ticket") && xysPassed) {// �������ǳ��ֵĵڶ���commutation_ticket,����������ת����ʱ��

				itsctXYsUp += itsctXYs;
				savedUpStopXYs = stopXYs;
				// System.out.println(savedUpStopXYs);
				stopXYs = stopXYs.substring(0, stopXYs.length() - 1);
				savedUpStopNames = stopNames;
				// System.out.println(savedUpStopNames);
				line_idUp = line_id;
				itsctXYsUp = itsctXYs;
				uppay_way = pay_way;
				uptotal_price = total_price;
				direction = false;
				stopXYs = "";
				itsctXYs = "";
				xysPassed = false;
				end_time = "";
				stopNames = "";
			}
			if (temp.contains("version")) {// ���������ļ��Ľ�β�����ж�ȡ���
				itsctXYsDown = itsctXYs;
				savedDownStopXYs = stopXYs;
				direction = true;
				start_time = "";
				stopXYs = "";
				itsctXYs = "";
				xysPassed = false;
			}
		}

		// ��Hanjingli.txt�����������ԭʼ�յ���Ϣ����Hanjingli2�������������·����Ϣ
		String up = itsctXYsUp.replace(" ", ";");
		up = up.substring(0, up.length() - 1);
		String down = itsctXYsDown.replace(" ", ";");
		down = down.substring(0, down.length() - 1);
		writerHanjingli.write("\"" + up + "\",\n");
		writerHanjingli.write("\"" + down + "\",\n");
		writerHanjingli2.write(line_idUp + "," + line_id + ",");

		// ��busline.txt���������·��������ʼ�յ㡢��ʼվ����Ϣ
		writerZheng.write(line_idUp + "\n");
		writerZheng.write(up + "\n");
		String stopnames[] = savedUpStopNames.split(" ");
		String stopxys[] = savedUpStopXYs.split(" ");
		for (int i = 0; i < 2 * stopxys.length; i++) {
			if (i % 2 == 0) {
				writerZheng.write(stopnames[i / 2] + ";");
			} else {
				writerZheng.write(stopxys[i / 2] + ";");
			}
		}
		writerZheng.write("\n");

		// ��busline.txt���������·��������ʼ�յ㡢��ʼվ����Ϣ
		writerZheng.write(line_id + "\n");
		writerZheng.write(down + "\n");
		String dstopnames[] = stopNames.split(" ");
		String dstopxys[] = savedDownStopXYs.split(" ");
		for (int i = 0; i < 2 * dstopxys.length; i++) {
			if (i % 2 == 0) {
				writerZheng.write(dstopnames[i / 2] + ";");
			} else {
				writerZheng.write(dstopxys[i / 2] + ";");
			}
		}
		writerZheng.write("\n");

		line_idUp = getLineName(line_idUp);// ��ȥline_id���Ų���
		line_id = getLineName(line_id);// ��ȥline_id���Ų���

		// ��stop���������վ��
		String[] stopxy;
		// System.out.println("stopnames size: " + stopnames.length);
		// System.out.println("stopxys size: " + stopxys.length);
		for (int i = 0; i < stopnames.length; i++) {
			stopxy = stopxys[i].split(",");
			GPSLocation newStopLoc = GPSLocation.parseGpsLocation(stopxys[i]);
			if (!stopLocations.contains(newStopLoc)) {
				stopLocations.add(newStopLoc);
				subSqlWriter.insertIntosubway_stop(stopnames[i],
						String.valueOf(newStopLoc.getGPSX()),
						String.valueOf(newStopLoc.getGPSY()), writerSQL);
				writerSQL.flush();
			}
		}
		// ��stop���������վ��
		String[] dstopxy;
		for (int i = 0; i < dstopnames.length; i++) {
			dstopxy = dstopxys[i].split(",");
			GPSLocation newStopLoc = GPSLocation.parseGpsLocation(dstopxys[i]);
			if (!stopLocations.contains(newStopLoc)) {
				stopLocations.add(newStopLoc);
				subSqlWriter.insertIntosubway_stop(dstopnames[i],
						String.valueOf(newStopLoc.getGPSX()),
						String.valueOf(newStopLoc.getGPSY()), writerSQL);
				writerSQL.flush();
			}
		}

		// ��busline�����������·
		String[] first = stopxys[0].split(",");
		firstStopX = first[0];
		firstStopY = first[1];
		if (uptotal_price.equals("")) {
			uptotal_price = "1";
		}
		subSqlWriter.insertIntosubway_line(firstStopX, firstStopY, line_idUp,
				true, uppay_way, uptotal_price, upStartTime, upEndTime, up,
				writerSQL);
		writerSQL.flush();
		// ��busline�����������·
		String[] dfirst = dstopxys[0].split(",");
		firstStopX = dfirst[0];
		firstStopY = dfirst[1];
		if (total_price.equals("")) {
			total_price = "1";
		}
		subSqlWriter.insertIntosubway_line(firstStopX, firstStopY, line_id,
				false, pay_way, total_price, downStartTime, downEndTime, down,
				writerSQL);
		writerSQL.flush();

		// ��stop_in_line���������վ�㣨��ʵվ�㣩����·�İ󶨹�ϵ
		subSqlWriter.insertIntosubstop_in_line(savedUpStopXYs, line_idUp, true,
				writerSQL);
		writerSQL.flush();
		// ��stop_in_line���������վ�㣨��ʵվ�㣩����·�İ󶨹�ϵ
		subSqlWriter.insertIntosubstop_in_line(savedDownStopXYs, line_id,
				false, writerSQL);

		// ��substop_in_rdsection���������վ����·�ε�ӳ���ϵ
		subSqlWriter.insertIntosubstop_in_rdsection(stopxys, writerSQL);
		// ��substop_in_rdsection���������վ����·�ε�ӳ���ϵ
		subSqlWriter.insertIntosubstop_in_rdsection(dstopxys, writerSQL);

		// ��substop_in_rdsection�����ֶ������������

		// ����һ��������������
		String subway_1_0 = "108.816737,34.298547;108.83997,34.288376;108.852035,34.284542;"
				+ "108.863198,34.278407;108.871803,34.274121;108.891721,34.269324;108.912642,34.269466;"
				+ "108.92144,34.269581;108.933375,34.269706;108.947141,34.269901;108.962933,34.270025;"
				+ "108.972922,34.27002;108.984246,34.269466;108.995528,34.269502;109.013772,34.269604;"
				+ "109.029173,34.271966;109.044129,34.27393;109.056306,34.275309;109.069433,34.279258";
		String subway_1_1 = "109.069433,34.279258;109.056306,34.275309;109.044129,34.27393;"
				+ "109.029173,34.271966;109.013772,34.269604;108.995528,34.269502;108.984246,34.269466;"
				+ "108.972922,34.27002;108.962933,34.270025;108.947141,34.269901;108.933375,34.269706;"
				+ "108.92144,34.269581;108.912642,34.269466;108.891721,34.269324;108.871803,34.274121;"
				+ "108.863198,34.278407;108.852035,34.284542;108.83997,34.288376;108.816737,34.298547";

		// ��������������������
		String subway_3_0 = "109.068859,34.399191;109.070029,34.382674;109.065367,34.360208;"
				+ "109.055352,34.340406;109.049612,34.328406;109.03258,34.311061;109.006938,34.310751;"
				+ "108.995576,34.303076;108.995587,34.291323;108.995592,34.277481;108.995528,34.269502;"
				+ "108.996681,34.251155;108.996729,34.242703;108.99395,34.230907;108.976656,34.223628;"
				+ "108.964012,34.222887;108.946685,34.222794;108.931895,34.222892;108.917314,34.225345;"
				+ "108.904327,34.231168;108.888754,34.237249;108.872243,34.237497;108.851547,34.237914";
		String subway_3_1 = "108.851547,34.237914;108.872243,34.237497;108.888754,34.237249;"
				+ "108.904327,34.231168;108.917314,34.225345;108.931895,34.222892;108.946685,34.222794;"
				+ "108.964012,34.222887;108.976656,34.223628;108.99395,34.230907;108.996729,34.242703;"
				+ "108.996681,34.251155;108.995528,34.269502;108.995592,34.277481;108.995587,34.291323;"
				+ "108.995576,34.303076;109.006938,34.310751;109.03258,34.311061;109.049612,34.328406;"
				+ "109.055352,34.340406;109.065367,34.360208;109.070029,34.382674;109.068859,34.399191";
		subSqlWriter.insertIntosubstop_in_rdsection(subway_1_0.split(";"), writerSQL);
		subSqlWriter.insertIntosubstop_in_rdsection(subway_1_1.split(";"), writerSQL);
		subSqlWriter.insertIntosubstop_in_rdsection(subway_3_0.split(";"), writerSQL);
		subSqlWriter.insertIntosubstop_in_rdsection(subway_3_1.split(";"), writerSQL);
		
		writerSQL.flush();

		reader2.close();

		if (xysCounter > 2)
			System.out.println("�����ļ�" + sourcePath + "�ĸ�ʽ���ԣ�xysNum:"
					+ xysCounter);

	}

	/**
	 * �����·������Ϣ�ļ���·���µ�ÿ���ļ���Ԥ�����temp.txt�����н�����ʹ�ô�����ļ�����������sqls.sql
	 * 
	 * @param sourcePath
	 *            ������·��Ϣ�ļ�����staticGen/prims/916.txt
	 * @param writer2
	 *            �����sqls.sql�������
	 */
	private static void busSqlGen(String sourcePath,
			OutputStreamWriter writerHanjingli,
			OutputStreamWriter writerHanjingli2,
			OutputStreamWriter writerZheng, OutputStreamWriter writerSQL)
			throws Exception {

		InputStreamReader read = new InputStreamReader(new FileInputStream(
				new File(sourcePath)), "UTF-8");
		BufferedReader reader = new BufferedReader(read);// ��ԭʼ��Ϣ�ļ���ȡ��Ϣ����
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(
						new File("tempfiles/bus/"
								+ sourcePath.substring(sourcePath
										.lastIndexOf("/") + 1))), "UTF-8");// д�������õ��м��ļ�

		InputStreamReader read2 = new InputStreamReader(
				new FileInputStream(
						new File("tempfiles/bus/"
								+ sourcePath.substring(sourcePath
										.lastIndexOf("/") + 1))), "UTF-8");// ���м��ļ���ȡ��Ϣ����
		BufferedReader reader2 = new BufferedReader(read2);

		String temp = "";

		// �����淶�����ݸ�ʽ
		if ((temp = reader.readLine()) != null) {

			temp = temp.replace("\",", "\"\n").replace("},", "}\n")
					.replace("],", "]\n").replace(":[", ":\n[")
					.replace("\"xys\":\"", "\"xys\":\"\n").replace(";", "\n");
			writer.write(temp);
		}
		reader.close();
		writer.close();

		boolean xysPassed = false;// ��"xys":"�Ѷ���ǣ�����ָʾ����ս�xy������������ֶ��ɡ�name��ָʾ����·����վ����
		int xysCounter = 0;
		boolean direction = true;// �����У�true����
		boolean pay_way = true;// �ܷ�ˢ��,true��ˢ
		boolean uppay_way = true;
		String total_price = "";// ���Ʊ�ۣ���Ʊ��
		String uptotal_price = "";
		String basic_price = "";// �𲽼�
		String line_id = "";// ��·���ƣ���916·(��������վ--������)
		String line_idUp = "";// ������·����
		String stop_name = "", stop_coord_x = "", stop_coord_y = "";// վ��������
		String stopXYs = "";// վ���꼯���ַ���
		String stopNames = "";
		String itsctXYs = "";// �ս����꼯���ַ���
		String itsct_coord_x = "", itsct_coord_y = "";// �ս�����
		String start_time = "", end_time = "";// ��ĩ�෢��ʱ��
		String firstStopX = "";
		String firstStopY = "";
		String itsctXYsUp = "";// ����itsctXYs���ʱ��������itsctXYs
		String itsctXYsDown = "";
		String savedDownStopXYs = "";// stopXYs���ʱ����stopXYs
		String savedUpStopXYs = "";
		String upStartTime = "", downStartTime = "";// �������װ෢��ʱ��
		String upEndTime = "", downEndTime = "";// ������ĩ�෢��ʱ��
		String savedUpStopNames = "";

		while ((temp = reader2.readLine()) != null) {
			// ��ȡstop.stop_nameֵ
			if (temp.startsWith("\"name\"") && !xysPassed) {
				stop_name = temp.substring(8, temp.length() - 1);
				stopNames += stop_name + " ";
			}
			// ��ȡstop.stop_coord_x,stop.stop_coord_yֵ
			if (temp.startsWith("\"xy\"") && !temp.contains("s")) {
				temp = temp.substring(6, temp.length() - 2);
				if (temp.endsWith("\""))// ���һ��վ����"}]��β��������վ���"}��β��ȣ��Ӻ����ȥ�����ַ��󣬻�����һ��"
					temp = temp.substring(0, temp.length() - 1);
				stop_coord_x = temp.split(",")[0];
				stop_coord_y = temp.split(",")[1];
				stopXYs += stop_coord_x + "," + stop_coord_y + " ";
			}
			if (!stop_name.equals("") && !stop_coord_x.equals("")
					&& !stop_coord_y.equals("")) {
				stop_name = "";// ����
				stop_coord_x = "";
				stop_coord_y = "";
			}
			if (temp.contains("ic_card")) {
				if (temp.endsWith("0\""))
					pay_way = false;
				else
					pay_way = true;
			}
			if (temp.contains("basic_price")) {
				String s = temp.split(":")[1];
				basic_price = s.substring(1, s.length() - 1);
			}
			if (temp.contains("total_price")) {
				String s = temp.split(":")[1];
				total_price = s.substring(1, s.length() - 1);
			}
			// ����ս�xy����
			if (temp.equals("\"xys\":\"")) {
				xysPassed = true;
				xysCounter++;
			}
			// ��ȡintersection.itsct_coord_x��intersection.itsct_coord_yֵ
			if (xysPassed && temp.contains(",") && !temp.startsWith("\"xy\"")) {

				itsct_coord_x = temp.split(",")[0];
				itsct_coord_y = temp.split(",")[1];
				if (itsct_coord_y.endsWith("\"")) {
					itsct_coord_y = itsct_coord_y.substring(0,
							itsct_coord_y.length() - 1);
				}
				itsctXYs += itsct_coord_x + "," + itsct_coord_y + " ";
			}
			if (temp.contains("end_time")) {
				end_time = temp.substring(12, temp.length() - 1);
				if (direction) {
					upEndTime = end_time;
				} else {
					downEndTime = end_time;
				}
			}
			// ��ȡbusline.line_idֵ
			if (temp.startsWith("\"name\"") && xysPassed) {
				line_id = temp.substring(8, temp.length() - 1);
			}
			// ��ȡbusline.summer_first��busline.summer_last,busline.winter_first��busline.winter_lastֵ
			if (temp.contains("start_time")) {
				start_time = temp.substring(14, temp.length() - 1);
				if (direction) {
					upStartTime = start_time;
				} else {
					downStartTime = start_time;
				}
			}
			if (temp.contains("commutation_ticket") && xysPassed) {// �������ǳ��ֵĵڶ���commutation_ticket,����������ת����ʱ��

				itsctXYsUp += itsctXYs;
				savedUpStopXYs = stopXYs;
				// System.out.println(savedUpStopXYs);
				stopXYs = stopXYs.substring(0, stopXYs.length() - 1);
				savedUpStopNames = stopNames;
				// System.out.println(savedUpStopNames);
				line_idUp = line_id;
				itsctXYsUp = itsctXYs;
				uppay_way = pay_way;
				uptotal_price = total_price;
				direction = false;
				stopXYs = "";
				itsctXYs = "";
				xysPassed = false;
				end_time = "";
				stopNames = "";
			}
			if (temp.contains("version")) {// ���������ļ��Ľ�β�����ж�ȡ���
				itsctXYsDown = itsctXYs;
				savedDownStopXYs = stopXYs;
				direction = true;
				start_time = "";
				stopXYs = "";
				itsctXYs = "";
				xysPassed = false;
			}
		}

		// ��Hanjingli.txt�����������ԭʼ�յ���Ϣ����Hanjingli2�������������·����Ϣ
		String up = itsctXYsUp.replace(" ", ";");
		up = up.substring(0, up.length() - 1);
		String down = itsctXYsDown.replace(" ", ";");
		down = down.substring(0, down.length() - 1);
		writerHanjingli.write("\"" + up + "\",\n");
		writerHanjingli.write("\"" + down + "\",\n");
		writerHanjingli2.write(line_idUp + "," + line_id + ",");

		// ��busline.txt���������·��������ʼ�յ㡢��ʼվ����Ϣ
		writerZheng.write(line_idUp + "\n");
		writerZheng.write(up + "\n");
		String stopnames[] = savedUpStopNames.split(" ");
		String stopxys[] = savedUpStopXYs.split(" ");
		for (int i = 0; i < 2 * stopxys.length; i++) {
			if (i % 2 == 0) {
				writerZheng.write(stopnames[i / 2] + ";");
			} else {
				writerZheng.write(stopxys[i / 2] + ";");
			}
		}
		writerZheng.write("\n");

		// ��busline.txt���������·��������ʼ�յ㡢��ʼվ����Ϣ
		writerZheng.write(line_id + "\n");
		writerZheng.write(down + "\n");
		String dstopnames[] = stopNames.split(" ");
		String dstopxys[] = savedDownStopXYs.split(" ");
		for (int i = 0; i < 2 * dstopxys.length; i++) {
			if (i % 2 == 0) {
				writerZheng.write(dstopnames[i / 2] + ";");
			} else {
				writerZheng.write(dstopxys[i / 2] + ";");
			}
		}
		writerZheng.write("\n");

		line_idUp = getLineName(line_idUp);// ��ȥline_id���Ų���
		line_id = getLineName(line_id);// ��ȥline_id���Ų���

		// ��stop���������վ��
		String[] stopxy;
		// System.out.println("stopnames size: " + stopnames.length);
		// System.out.println("stopxys size: " + stopxys.length);
		for (int i = 0; i < stopnames.length; i++) {
			stopxy = stopxys[i].split(",");
			GPSLocation newStopLoc = GPSLocation.parseGpsLocation(stopxys[i]);
			if (!stopLocations.contains(newStopLoc)) {
				stopLocations.add(newStopLoc);
				busSqlWriter.insertIntoStop(stopnames[i],
						String.valueOf(newStopLoc.getGPSX()),
						String.valueOf(newStopLoc.getGPSY()), writerSQL);
				writerSQL.flush();
			}
		}
		// ��stop���������վ��
		String[] dstopxy;
		for (int i = 0; i < dstopnames.length; i++) {
			dstopxy = dstopxys[i].split(",");
			GPSLocation newStopLoc = GPSLocation.parseGpsLocation(dstopxys[i]);
			if (!stopLocations.contains(newStopLoc)) {
				stopLocations.add(newStopLoc);
				busSqlWriter.insertIntoStop(dstopnames[i],
						String.valueOf(newStopLoc.getGPSX()),
						String.valueOf(newStopLoc.getGPSY()), writerSQL);
				writerSQL.flush();
			}
		}

		// ��busline�����������·
		String[] first = stopxys[0].split(",");
		firstStopX = first[0];
		firstStopY = first[1];
		if (uptotal_price.equals("")) {
			uptotal_price = "1";
		}
		busSqlWriter
				.insertIntoBusline(firstStopX, firstStopY, line_idUp, true,
						uppay_way, uptotal_price, upStartTime, upEndTime, up,
						writerSQL);
		writerSQL.flush();
		// ��busline�����������·
		String[] dfirst = dstopxys[0].split(",");
		firstStopX = dfirst[0];
		firstStopY = dfirst[1];
		if (total_price.equals("")) {
			total_price = "1";
		}
		busSqlWriter.insertIntoBusline(firstStopX, firstStopY, line_id, false,
				pay_way, total_price, downStartTime, downEndTime, down,
				writerSQL);
		writerSQL.flush();

		// ��stop_in_line���������վ�㣨��ʵվ�㣩����·�İ󶨹�ϵ
		busSqlWriter.insertIntostop_in_line(savedUpStopXYs, line_idUp, true,
				writerSQL);
		writerSQL.flush();
		// ��stop_in_line���������վ�㣨��ʵվ�㣩����·�İ󶨹�ϵ
		busSqlWriter.insertIntostop_in_line(savedDownStopXYs, line_id, false,
				writerSQL);
		writerSQL.flush();

		reader2.close();

		if (xysCounter > 2)
			System.out.println("�����ļ�" + sourcePath + "�ĸ�ʽ���ԣ�xysNum:"
					+ xysCounter);

	}

	private static String getLineName(String lineName) {
		int indexEnd = lineName.indexOf("(");
		if (indexEnd >= 0) {
			lineName = lineName.substring(0, indexEnd);
		}
		return lineName;
	}

}
