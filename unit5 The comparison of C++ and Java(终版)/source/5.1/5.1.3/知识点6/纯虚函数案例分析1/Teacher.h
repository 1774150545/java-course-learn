/*
 * Teacher.h
 *
 *  Created on: 2013-10-25
 *      Author: Administrator
 */

#ifndef TEACHER_H_
#define TEACHER_H_
#include <string>
#include "FacultyMember.h"
#include "BankAccount.h"
using namespace std;
class Teacher:public FacultyMember{

private:
	string title;
	BankAccount account;

public:
	 Teacher(string& initialId, string& initialName, string& initialTele,string& initialTitle,BankAccount& account);
	 Teacher(string& initialId, string& initialName, string& initialTele,string& initialTitle);
	 string getTitle();
     string toString();
     ~Teacher(){};
     Teacher(FacultyMember& f){
    	 string title = "lecture";
    	 string id = f.getIdentification();
    	 string name = f.getName();
    	 string tele = f.getContactTelephone();
    	 // Teacher(id,name,tele,title);
     }
     virtual bool operator==(FacultyMember &facuty);
};


#endif /* TEACHER_H_ */
