#include <iostream>
using namespace std;
int count1 = 0;
int count2 = 0;
int count3 = 0;
int count4 =0;
int count5 =0;
/*
class CanFight {
	public:
	CanFight(){cout << "CanFight called : " << count1++ << endl;}  
	virtual void fight()=0;
};

class CanSwim {
	public:
	CanSwim(){cout << "CanSwim called : " << count2++ << endl;}  
	virtual void swim()=0;
};

class CanFly {

	public:
	CanFly(){cout << "Canfly called : " << count3++ << endl;}  
	virtual void fly()=0;
};*/


class CanFight {
	public:
	CanFight(){cout << "CanFight called : " << count1++ << endl;}  
	virtual void fight(){
	  	cout<<"CanFight.fight()"<<endl;
	}
};

class CanSwim {
	public:
	CanSwim(){cout << "CanSwim called : " << count2++ << endl;}  
	virtual void swim(){
			cout<<"CanSwim.swim()"<<endl;
	}
};

class CanFly {

	public:
	CanFly(){cout << "Canfly called : " << count3++ << endl;}  
	virtual void fly(){
	     cout<<"CanFly.fly()"<<endl;
	}
};

class Animal:public CanFight, public CanSwim, public CanFly {
	public:
	void run(){
	       cout<<"Animal.run()"<<endl;
	}
};

class ActionCharacter {
	public:
	ActionCharacter(){cout << "ActionCharacter called : " << count5++ << endl;}  
	void fight() {
		
		cout<<"ActionCharacter.fight()"<<endl;
	}
};

class Hero : public CanFight, public CanSwim, public CanFly, public ActionCharacter{ //,public ActionCharacter
    public:	
	Hero(){cout << "Hero called : " << count4++ << endl;}  
	void swim() {
		
		cout<<"Hero.swim()"<<endl;
	}
  
	void fly() {
		
		cout<<"Hero.fly()"<<endl;
	}
};

class Adventure {
public:
	static void t(CanFight x) {
		
		x.fight(); 
	}
	  
	static void u(CanSwim x) { 
		
		x.swim();
	}
	  
	static void v(CanFly x) {
		
		x.fly();
	}
	  
	static void w(ActionCharacter x) {
		
		x.fight(); 
	}	  
	
} ;
int main() {
		
			
		Hero h;
		//Adventure a;
		//CanSwim c;
		//h.fight();
		/*c=h;
		h.CanSwim::swim();   
		Adventure::t(h); // Treat it as a CanFight
		a.u(h); // Treat it as a CanSwim
		a.v(h); // Treat it as a CanFly
		//a.w(h); // Treat it as an ActionCharacter*/
		return 0;
	}