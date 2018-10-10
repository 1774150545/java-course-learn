#include <iostream> 
using namespace std;
 // 也可以把函数指针放到一个模板方法里
 template <class T> void Func(void (*Action)(T),T val) {
     Action(val);
 } 
 // 具体的一个无返回值,有一个 Int参数的方法
 void FunInt (int i) {
     cout << "整型数据 : " << i << endl;
 }
 // 具体的一个无返回值,有一个 String参数的方法
 void FunString (string s) {
     cout << "字符串数据 : " << s << endl;
 }  
 int main()
 {
     // 模板方法里的函数指针
     Func(FunInt,999);
     Func<string>(FunString,"happy");
     return 0;
 }