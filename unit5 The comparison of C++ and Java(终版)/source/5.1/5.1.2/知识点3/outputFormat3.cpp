/*
 * outputFormat.cpp
 *
 *  Created on: 2013-10-23
 *      Author: Administrator
 */

#include <iostream>
using namespace std;
int main( ){
	 char *a="BASIC";//字符指针指向′B′
	 for(int i=4;i>=0;i--)
	  cout.put(*(a+i));                  //从最后一个字符开始输出
	 cout.put('\n');
	 
     string b("BASIC");
	 for(int i=b.size()-1;i>=0;i--)
	  cout.put(b[i]);                  //从最后一个字符开始输出
	 cout.put('\n');

	 return 0;
}

