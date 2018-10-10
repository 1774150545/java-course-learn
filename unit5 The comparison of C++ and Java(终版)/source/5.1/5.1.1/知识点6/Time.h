/*
 * Time.h
 *
 *  Created on: 2013-10-22
 *      Author: Administrator
 */

#ifndef TIME_H_
#define TIME_H_
#include <iostream>
using namespace std;
class Date;                 //��Date�����ǰ��������
class Time {                 //����Time��

	public:
		Time(int,int,int);
		void display(Date &);    //display�ǳ�Ա�������β���Date����������
	private:
		int hour;
		int minute;
		int sec;
};
class Date {                              //����Date��
	public:
		Date(int,int,int);
		//friend class Time;
		friend void Time::display(Date &);    //����Time�е�display����Ϊ��Ԫ��Ա����
	 private:
		int month;
		int day;
		int year;
};

#endif /* TIME_H_ */
