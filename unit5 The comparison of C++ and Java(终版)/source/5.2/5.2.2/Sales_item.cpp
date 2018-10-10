#include "Sales_item.h"
#include <iostream>
using std::istream; 
using std::ostream;

Sales_item& Sales_item::operator++(){

	++this->units_sold;
	this->revenue += avg_price();
	return *this;
}
Sales_item Sales_item::operator++(int){

	Sales_item s(*this);
	units_sold++;
	revenue += avg_price();
	return s;
}

Sales_item& Sales_item::operator+=(const Sales_item& rhs)
{
    if (this->same_isbn(rhs)){
		units_sold += rhs.units_sold;
	    revenue += rhs.revenue;			
	} 
	return *this;	
}

Sales_item operator+(const Sales_item& lhs, const Sales_item& rhs)
{
    if (lhs.same_isbn(rhs)){	    
		Sales_item ret(lhs);         		
	    ret += rhs; 			
		return ret; 		
    } else {
		return lhs;
	}
	          
}

istream& operator>>(istream& in, Sales_item& s)
{
    double price;
    in >> s.isbn >> s.units_sold >> price;
    // check that the inputs succeeded
    if (in)
        s.revenue = s.units_sold * price;
    else
        s = Sales_item();  // input failed: reset object to default state
    return in;
}

ostream& operator<<(ostream& out, const Sales_item& s)
{
    out << s.isbn << " " << s.units_sold << " "
        << s.revenue << " " <<  s.avg_price();
    return out;
}

double Sales_item::avg_price() const
{
    if (units_sold)
        return revenue/units_sold;
    else
        return 0;
}

