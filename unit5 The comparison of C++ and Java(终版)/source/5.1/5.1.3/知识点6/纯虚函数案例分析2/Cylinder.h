/*
 * Cylinder.h
 *
 *  Created on: 2013-10-29
 *      Author: Administrator
 */

#ifndef CYLINDER_H_
#define CYLINDER_H_

#include "Shape.h"
#include <iostream>
class Cylinder:public Shape
{public:
		Cylinder (float x=0,float y=0,float r=0,float h=0);
		void setHeight(float);
		virtual float area( ) const;
		virtual float volume( ) const;
		virtual void shapeName( ) const {
			cout<<"Cylinder:";
		}//对虚函数进行再定义
		//friend ostream& operator<<(ostream&,const Cylinder&);
   protected:
		float height;
		float x;
		float y;
		float radius;
  };

#endif /* CYLINDER_H_ */
