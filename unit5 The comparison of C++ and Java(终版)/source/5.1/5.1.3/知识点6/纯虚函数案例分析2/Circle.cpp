/*
 * Circle.cpp
 *
 *  Created on: 2013-10-29
 *      Author: Administrator
 */

#include "Circle.h"
using namespace std;

	//����Circle���Ա����
	Circle::Circle(float r):radius(r){ }

	void Circle::setRadius(float r){

		radius = r;
	}

	float Circle::getRadius( ) const {return radius;}

	float Circle::area( ) const {return 3.14159*radius*radius;}


