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

	FacultyMember randMember() {

		srand(time(0));       //seed
		int index1 = rand() % 9;       // random number 1-10
		int index2 = rand() % 3;

		switch((rand() % 3)) {
			default:
			case 0: return  Teacher(ids[index1], names[index1], teles[index1],titles[index2]);

			case 1: return  Official(ids[index1],names[index1], teles[index1],jobPositions[index2]);
			case 2: return  Experimenter(ids[index1],names[index1], teles[index1],degrees[index2]);
			case 3: return  FacultyMember(ids[index1],names[index1], teles[index1]);
	  	}
	}

	/**
	 * Demonstrates the use of polymorphism in the container
	 * classes.
	 *
	 * @param args  not used.
	 */
	int main() {

		FacultyMember members[35];
       /*数组定义中的类型名可以是内置数据类型或类类型；
		除引用之外，数组元素的类型还可以是任意的复合类型。没有所有元素都是引用的数组。
		*/
		FacultyMember* members1 = new FacultyMember[35];
		

		for (int index=0; index<35; index++){
			members[index] = randMember();
		}

		/*
				string id = "a001";
				string name = "xiao";
				string tele = "13000099822";
				string title = "lecture";
				members[0]=Teacher(id,name,tele,title);

				Teacher t = (Teacher)(members[0]);

				*/

		members[1]=Official("a001","xiao","13000099822","Service for teachers");
		//FacultyMember &f = members[1];
		//members2[1]= Official("a001","xiao","13000099822","Service for teachers");
		for (int index=0; index<35; index++){
			cout<<members[index].toString()<<endl;

		}



        return 0;
	}

