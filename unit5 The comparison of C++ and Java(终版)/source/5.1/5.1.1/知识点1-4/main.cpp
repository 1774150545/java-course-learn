#include <iostream>
#include "point2D.h"
using std::cout;
using std::endl;
  int main(){
    //与ppt中构造函数演示相对应
      Point2D  pointOne(30,30);//1,2,3
  	  Point2D*  pointTwo =&pointOne;
	  Point2D  &pointThree = pointOne;
	  Point2D* &pointFour = pointTwo;
	  Point2D  *pointFive = new Point2D;
	  float x1 = pointOne.getX();
	  float y1 = pointOne.getY();
	  float x2 = pointTwo->getX();
	  float y2 = pointTwo->getY();
	  float x3 = pointThree.getX();
	  float y3 = pointThree.getY();
	  float x4 = pointFour->getX();
	  float y4 = pointFour->getY();
	  float x5 = pointFive->getX();
	  float y5 = pointFive->getY();

	  cout<<"x1:"<<x1<<endl;
	  cout<<"y1:"<<y1<<endl;
	  cout<<"x2:"<<x2<<endl;
	  cout<<"y2:"<<y2<<endl;
	  cout<<"x3:"<<x3<<endl;
	  cout<<"y3:"<<y3<<endl;
	  cout<<"x4:"<<x4<<endl;
	  cout<<"y4:"<<y4<<endl;
	  cout<<"x5:"<<x5<<endl;
	  cout<<"y5:"<<y5<<endl;
	  delete pointFive;  

	  return 0;
  }
