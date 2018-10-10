
#include <iostream>
using namespace std; 
int count = 0;
class Base   
{
 public:  
	Base(){cout << "Base called : " << count++ << endl;}  
	void print(){
	cout << "Base print" <<endl;}
}; 

class Mid1 : virtual public Base  
{ 
 public:  
	Mid1(){cout << "Mid1 called" << endl;} 
 private:  
}; 

class Mid2 : virtual public Base 
 { 
 public:
	Mid2(){cout << "Mid2 called" << endl;} 
 }; 
 class Child:public Mid1, public Mid2 
 { 
 public:  
	Child(){cout << "Child called" << endl;} 
 }; 
 
 int main()  
 { 
	 Child d;
	 d.print(); 
	 d.Mid1::print(); 
	 d.Mid2::print(); 

	 return 0; 
 } 
 