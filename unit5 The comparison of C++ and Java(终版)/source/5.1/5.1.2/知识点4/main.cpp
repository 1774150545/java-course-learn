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
 //��ʾ�����������ͺ��ַ����������ݵĶ���
 /*string id, evaluation;
 cout<<"enter id, grade and evalution:";
 if(cin>>id>>grade>>evaluation){//�ܴ�cin����ȡ����
	  if(grade>=85) cout<<id<<" "<<grade<<" "<<evaluation<<endl;
	  if(grade<60) cout<<id<<"-"<<grade<<"-"<<evaluation<<endl;
	  cout<<"enter grade finish"<<endl;
 }*/
 
 //��ʾcin�Ĳ���������get()����
  
 /* int c;
  cout<<"enter a sentence:"<<endl;
  while((c=cin.get())!=EOF)
	  cout.put(c);
*/
  /*char c;
   while(!cin.eof( ))//eof( )Ϊ�ٱ�ʾδ�����ļ�������
   if((c=cin.get( ))!=' ') {           //��������ַ��Ƿ�Ϊ�ո��ַ�
       cout.put(c);
   }*/


 //��ʾcin�Ĵ�1��������get()����  ctrl+Z����
  
 /* char c;
  cout<<"enter a sentence:"<<endl;
  while(cin.get(c)){ //��ȡһ���ַ������ַ�����c�������ȡ�ɹ���cin.get(c)Ϊ��
	  cout.put(c);
  }
  cout<<"end"<<endl;
  */

   //��ʾcin�Ĵ�3��������get()����  ctrl+Z����
 /*
    char ch[20];
    cout<<"enter a sentence:"<<endl;
    cin.get(ch,10,'\n');//ָ�����з�Ϊ��ֹ�ַ�
    cout<<ch<<endl;
*/
 //��ʾgetline����������һ���ַ�
/*
   char ch[20];
   cin.getline(ch,20,'/');//��19���ַ�������/�����
   cout<<"The second part is:"<<ch<<endl;
   cin.getline(ch,20);                              //��19���ַ�������/n�����
   cout<<"The third part is:"<<ch<<endl;
*/
  return 0;

}
