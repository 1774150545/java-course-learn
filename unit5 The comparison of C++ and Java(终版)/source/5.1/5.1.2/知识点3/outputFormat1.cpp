/*
 * outputFormat.cpp
 *
 *  Created on: 2013-10-23
 *      Author: Administrator
 */
#include <iostream>
#include <fstream>
#include <iomanip>//不要忘记包含此头文件
using namespace std;
int main() {
	int a;
	cout<<"input integer a:";
	cin>>a;
	//保存cout流缓冲区指针
	streambuf* coutBuf = cout.rdbuf();
	ofstream out("out.txt");
	//获取文件out的缓冲区指针
	streambuf* fileBuf = out.rdbuf();
	cout.rdbuf(fileBuf);	
	//以十进制形式输出整数
	cout<<"dec:"<<dec<<a<<endl;  
	//以十六进制形式输出整数a
	cout<<"hex:"<<hex<<a<<endl; 
    //以八进制形式输出整数a	 
	cout<<"oct:"<<setbase(8)<<a<<endl;
    //pt指向字符串″China″	 
	string pt("China");                        
	//指定域宽为10，输出字符串
	cout<<setw(10)<<pt<<endl; 
    //指定域宽10，输出字符串，空白处以′*′填充	 
	cout<<setfill('*')<<setw(10)<<pt<<endl;
    //计算pi值	
	double pi=22.0/7.0; 
    //按指数形式输出，8位小数	
	cout<<"按指数形式输出，8位小数:"<<setiosflags(ios::scientific)<<setprecision(8);
	//输出pi值
	cout<<"pi="<<pi<<endl; 
	//改为4位小数	
	cout<<"4位小数 pi="<<setprecision(4)<<pi<<endl;  
	//改为小数形式输出	
	cout<<"cout pi="<<setiosflags(ios::fixed)<<pi<<endl;	
	
	cout.rdbuf(coutBuf);
	
		//以十进制形式输出整数
	cout<<"dec:"<<dec<<a<<endl;  
	//以十六进制形式输出整数a
	cout<<"hex:"<<hex<<a<<endl; 
    //以八进制形式输出整数a	 
	cout<<"oct:"<<setbase(8)<<a<<endl;    
	//指定域宽为10，输出字符串
	cout<<setw(10)<<pt<<endl; 
    //指定域宽10，输出字符串，空白处以′*′填充	 
	cout<<setfill('*')<<setw(10)<<pt<<endl;
    //按指数形式输出，8位小数	
	cout<<"按指数形式输出，8位小数:"<<setiosflags(ios::scientific)<<setprecision(8);
	//输出pi值
	cout<<"pi="<<pi<<endl; 
	//改为4位小数	
	cout<<"4位小数 pi="<<setprecision(4)<<pi<<endl;  
	//改为小数形式输出	
	cout<<"cout pi="<<setiosflags(ios::fixed)<<pi<<endl;	
	return 0;
}
