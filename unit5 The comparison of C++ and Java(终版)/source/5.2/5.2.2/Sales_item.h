
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
    //�вι��캯��
    Sales_item(const string &book, int num, double rev):
              isbn(book), units_sold(num), revenue(rev) { }
	//�޲ι��캯��
    Sales_item(): units_sold(0), revenue(0.0) { }
	//���ƹ��캯��
	Sales_item(const Sales_item& s){
		 isbn = s.isbn;
		 units_sold = s.units_sold;
         revenue = s.revenue;
	}
	//�����������Ĺ��캯��
    Sales_item(istream &is) { is >> *this; }

public:
    //������Ŀ���������
    Sales_item& operator++();
    Sales_item operator++(int);
	//һ��˫Ŀ���������
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
