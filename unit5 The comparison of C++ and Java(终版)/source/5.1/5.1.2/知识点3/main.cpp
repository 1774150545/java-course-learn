/*
 * main.cpp
 *
 *  Created on: 2013-10-22
 *      Author: Administrator
 */
#include <iostream>
#include "Compare.h"
using namespace std;
template<typename T>        //模板声明，其中T为类型参数
T max(T a,T b,T c)          //定义一个通用函数，用T作虚拟的类型名
{
 if(b>a) a=b;
 if(c>a) a=c;
 return a;
}
int main( ){
	//演示模板类的应用
	Compare<int> cmp1(3,7);            //定义对象cmp1，用于两个整数的比较
	cout<<cmp1.max( )<<" is the Maximum of two integer numbers."<<endl;
	cout<<cmp1.min( )<<" is the Minimum of two integer numbers."<<endl<<endl;
	Compare<float> cmp2(45.78,93.6);   //定义对象cmp2，用于两个浮点数的比较
	cout<<cmp2.max( )<<" is the Maximum of two float numbers."<<endl;
	cout<<cmp2.min( )<<" is the Minimum of two float numbers."<<endl<<endl;
	Compare<char> cmp3('a','A');       //定义对象cmp3，用于两个字符的比较
	cout<<cmp3.max( )<<" is the Maximum of two characters."<<endl;
	cout<<cmp3.min( )<<" is the Minimum of two characters."<<endl;
	//演示模板函数的应用
	int i1=185,i2=-76,i3=567,i;
	double d1=56.87,d2=90.23,d3=-3214.78,d;
	long g1=67854,g2=-912456,g3=673456,g;
	i=max(i1,i2,i3);          //调用模板函数，此时T被int取代
	d=max(d1,d2,d3);          //调用模板函数，此时T被double取代
	g=max(g1,g2,g3);          //调用模板函数，此时T被long取代
	cout<<"i_max="<<i<<endl;
	cout<<"f_max="<<d<<endl;
	cout<<"g_max="<<g<<endl;
	return 0;
}

