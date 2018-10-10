/*
 * Cylinder.cpp
 *
 *  Created on: 2013-10-29
 *      Author: Administrator
 */

//����Cylinder���Ա����
#include "Cylinder.h"

		Cylinder::Cylinder(float a,float b,float r,float h):x(a),y(b),radius(r),height(h){

		}

		void Cylinder::setHeight(float h){height=h;}

		float Cylinder::area( ) const
		{ return 2*3.14159*radius*radius+2*3.14159*radius*height;}

		float Cylinder::volume( ) const{

			return 3.14159*radius*radius*height;
		}
		/*ostream &operator<<(ostream &output,const Cylinder& cy){
			output<<"r=" << cy.radius<<", h=" << cy.height<<endl;
			return output;
		}*/
