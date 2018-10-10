#include <iostream>
#include<string>
#include"Stack.h"
using namespace std;

template <class T>
bool Stack<T>::stackEmpty()
{
   return top==NULL;
}

template <class T>
void Stack<T>::push(T e)
{
   Node<T> *p=new Node<T>(e);
   if(top!=NULL)
    p->next=top;
   top=p;
}
template <class T>
bool Stack<T>::pop(T &e)
{
   Node<T> *p;
   if(top==NULL){
     return false;
   }
   p=top;
   e=p->data;
   top=p->next;
   delete p;
   return true;
}
template <class T>
Stack<T>::~Stack()
{
   if(top!=NULL)
   {
    Node<T> *p=top;
	Node<T> *q;
    while(p!=NULL)
    {
     q=p->next;
     delete p;
     p=q;
    }
    delete q;
   }
}
int main()
{
	Stack<string> stack;
	string s;
	cout<<"��ʼ��ջstack"<<endl;
	cout<<(stack.stackEmpty()?"ջ��":"ջ����")<<endl;
	cout<<"��ջԪ��I,am,very,happy"<<endl;
	stack.push("I");
	stack.push("am");
	stack.push("very");
	stack.push("happy");
	cout<<(stack.stackEmpty()?"ջ��":"ջ����")<<endl;
	stack.pop(s);
	cout<<"ջ��Ԫ�� ��"<<s<<endl;
	cout<<"ʣ�����ݳ�ջ����:";
	while(!stack.stackEmpty())
	{
	   stack.pop(s);
	   cout<<s<<endl;
	}
	cout<<endl<<"��ջ��ϣ����ͷ�ջ�ռ�"<<endl;
	return 0;
}