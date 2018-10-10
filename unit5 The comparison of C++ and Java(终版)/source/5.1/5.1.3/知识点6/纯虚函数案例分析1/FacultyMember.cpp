/*
 * FacultyMember.cpp
 *
 *  Created on: 2013-10-25
 *      Author: Administrator
 */
#include "FacultyMember.h"
using namespace std;

    FacultyMember:: FacultyMember(string& initialId, string& initialName,string& initialTele ):
				identification(initialId),name(initialName),contactTelephone(initialTele){}
    FacultyMember::FacultyMember(){}
	string FacultyMember:: getIdentification(){
		return identification;
	}

	string FacultyMember:: getName(){
		return name;
	}

	string FacultyMember:: getContactTelephone(){
		return contactTelephone;
	}
	string FacultyMember:: toString(){

		return "identification: "+identification+" name: "+name+
		 	" contactTelephone: "+contactTelephone;
	}

	/*
	bool FacultyMember:: operator==(FacultyMember &facuty){

		return this->identification==facuty.getIdentification()&&
			       this->name==facuty.getName()&&
			       this->contactTelephone==facuty.getContactTelephone();
	}*/

