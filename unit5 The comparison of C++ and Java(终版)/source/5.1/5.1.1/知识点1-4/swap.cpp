#include <iostream>
using std::cout;
using std::endl;  
  
  /*1void swap(int v1, int v2)      {
          int tmp = v2;          
          v2 = v1;          
          v1 = tmp;      
  }*/
  /*2
  void swap(int* v1, int* v2)      {
          int tmp = *v2;          
          *v2 = *v1;          
          *v1 = tmp;      
  }*/
  void swap(int* v1, int* v2)      {
          int *tmp = v2;          
          v2 = v1;          
          v1 = tmp;      
  }

  
  /*3
  void swap(int& v1, int& v2)      {
          int tmp = v2;          
          v2 = v1;          
          v1 = tmp;      
 }*/
 
 
 
 /*
 void swap(int *&v1, int *&v2)      
{   
        int *tmp = v2;          
	v2 = v1;          
	v1 = tmp;  
    
         int tmp = *v2;          
          *v2 = *v1;          
          *v1 = tmp;        
}*/

 
 
 

  int main(){
     /*1 int x=0;
	  int y=10;
	  swap(x,y);
	  cout<<"x:"<<x<<endl;
  	  cout<<"y:"<<y<<endl;*/
	 /*2
	 int x0 = 0;
	 int y0 = 10;
	 int* x=&x0;
	 int* y=&y0;
	 cout<<"pre x:"<<x<<endl;
  	 cout<<"pre y:"<<y<<endl;
	 cout<<"pre x:"<<*x<<endl;
  	 cout<<"pre y:"<<*y<<endl;
	 swap(x,y);
	 cout<<"x:"<<x<<endl;
  	 cout<<"y:"<<y<<endl;
	 cout<<"x:"<<*x<<endl;
  	 cout<<"y:"<<*y<<endl;*/
	 /*3int x=0;
	  int y=10;
	  swap(x,y);
	  cout<<"x:"<<x<<endl;
  	  cout<<"y:"<<y<<endl;*/
	 int x0 = 0;
	 int y0 = 10;
	 int* x=&x0;
	 int* y=&y0;
	 cout<<"pre x:"<<x<<endl;
  	 cout<<"pre y:"<<y<<endl;
	 cout<<"pre x:"<<*x<<endl;
  	 cout<<"pre y:"<<*y<<endl;
	 swap(x,y);
	 cout<<"x:"<<x<<endl;
  	 cout<<"y:"<<y<<endl;
	 cout<<"x:"<<*x<<endl;
  	 cout<<"y:"<<*y<<endl;	 
	  return 0;
  }