#include <iostream> 
using namespace std;
 // Ҳ���԰Ѻ���ָ��ŵ�һ��ģ�巽����
 template <class T> void Func(void (*Action)(T),T val) {
     Action(val);
 } 
 // �����һ���޷���ֵ,��һ�� Int�����ķ���
 void FunInt (int i) {
     cout << "�������� : " << i << endl;
 }
 // �����һ���޷���ֵ,��һ�� String�����ķ���
 void FunString (string s) {
     cout << "�ַ������� : " << s << endl;
 }  
 int main()
 {
     // ģ�巽����ĺ���ָ��
     Func(FunInt,999);
     Func<string>(FunString,"happy");
     return 0;
 }