#include <fstream>
#include <iostream>
#include <stdlib.h>

using namespace std;

int main(){


	 ifstream infile("copy.cpp",ios::in );
	 if(!infile) {                      //�����ʧ�ܣ�outfile����0ֵ
	       cerr<<"open error!"<<endl;
	       exit(1);
	 }
	 ofstream outfile("b.txt",ios::out);
	 if(!outfile){                    //�����ʧ�ܣ�outfile����0ֵ
	       cerr<<"open error!"<<endl;
	       exit(1);
	 }
   /* 
    char a;
    while(infile>>a){

    	outfile<<a;
    }*/
/*
char c[2000];
infile.getline(c,2000,EOF); 
outfile<<c;
*/

    //int length;
	streampos length;
    char * buffer;
    // get length of file:
    infile.seekg (0, ios::end);// ���ļ�ָ��ָ��ĩβ����һ��������ƫ�������ڶ��������ǻ�
    length = infile.tellg();
    infile.seekg (0, ios::beg);

    // allocate memory:
    buffer = new char [length];

    // read data as a block:
    infile.read (buffer,length);
    outfile.write (buffer,length);

    infile.close();
    outfile.close();
    delete[] buffer;
	
	// position of put pointer
  int  pos;
  outfile.open ("test.txt");
  outfile.write ("This is an apple",16);
  pos=outfile.tellp();
  outfile.seekp (pos-7);
  outfile.write (" sam",4);//This is a sample
  outfile.close();
	return 0;
}
