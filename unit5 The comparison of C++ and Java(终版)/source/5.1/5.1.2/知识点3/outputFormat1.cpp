/*
 * outputFormat.cpp
 *
 *  Created on: 2013-10-23
 *      Author: Administrator
 */
#include <iostream>
#include <fstream>
#include <iomanip>//��Ҫ���ǰ�����ͷ�ļ�
using namespace std;
int main() {
	int a;
	cout<<"input integer a:";
	cin>>a;
	//����cout��������ָ��
	streambuf* coutBuf = cout.rdbuf();
	ofstream out("out.txt");
	//��ȡ�ļ�out�Ļ�����ָ��
	streambuf* fileBuf = out.rdbuf();
	cout.rdbuf(fileBuf);	
	//��ʮ������ʽ�������
	cout<<"dec:"<<dec<<a<<endl;  
	//��ʮ��������ʽ�������a
	cout<<"hex:"<<hex<<a<<endl; 
    //�԰˽�����ʽ�������a	 
	cout<<"oct:"<<setbase(8)<<a<<endl;
    //ptָ���ַ�����China��	 
	string pt("China");                        
	//ָ�����Ϊ10������ַ���
	cout<<setw(10)<<pt<<endl; 
    //ָ�����10������ַ������հ״��ԡ�*�����	 
	cout<<setfill('*')<<setw(10)<<pt<<endl;
    //����piֵ	
	double pi=22.0/7.0; 
    //��ָ����ʽ�����8λС��	
	cout<<"��ָ����ʽ�����8λС��:"<<setiosflags(ios::scientific)<<setprecision(8);
	//���piֵ
	cout<<"pi="<<pi<<endl; 
	//��Ϊ4λС��	
	cout<<"4λС�� pi="<<setprecision(4)<<pi<<endl;  
	//��ΪС����ʽ���	
	cout<<"cout pi="<<setiosflags(ios::fixed)<<pi<<endl;	
	
	cout.rdbuf(coutBuf);
	
		//��ʮ������ʽ�������
	cout<<"dec:"<<dec<<a<<endl;  
	//��ʮ��������ʽ�������a
	cout<<"hex:"<<hex<<a<<endl; 
    //�԰˽�����ʽ�������a	 
	cout<<"oct:"<<setbase(8)<<a<<endl;    
	//ָ�����Ϊ10������ַ���
	cout<<setw(10)<<pt<<endl; 
    //ָ�����10������ַ������հ״��ԡ�*�����	 
	cout<<setfill('*')<<setw(10)<<pt<<endl;
    //��ָ����ʽ�����8λС��	
	cout<<"��ָ����ʽ�����8λС��:"<<setiosflags(ios::scientific)<<setprecision(8);
	//���piֵ
	cout<<"pi="<<pi<<endl; 
	//��Ϊ4λС��	
	cout<<"4λС�� pi="<<setprecision(4)<<pi<<endl;  
	//��ΪС����ʽ���	
	cout<<"cout pi="<<setiosflags(ios::fixed)<<pi<<endl;	
	return 0;
}
