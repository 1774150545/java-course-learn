 #include <iostream>
 using namespace std;
namespace l{
 void printValues(int (&arr)[10]) { 
      for (int i=0; i<10;i++){
		cout <<*(arr+i)<<endl;
	  }
  }
}
namespace l{
  class A{
  };

}