/*
 * Official.cpp
 *
 *  Created on: 2013-10-28
 *      Author: Administrator
 */

    #include <string>
    #include "Official.h"
    using namespace std;


	Official::Official(string initialId, string initialName,string initialTele,string initialJP):FacultyMember(initialId, initialName,initialTele){
		jobPostion = initialJP;
	}
	string Official::getJobPostion(){

		return jobPostion;
	}
	string Official::toString(){

		return "Official--------"+FacultyMember::toString()+" jobPostion:"+jobPostion;
	}

	 bool Official:: operator==(FacultyMember &faculty){

				return this->getIdentification()==faculty.getIdentification();
	 }
