#include <iostream>
 using namespace std;
  // ģ�庯��ָ��Ķ��� ���Էŵ�����߽ṹ����.
  template <class T>
  struct Type
  {
     typedef void (*Action)(T);
 };
 // �����һ���޷���ֵ,��һ�� Int�����ķ���
 void FunInt (int i) {
     cout << "�������� : " << i << endl;
 }
 // �����һ���޷���ֵ,��һ�� String�����ķ���
 void FunString (string s) {
     cout << "�ַ������� : " << s << endl;
 } 
 // �˷����ĵ�һ����������ģ��ĺ���ָ��
 void Func1 (Type<int>::Action action,int i) {
     action(i);
 }
 //����ͬ��
  void Func2 (Type<string>::Action action ,string s){
     action(s);
 } 
 int main()
 {
     // ��һ������ĺ�������
     Func1(FunInt ,6678);
     Func2(FunString,"Happy"); 
     return 0;
 }