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
    Point( ){ }                                                   //Point�๹�캯��
   //~Point(){cout<<this<<"executing Point destructor"<<endl;}//Point����������
    virtual ~Point(){cout<<this<<"executing Point destructor"<<endl;}//Point����������
};

class Circle:public Point {
public:
   Circle( ){ }                                                 //Circle�๹�캯��
   ~Circle( ){cout<<this<<"executing Circle destructor"<<endl;}//Circle����������
 private:
  int radius;
};

int main( ){
   Point *p=new Circle;                             //��new���ٶ�̬�洢�ռ�
   delete p;                                        //��delete�ͷŶ�̬�洢�ռ�
   //Point p1;
   Circle p2;
  // Circle p3;
   return 0;
}
