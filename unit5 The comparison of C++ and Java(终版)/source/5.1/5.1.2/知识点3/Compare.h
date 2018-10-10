/*
 * Compare.h
 *
 *  Created on: 2013-10-22
 *      Author: Administrator
 */

#ifndef COMPARE_H_
#define COMPARE_H_
template<class numtype>         //声明一个模板，虚拟类型名为numtype
class Compare                           //类模板名为Compare
{
public:
	Compare(numtype a,numtype b) {x=a;y=b;}
	numtype max( ) {return (x>y)?x:y;}
	numtype min( ) {return (x<y)?x:y;}
 private:
	 numtype x,y;
};


#endif /* COMPARE_H_ */
