/*
 * main.cpp
 *
 *  Created on: 2013-10-22
 *      Author: Administrator
 */
#include <iostream>
#include "Compare.h"
using namespace std;
template<typename T>        //ģ������������TΪ���Ͳ���
T max(T a,T b,T c)          //����һ��ͨ�ú�������T�������������
{
 if(b>a) a=b;
 if(c>a) a=c;
 return a;
}
int main( ){
	//��ʾģ�����Ӧ��
	Compare<int> cmp1(3,7);            //�������cmp1���������������ıȽ�
	cout<<cmp1.max( )<<" is the Maximum of two integer numbers."<<endl;
	cout<<cmp1.min( )<<" is the Minimum of two integer numbers."<<endl<<endl;
	Compare<float> cmp2(45.78,93.6);   //�������cmp2�����������������ıȽ�
	cout<<cmp2.max( )<<" is the Maximum of two float numbers."<<endl;
	cout<<cmp2.min( )<<" is the Minimum of two float numbers."<<endl<<endl;
	Compare<char> cmp3('a','A');       //�������cmp3�����������ַ��ıȽ�
	cout<<cmp3.max( )<<" is the Maximum of two characters."<<endl;
	cout<<cmp3.min( )<<" is the Minimum of two characters."<<endl;
	//��ʾģ�庯����Ӧ��
	int i1=185,i2=-76,i3=567,i;
	double d1=56.87,d2=90.23,d3=-3214.78,d;
	long g1=67854,g2=-912456,g3=673456,g;
	i=max(i1,i2,i3);          //����ģ�庯������ʱT��intȡ��
	d=max(d1,d2,d3);          //����ģ�庯������ʱT��doubleȡ��
	g=max(g1,g2,g3);          //����ģ�庯������ʱT��longȡ��
	cout<<"i_max="<<i<<endl;
	cout<<"f_max="<<d<<endl;
	cout<<"g_max="<<g<<endl;
	return 0;
}

