/*
 * Time.cpp
 *
 *  Created on: 2013-10-22
 *      Author: Administrator
 */
#include "Time.h"
Time::Time(int h,int m,int s){    //��Time�Ĺ��캯��
	hour=h;
	minute=m;
	sec=s;
}
void Time::display(Date &d){   //display������������ꡢ�¡��պ�ʱ���֡���
	cout<<d.month<<"/"<<d.day<<"/"<<d.year<<endl; //����Date������е�˽������
	cout<<hour<<":"<<minute<<":"<<sec<<endl;   //���ñ�������е�˽������
}
Date::Date(int m,int d,int y){  //��Date�Ĺ��캯��
	month=m;
	day=d;
	year=y;
}
int main(){
	Time t1(10,13,56);       //����Time�����t1
	Date d1(01,01,2014);     //����Date�����d1
	t1.display(d1);         //����t1�е�display������ʵ����Date�����d1
	return 0;
}

