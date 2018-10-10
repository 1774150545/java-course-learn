
#ifndef SALESITEM_H
#define SALESITEM_H
#include <iostream>
#include <string>
using namespace std;

class Sales_item {
    friend  Sales_item operator+(const Sales_item&, const Sales_item&);
    friend  istream& operator>>(istream&, Sales_item&);
    friend  ostream& operator<<(ostream&, const Sales_item&);

public:
    //有参构造函数
    Sales_item(const string &book, int num, double rev):
              isbn(book), units_sold(num), revenue(rev) { }
	//无参构造函数
    Sales_item(): units_sold(0), revenue(0.0) { }
	//复制构造函数
	Sales_item(const Sales_item& s){
		 isbn = s.isbn;
		 units_sold = s.units_sold;
         revenue = s.revenue;
	}
	//基于输入流的构造函数
    Sales_item(istream &is) { is >> *this; }

public:
    //两个单目运算符重载
    Sales_item& operator++();
    Sales_item operator++(int);
	//一个双目运算符重载
	Sales_item& operator+=(const Sales_item&);

public:
    double avg_price() const;	
    bool same_isbn(const Sales_item &rhs) const
        { return isbn == rhs.isbn; }
	string getIsbn() const{
		return isbn;
	}
	unsigned getUnits_sold() const {
		return units_sold;
	}
	double getRevenue() const{
	    return revenue;
	}
private:
    string isbn;
    unsigned units_sold;
    double revenue;
};
inline bool operator==(const Sales_item &lhs,
                        const Sales_item &rhs)
{
    return lhs.getUnits_sold() == rhs.getUnits_sold() 
	       &&lhs.getRevenue() == rhs.getRevenue() 
		   &&lhs.same_isbn(rhs);
}

inline bool operator!=(const Sales_item &lhs, const Sales_item &rhs)
{
    return !(lhs == rhs); // != defined in terms of operator==
}
#endif
