/*
 * FacultyMain.cpp
 *
 *  Created on: 2013-10-28
 *      Author: Administrator
 */

#include <string>
#include <iostream>
#include "facultyMember.h"
#include "teacher.h"
#include "official.h"
#include "facultyMember.h"
#include "experimenter.h""
#include <ctime>
#include <cstdlib>

using namespace std;

    string ids[] = {"A001","A002","A003","B001",
	   "B002","B003", "C001","C002",
	   "C003"};
	string teles[] = {
		"13791282722","13491282722","13951282722",
		"13991252722",
		"13341282722","123991282762","13771282722",
		"13991282799","13991432722"
	};

	string names[] = {
		"Chocolate", "Strawberry",
		"Vanilla Fudge Swirl", "Mint Chip",
		"Mocha Almond Fudge", "Rum Raisin",
		"Praline Cream", "Mud Pie","apple" };
	string titles[] = {	"professor", "viceProfessor","lecture"};
	string jobPositions[] = {"Job1","Job2","Job3"	};
	string degrees[] = {		"undergraduate","master","doctor"};

	FacultyMember* randMember() {
        srand((int)time(0));       //seed
		int index1 = rand() % 9;       // random number 1-10
		int index2 = rand() % 3;

		switch((rand() % 3)) {
			default:
			case 0: return  new Teacher(ids[index1], names[index1], teles[index1],titles[index2]);

			case 1: return  new Official(ids[index1],names[index1], teles[index1],jobPositions[index2]);
			case 2: return  new Experimenter(ids[index1],names[index1], teles[index1],degrees[index2]);
			//case 3: return  new FacultyMember(ids[index1],names[index1], teles[index1]);
	  	}
	}

	/**
	 * Demonstrates the use of polymorphism in the container
	 * classes.
	 *
	 * @param args  not used.
	 */
	int main() {

		FacultyMember* members[35];

		for (int index=0; index<35; index++){
			members[index] = randMember();
		}


		for (int index=0; index<35; index++){
			cout<<members[index]->toString()<<endl;

		}

        return 0;
	}

