/*
 * Time.cpp
 *
 *  Created on: 2013-10-22
 *      Author: Administrator
 */
#include "Time.h"
Time::Time(int h,int m,int s){    //类Time的构造函数
	hour=h;
	minute=m;
	sec=s;
}
void Time::display(Date &d){   //display的作用是输出年、月、日和时、分、秒
	cout<<d.month<<"/"<<d.day<<"/"<<d.year<<endl; //引用Date类对象中的私有数据
	cout<<hour<<":"<<minute<<":"<<sec<<endl;   //引用本类对象中的私有数据
}
Date::Date(int m,int d,int y){  //类Date的构造函数
	month=m;
	day=d;
	year=y;
}
int main(){
	Time t1(10,13,56);       //定义Time类对象t1
	Date d1(01,01,2014);     //定义Date类对象d1
	t1.display(d1);         //调用t1中的display函数，实参是Date类对象d1
	return 0;
}

