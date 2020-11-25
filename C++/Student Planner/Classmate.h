#ifndef CLASSMATE_H
#define CLASSMATE_H

#include <iostream>
#include <string>

using namespace std;

class ClassMate {

private:
  int databaseID;
  string firstName;
  string lastName;
  string phone;
  string email;
  string note;

public:
  ClassMate(int databaseID,  string firstName, string lastName, string phone, string email, string note){
    this->databaseID = databaseID;
    this->firstName = firstName;
    this->lastName = lastName;
    this->phone = phone;
    this->email = email;
    this->note = note;

  }

//**************SETTER and GETTERS*******************
  void setDatabaseID(int id) {databaseID = id;}
  void setfirstName(string fn) { firstName = fn; }
  void setLastName(string ln) { lastName = ln; }
  void setPhone(string p) { phone = p; }
  void setEmail(string e) { email = e; }
  void setNote(string n) { note = n; }
  int getDatabaseID() {return databaseID; }
  string getLastName() {return lastName; }
  string getFirstName() {return firstName; }
  string getPhone() {return phone; }
  string getEmail() {return email; }
  string getNote() {return note; }
//-------------SETTER and GETTERS---------------------
friend ostream& operator<<(ostream &os, const  ClassMate& n);
friend class Course;
friend class Assignment;
};


#endif
