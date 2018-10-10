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
class Date;                 //对Date类的提前引用声明
class Time {                 //定义Time类

	public:
		Time(int,int,int);
		void display(Date &);    //display是成员函数，形参是Date类对象的引用
	private:
		int hour;
		int minute;
		int sec;
};
class Date {                              //声明Date类
	public:
		Date(int,int,int);
		//friend class Time;
		friend void Time::display(Date &);    //声明Time中的display函数为友元成员函数
	 private:
		int month;
		int day;
		int year;
};

#endif /* TIME_H_ */
