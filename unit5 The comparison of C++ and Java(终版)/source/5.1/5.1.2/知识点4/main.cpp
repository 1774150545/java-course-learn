/*
 * main.cpp
 *
 *  Created on: 2013-10-23
 *      Author: Administrator
 */

#include <iostream>
#include<cstdio>
using namespace std;
int main( ){
 float grade;
  /*
  string str;
  cout << "Please enter full name: ";
  while (getline (cin,str,'c')){
	  cout << "Hello " << str << ".\n";
	  cout<<"The end."<<endl;
  }*/
 //演示基本数据类型和字符串类型数据的读入
 /*string id, evaluation;
 cout<<"enter id, grade and evalution:";
 if(cin>>id>>grade>>evaluation){//能从cin流读取数据
	  if(grade>=85) cout<<id<<" "<<grade<<" "<<evaluation<<endl;
	  if(grade<60) cout<<id<<"-"<<grade<<"-"<<evaluation<<endl;
	  cout<<"enter grade finish"<<endl;
 }*/
 
 //演示cin的不带参数的get()方法
  
 /* int c;
  cout<<"enter a sentence:"<<endl;
  while((c=cin.get())!=EOF)
	  cout.put(c);
*/
  /*char c;
   while(!cin.eof( ))//eof( )为假表示未遇到文件结束符
   if((c=cin.get( ))!=' ') {           //检查读入的字符是否为空格字符
       cout.put(c);
   }*/


 //演示cin的带1个参数的get()方法  ctrl+Z结束
  
 /* char c;
  cout<<"enter a sentence:"<<endl;
  while(cin.get(c)){ //读取一个字符赋给字符变量c，如果读取成功，cin.get(c)为真
	  cout.put(c);
  }
  cout<<"end"<<endl;
  */

   //演示cin的带3个参数的get()方法  ctrl+Z结束
 /*
    char ch[20];
    cout<<"enter a sentence:"<<endl;
    cin.get(ch,10,'\n');//指定换行符为终止字符
    cout<<ch<<endl;
*/
 //演示getline函数，读入一行字符
/*
   char ch[20];
   cin.getline(ch,20,'/');//读19个字符或遇′/′结束
   cout<<"The second part is:"<<ch<<endl;
   cin.getline(ch,20);                              //读19个字符或遇′/n′结束
   cout<<"The third part is:"<<ch<<endl;
*/
  return 0;

}
