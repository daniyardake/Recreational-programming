#ifndef COURSE_H
#define COURSE_H



#include <iostream>
#include <string>
#include <fstream>
#include <cstdio>
#include <string>
#include "Assignment.cpp"
#include "classMate.cpp"

using namespace std;

class Course;

class Course{

private:
  int databaseID;
  string courseName;
  string courseID;
  string profName;
  string roomCode;
  double currentGrade;

public:
  Course(){}
  Course(int id, string cn, string cid, string pn, string rc, double cg){
    databaseID = id;
    courseName = cn;
    courseID = cid;
    profName = pn;
    currentGrade = cg;
    roomCode = rc;
  }
//*****************Getters and setters********************
  int getDatabaseID() { return databaseID;}
  string getName() { return courseName;}
  string getID() { return courseID;}
  string getProfName() { return profName;}
  double getGrade() { return currentGrade;}
  string getRoomCode() { return roomCode; }
  void setDatabaseID( int sdid) { databaseID = sdid; }
  void setName(string cn) { courseName = cn; }
  void setID(string cid) { courseID = cid; }
  void setProfName(string pn) { profName = pn; }
  void setGrade(double g) { currentGrade = g; }
  void setRoomCode ( string r) {roomCode = r; }

  int getNumberOfAssignments();
  Assignment* showAssignment(int id);
  void showAllAssignments();
  void addAssignment(Assignment* ass);
  void addAssignmentUI();

  int getNumberOfClassmates();
  ClassMate* showClassmate(int id);
  void showAllClassmates();
  void searchPhone(string phone);
  void searchFirstName(string fn);
  void searchLastName(string ln);


  void addClassMate(ClassMate* cm);
  void addClassMateUI();

//----------------Getters and setters------------------
  friend class ClassMate;
  friend class Assignment;
  friend ostream& operator<<(ostream &os, const  Course& n);
  friend istream& operator>>(istream &is, Course& n);

};




#endif
