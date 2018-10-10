/*
 * Experimenter.cpp
 *
 *  Created on: 2013-10-28
 *      Author: Administrator
 */
   #include "Experimenter.h"
   using namespace std;
   Experimenter::Experimenter(string initialId, string initialName,string initialTele,string initialDegree):FacultyMember(initialId, initialName,initialTele){

		degree = initialDegree;
	}
	string Experimenter::getDegree(){

		return degree;
	}
	string Experimenter::toString(){

		return "Experimenter---"+FacultyMember::toString()+" degree:"+degree;
	}
