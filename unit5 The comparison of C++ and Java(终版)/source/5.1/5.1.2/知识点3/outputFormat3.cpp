/*
 * outputFormat.cpp
 *
 *  Created on: 2013-10-23
 *      Author: Administrator
 */

#include <iostream>
using namespace std;
int main( ){
	 char *a="BASIC";//�ַ�ָ��ָ���B��
	 for(int i=4;i>=0;i--)
	  cout.put(*(a+i));                  //�����һ���ַ���ʼ���
	 cout.put('\n');
	 
     string b("BASIC");
	 for(int i=b.size()-1;i>=0;i--)
	  cout.put(b[i]);                  //�����һ���ַ���ʼ���
	 cout.put('\n');

	 return 0;
}

