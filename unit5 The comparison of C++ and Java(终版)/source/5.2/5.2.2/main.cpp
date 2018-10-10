/*
 * main.cpp
 *
 *  Created on: 2013-10-23
 *      Author: Administrator
 */
#include <iostream>
#include <fstream>
#include <string>
#include "sales_item.h"
#include <stdlib.h>
using namespace std;
int main(){

	Sales_item saleItem1("A001",20,120.00);
	Sales_item saleItem2("A001",10,50.00);
	//演示前置++和后置++的用法
	Sales_item total1 = saleItem1++;
	Sales_item total2 = ++saleItem2;
	cout<<"saleItem1:                  "<<saleItem1<<endl;
	cout<<"total1=saleItem1++, total1: "<<total1<<endl;
	
	cout<<"saleItem2:                  "<<saleItem2<<endl;
	cout<<"total2 =++saleItem2,tota2:  "<<total2<<endl;
	
	//演示重载运算符+=的用法
	/*cout<<"saleItem1:                    "<<saleItem1<<endl;
	cout<<"saleItem2:                     "<<saleItem2<<endl;
	saleItem1 += saleItem2;
	cout<<"saleItem1+=saleItem2,saleItem1:"<<saleItem1<<endl;
	//演示从控制台输入
	Sales_item saleItem1;
	Sales_item saleItem2;
	cout<<"请输入第一个saleItem:";
	cin>>saleItem1;
	cout<<"请输入第二个saleItem:";
	cin>>saleItem2;
	cout<<"saleItem1: "<<saleItem1<<endl;
	cout<<"saleItem2: "<<saleItem2<<endl;*/
	//演示从文件输入
	/* ifstream infile("data.txt", ios::in );
	 if(!infile) {                      //如果打开失败，outfile返回0值
	       cerr<<"open error!"<<endl;
	       exit(1);
	 }
	 ofstream outfile("out.txt", ios::out);
	 if(!outfile){                    //如果打开失败，outfile返回0值
	       cerr<<"open error!"<<endl;
	       exit(1);
	 }
	Sales_item saleItem3;
	while(infile>>saleItem3){		
		outfile<<saleItem3<<endl;
		cout<<saleItem3<<endl;
	}
	infile.close();
    outfile.close();*/
	//演示重载运算符+的用法
	/*Sales_item total = saleItem1 + saleItem2;	
	cout<<"saleItem1:  "<<saleItem1<<endl;
	cout<<"saleItem2:  "<<saleItem2<<endl;	
	cout<<"total:      "<<total<<endl;	
	if (saleItem1 == saleItem2){
		cout<<"equal!"<<endl;
	}else {
		cout<<"not equal!"<<endl;
	}*/
	
	return 0;
}
