/*
 * main.cpp
 *
 *  Created on: 2013-10-29
 *      Author: Administrator
 */

//main����
#include "Circle.h"
#include "Cylinder.h"
#include "Shape.h"
int main( ){


		 Circle circle(5.6);
		 Cylinder cylinder(3.5,6.4,5.2,10.5);

		 Shape *pt;                                     //�������ָ��

		 pt=&circle;                                     //ָ��ָ��Circle�����
		 pt->shapeName( );                                //��̬����
		 cout<<"area="<<pt->area( )
			 <<", nvolume="<<pt->volume( )<<endl;

		 pt=&cylinder;                                   //ָ��ָ��Cylinder�����
		 pt->shapeName( );                                //��̬����
		 cout<<"area="<<pt->area( )
			 <<", volume="<<pt->volume( )<<endl;
		 return 0;
}
