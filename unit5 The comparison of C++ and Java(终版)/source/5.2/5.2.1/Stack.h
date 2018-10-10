template <class T>
class Stack;
template <class T>
class Node
{
	T data;
	Node *next;
public:
	Node (){}
	Node (T d):data(d),next(NULL){};
	friend class Stack<T>;
};

template <class T>
class Stack
{
	Node<T> *top;
public:
    Stack(){
	   top=NULL;
	}
	bool stackEmpty();
	void push(T e);
	bool pop(T &e);
	~Stack();
};
