#include <iostream>
#include <string>
#include <cstddef>
using namespace std;
/* 
template <class T, size_t N>
size_t size( T (&arr)[N] )   
{
    return N;
}*/

template <class T>
void printValues2(T*  arr)  
{
    int size =sizeof(arr)/sizeof(arr[0]);
	cout<<"size="<<size<<endl;	
    for ( int i = 0; i !=size; ++i )
    {
         cout << "arr["<<i<<"]="<<arr[ i ] << endl;
    }
}
template <class T>
void printValues3(T arr[4])
{
    int size =sizeof(arr)/sizeof(arr[0]);
	cout<<"size="<<size<<endl;	
    for ( int i = 0; i != size; ++i )
    {
         cout << "arr["<<i<<"]="<<arr[ i ] << endl;
    }
}

template <class T>
void printValues1(T arr[])  //int size
{
    int size =sizeof(arr)/sizeof(arr[0]);
	cout<<"size="<<size<<endl;
    for ( int i = 0; i !=size; ++i )
    {
        cout << "arr["<<i<<"]="<<arr[ i ] << endl;
    }
}
 
template <class T, size_t N>
void printValues4( const T (&arr)[N] ) 
{
    int size =sizeof(arr)/sizeof(arr[0]);
	cout<<"size="<<size<<endl;
	for ( size_t i = 0; i != N; ++i )
    {
        cout << arr[ i ] << endl;
    }
}
 
int main()
{
    int a[] = { 1, 2, 3, 4 };
	cout << "printValues1" << endl;
    printValues1(a); 
	cout << "printValues2" << endl;
	printValues2(a); 
	cout << "printValues3" << endl;
	printValues3(a); 
	cout << "printValues4" << endl;
	printValues4(a); 
    return 0;
}