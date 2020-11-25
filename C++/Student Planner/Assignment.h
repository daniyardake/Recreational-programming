#ifndef ASSIGNMENT_H
#define ASSIGNMENT_H


#include <iostream>
#include <string>
#include <fstream>
#include <cstdio>
#include "date.h"


using namespace std;



class Assignment{


private:
  int databaseID;
  string description;
  date dueDate;
  string note;
  int weight;
  double grade;

public:

  Assignment(int dID, string d, date due, string n, int w){
    databaseID = dID;
    description = d;
    dueDate = due;
    note = n;
    weight = w;
    grade = 0;

  }

  Assignment(int dID,  string d, string due, string n, int w){
    databaseID = dID;
    description = d;
    dueDate = stringToDate(due);
    note = n;
    weight = w;
    grade = 0;
  }

  Assignment(int dID,  string d, string due, string n, int w, double g){
    databaseID = dID;
    description = d;
    dueDate = stringToDate(due);
    note = n;
    weight = w;
    grade = g;
  }

  Assignment(int dID, string d, date due, string n, int w, double g){
    databaseID = dID;
    description = d;
    dueDate = due;
    note = n;
    weight = w;
    grade = g;

  }



//*****************Getters and setters********************
  int getDatabaseID() { return databaseID;}
  string getDescription() { return description;}
  date getDueDate() { return dueDate;}
  string getNote() { return note;}
  int getWeight() { return weight;}
  double getGrade() { return grade;}

  void setDatabaseID( int sdid) { databaseID = sdid; }
  void setDescription(string d) { description = d; }
  void setDueDate(date dd) { dueDate = dd; }
  void setNote(string n) { note = n; }
  void setWeight(int w) { weight = w; }
  void setGrade(double g) { grade = g; }
  //----------------Getters and setters------------------
  friend ostream& operator<<(ostream &os, const  Assignment& n);
  friend class Course;
  friend class ClassMate;
};
#endif
