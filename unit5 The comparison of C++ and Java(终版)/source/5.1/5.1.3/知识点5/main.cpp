/*
 * main.cpp
 *
 *  Created on: 2013-10-29
 *      Author: Administrator
 */

#include <iostream>
using namespace std;
class Point {
public:
    Point( ){ }                                                   //Point类构造函数
   //~Point(){cout<<this<<"executing Point destructor"<<endl;}//Point类析构函数
    virtual ~Point(){cout<<this<<"executing Point destructor"<<endl;}//Point类析构函数
};

class Circle:public Point {
public:
   Circle( ){ }                                                 //Circle类构造函数
   ~Circle( ){cout<<this<<"executing Circle destructor"<<endl;}//Circle类析构函数
 private:
  int radius;
};

int main( ){
   Point *p=new Circle;                             //用new开辟动态存储空间
   delete p;                                        //用delete释放动态存储空间
   //Point p1;
   Circle p2;
  // Circle p3;
   return 0;
}
