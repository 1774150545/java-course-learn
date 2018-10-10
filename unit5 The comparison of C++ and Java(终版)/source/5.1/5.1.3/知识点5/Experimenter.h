/*
 * Experimenter.h
 *
 *  Created on: 2013-10-28
 *      Author: Administrator
 */

#ifndef EXPERIMENTER_H_
#define EXPERIMENTER_H_

#include <string>
#include "facultyMember.h"
using namespace std;
class Experimenter : public FacultyMember{

private:
	string degree;

public:
	Experimenter(string initialId, string initialName,string initialTele,string initialDegree);
	string getDegree();
	string toString();
};





#endif /* EXPERIMENTER_H_ */
