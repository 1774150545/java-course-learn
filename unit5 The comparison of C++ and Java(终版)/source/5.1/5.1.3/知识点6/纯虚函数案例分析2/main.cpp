/*
 * main.cpp
 *
 *  Created on: 2013-10-29
 *      Author: Administrator
 */

//main函数
#include "Circle.h"
#include "Cylinder.h"
#include "Shape.h"
int main( ){


		 Circle circle(5.6);
		 Cylinder cylinder(3.5,6.4,5.2,10.5);

		 Shape *pt;                                     //定义基类指针

		 pt=&circle;                                     //指针指向Circle类对象
		 pt->shapeName( );                                //动态关联
		 cout<<"area="<<pt->area( )
			 <<", nvolume="<<pt->volume( )<<endl;

		 pt=&cylinder;                                   //指针指向Cylinder类对象
		 pt->shapeName( );                                //动态关联
		 cout<<"area="<<pt->area( )
			 <<", volume="<<pt->volume( )<<endl;
		 return 0;
}
