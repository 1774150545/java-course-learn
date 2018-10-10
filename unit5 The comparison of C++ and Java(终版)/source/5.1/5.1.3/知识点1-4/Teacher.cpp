/*
 * Teacher.cpp
 *
 *  Created on: 2013-10-25
 *      Author: Administrator
 */
    #include "Teacher.h"
	Teacher::Teacher(string& initialId, string& initialName,string& initialTele,string& initialTitle, BankAccount& initialAccount):
		                                   FacultyMember(initialId,initialName,initialTele),account(initialAccount),title(initialTitle){

	}
	Teacher::Teacher(string& initialId, string& initialName,string& initialTele,string& initialTitle):
			                                   FacultyMember(initialId,initialName,initialTele),title(initialTitle){

		}
	string Teacher::getTitle(){
		return title;
	}
    string Teacher::toString(){

		return "Teacher--------"+FacultyMember::toString()+" title:"+title;
	}
