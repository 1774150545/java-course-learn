/*
 * Official.h
 *
 *  Created on: 2013-10-28
 *      Author: Administrator
 */

#ifndef OFFICIAL_H_
#define OFFICIAL_H_

#include <string>
#include "facultyMember.h"
using namespace std;
 class Official: public FacultyMember{

    private:
    	string jobPostion;

    public:
    	Official(string initialId, string initialName,string initialTele,string initialJP);
	    string getJobPostion();
	     string toString();
	     virtual bool operator==(FacultyMember &facuty);
};


#endif /* OFFICIAL_H_ */
