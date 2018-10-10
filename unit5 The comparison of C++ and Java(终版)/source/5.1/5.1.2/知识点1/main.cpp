/*
 * main.cpp
 *
 *  Created on: 2013-10-23
 *      Author: Administrator
 */
    #include "DNASequence.h"
    #include <iostream>
    using namespace std;
    int main(){
		string s("ATTATCGGGGTAA");
		string s1;
		string s2(10,'A');
		string s3(s);
		string* s4 = new string;
		string* s5 = new string("ATTATCGGGGTAA");
		string* s6 = new string(s);
		string* s7 = new string(10,'A');
		DNASequence dnaSequence(*s5);		
		if (dnaSequence.getNumberOfA() == 4) {
			cout<<"Test case 1:  get methods passed\n"<<endl;
		} else {
			cout<<"Test case 1:  get methods failed !!!\n"<<endl;
		}
		if (dnaSequence.twoConsecutive('A')) {
			cout<<"Test case 3: method twoConsecutive passed\n"<<endl;
		} else {

			cout<<"Test case 3: method twoConsecutive failed !!!\n"<<endl;
		}
		
		if (s2==*s7) {
		    cout<<"s2 is equal to *s7"<<endl;			
		} else {
		    cout<<"s2 is not equal to *s7"<<endl;
		}
		
		string s9("ATTB");
		string s10("C");
		string s11=s9+s10;
		if (s9<s10) {
		    cout<<"s9 < s10"<<endl;			
		} else {
		    cout<<"!s9<s10"<<endl;
		}
		cout<<"s11:"<<s11<<endl;
		
  		return 0;
	}

