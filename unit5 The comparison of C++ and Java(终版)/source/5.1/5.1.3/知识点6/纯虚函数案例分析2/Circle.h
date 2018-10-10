/*
 * circle.h
 *
 *  Created on: 2013-10-29
 *      Author: Administrator
 */

#ifndef CIRCLE_H_
#define CIRCLE_H_
#include "Shape.h"
#include <iostream>
using namespace std;
//����Circle��
class Circle: public Shape {
	public:
	  Circle(float r=0);
	  void setRadius(float);
	  float getRadius( ) const;
	  virtual float area( ) const;
	  virtual void shapeName( ) const {cout<<"Circle:";}//���麯�������ٶ���
	  //friend ostream &operator<<(ostream &,const Circle &);
   protected:
	  float radius;
};

#endif /* CIRCLE_H_ */
