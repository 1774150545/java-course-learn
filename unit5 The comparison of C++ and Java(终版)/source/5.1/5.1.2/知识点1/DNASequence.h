/*
 * DNASequence.h
 *
 *  Created on: 2013-10-23
 *      Author: Administrator
 */

#ifndef DNASEQUENCE_H_
#define DNASEQUENCE_H_
#include <string>
using namespace std;
class DNASequence  {
	private:
		string  sequence;
	    int numberOfA;
	public:
		DNASequence(string initialSequence, int initialNumberOfA=0);
	private:
		void  countNucleotides();
	public:
		int  getNumberOfA();
        bool  twoConsecutive(char);
};
#endif /* DNASEQUENCE_H_ */
