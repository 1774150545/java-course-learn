#include <iostream>
 using namespace std;
  // 模板函数指针的定义 可以放到类或者结构体里.
  template <class T>
  struct Type
  {
     typedef void (*Action)(T);
 };
 // 具体的一个无返回值,有一个 Int参数的方法
 void FunInt (int i) {
     cout << "整型数据 : " << i << endl;
 }
 // 具体的一个无返回值,有一个 String参数的方法
 void FunString (string s) {
     cout << "字符串数据 : " << s << endl;
 } 
 // 此方法的第一个参数调用模板的函数指针
 void Func1 (Type<int>::Action action,int i) {
     action(i);
 }
 //与上同理
  void Func2 (Type<string>::Action action ,string s){
     action(s);
 } 
 int main()
 {
     // 将一个具体的函数传入
     Func1(FunInt ,6678);
     Func2(FunString,"Happy"); 
     return 0;
 }