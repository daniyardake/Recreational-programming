#include "Course.h"

using namespace std;




//**************Assignments handling*********************
  int Course::getNumberOfAssignments(){
    int result;
    ifstream fileAssignments;
    fileAssignments.open("course"+to_string(databaseID)+"assignments"+".txt");
    if(!fileAssignments){
      fileAssignments.open("course"+to_string(databaseID)+"assignments"+".txt");
      result = 0;
    }
    else {
      int i = 0;
      string temp;
      while(!fileAssignments.eof()){
        getline(fileAssignments, temp);
        i++;
      }
      fileAssignments.close();
      result = i/6;
    }
    return result;
  }
  Assignment* Course::showAssignment(int id){
    ifstream fileAssignments("course"+to_string(databaseID)+"assignments"+".txt");
    fileAssignments.clear();
    string temp;
    int databaseID;
    string description;
    string dueDate;
    string note;
    int weight;
    double grade;


    for(int i = 1; i<=id; i++){
      getline(fileAssignments, temp);
      databaseID = stoi(temp);
      getline(fileAssignments, description);
      getline(fileAssignments, dueDate);
      getline(fileAssignments, note);
      getline(fileAssignments, temp);
      weight = stoi(temp);
      getline(fileAssignments, temp);
      grade = stod(temp);
    }
    fileAssignments.close();

    Assignment *ass = new Assignment(databaseID, description, dueDate, note, weight);
    return ass;
  }
  void Course::showAllAssignments(){
    for(int i = 1; i<=getNumberOfAssignments(); i++){cout<<*showAssignment(i);}
  }

  void Course::addAssignment(Assignment* ass){

    ofstream fileAssignments;

    fileAssignments.open("course"+to_string(databaseID)+"assignments"+".txt",  fstream::in | fstream::out | fstream::app);
    if (!fileAssignments){

      fileAssignments.open("course"+to_string(databaseID)+"assignments"+".txt",  fstream::in | fstream::out | fstream::app);
      fileAssignments.close();
    }

    fileAssignments<<to_string(ass->getDatabaseID())<<endl;
    fileAssignments<<ass->getDescription()<<endl;
    fileAssignments<<toString(ass->getDueDate())<<endl;
    fileAssignments<<ass->getNote()<<endl;
    fileAssignments<<to_string(ass->getWeight())<<endl;
    fileAssignments<<to_string(ass->getGrade())<<endl;
    fileAssignments.close();
  }
  void Course::addAssignmentUI(){
    int id1;
    string description1;
    date dueDate1;
    string note1;
    int weight1;
    double grade1;

    string temp;
    id1 = getNumberOfAssignments() + 1;

    cout<<"Description: ";
    getline(cin, description1);

    cout<<"Due Date: \n";
    cout<<"Month: ";
    getline(cin, temp);
    dueDate1.month = stoi(temp);
    cout<<"Day: ";
    getline(cin, temp);
    dueDate1.day = stoi(temp);
    cout<<"Year: ";
    getline(cin, temp);
    dueDate1.year = stoi(temp);


    cout<<"Note: ";
    getline(cin, note1);

    cout<<"Weight: ";
    getline(cin, temp);
    weight1 = stoi(temp);

    cout<<"Grade: ";
    getline(cin, temp);
    grade1 = stod(temp);

    Assignment *ass = new Assignment (id1, description1, dueDate1, note1, weight1, grade1);
    addAssignment(ass);
    cout<<"Assignment succesfully added \n";


  }

//--------------Assignments handling---------------------

//**************Classmates handling*********************
  int Course::getNumberOfClassmates(){
    int result;
    ifstream fileClassMates;
    fileClassMates.open("course"+to_string(databaseID)+"classmates"+".txt");
    if(!fileClassMates){
      fileClassMates.open("course"+to_string(databaseID)+"classmates"+".txt");
      result = 0;
    }
    else {
      int i = 0;
      string temp;
      while(!fileClassMates.eof()){
        getline(fileClassMates, temp);
        i++;
      }
      fileClassMates.clear();
      fileClassMates.close();
    result = i/6;
  }
  return result;
}
  ClassMate* Course::showClassmate(int id){

  ifstream file("course"+to_string(databaseID)+"classmates"+".txt", ios::beg);
  file.clear();

  string temp, firstName, lastName, phone, email, note;


  for(int i = 1; i<=id; i++){
    getline(file, temp);
    getline(file, firstName);
    getline(file, lastName);
    getline(file, phone);
    getline(file, email);
    getline(file, note);
  }
  file.close();
  databaseID = stoi(temp);
  ClassMate *cm = new ClassMate(databaseID, firstName, lastName, phone, email, note);
  return cm;
}
  void Course::showAllClassmates(){
    for(int i = 1; i<=getNumberOfClassmates(); i++){

      cout<<*showClassmate(i);}
  }

  void Course::addClassMate(ClassMate* cm){
  fstream fileClassMates;


  fileClassMates.open("course"+to_string(databaseID)+"classmates"+".txt",  fstream::in | fstream::out | fstream::app);
  if (!fileClassMates ){
    fileClassMates.open("course"+to_string(databaseID)+"classmates"+".txt",  fstream::in | fstream::out | fstream::app);
    fileClassMates.close();
  }
  fileClassMates.close();

  fileClassMates.open("course"+to_string(databaseID)+"classmates"+".txt" , fstream::out | fstream::app);

  fileClassMates<<cm->getDatabaseID()<<endl;
  fileClassMates<<cm->getFirstName()<<endl;
  fileClassMates<<cm->getLastName()<<endl;
  fileClassMates<<cm->getPhone()<<endl;
  fileClassMates<<cm->getEmail()<<endl;
  fileClassMates<<cm->getNote()<<endl;

  fileClassMates.close();
}
  void Course::addClassMateUI(){

    int id;
    string firstName;
    string lastName;
    string phone;
    string email;
    string note;

    id = getNumberOfClassmates() + 1;

    cout<<"First Name: ";
    getline(cin, firstName);

    cout<<"Last Name: ";
    getline(cin, lastName);

    cout<<"Phone: ";
    getline(cin, phone);

    cout<<"Email: ";
    getline(cin, email);

    cout<<"Note: ";
    getline(cin, note);

    ClassMate *cm = new ClassMate (id, firstName, lastName, phone, email, note);

    addClassMate(cm);
    cout<<"Classmate succesfully added \n";

  }
  void Course:: searchPhone(string phone){ for(int i = 1; i<=getNumberOfClassmates(); i++){
      ClassMate* cm = showClassmate(i);
        if( cm->getPhone().find(phone) ){
          cout<<*cm;
        }
        delete cm;
    } }
  void Course:: searchFirstName(string fn){
      for(int i = 1; i<=getNumberOfClassmates(); i++){
        ClassMate* cm = showClassmate(i);
          if( toLow(cm->getFirstName()).find(toLow(fn))!=std::string::npos ){
            cout<<*cm<<endl;
          }
          delete cm;
    }
  }
  void Course:: searchLastName(string ln){
        string newLn;
        for(int i = 1; i<=getNumberOfClassmates(); i++){
          ClassMate* cm = showClassmate(i);
            //cout<<"\n\n 1: "<<;
            //cout<<"   2: "<<ln;
            newLn =  cm->getLastName();
            if( toLow(newLn).find(toLow(ln))!=std::string::npos  ){
              cout<<*cm;
            }
            delete cm;
        }
      }
//--------------Classmates handling---------------------

ostream& operator<<(ostream &os, const Course& n){
  os<<"Course ID: "<<n.databaseID<<endl;
  os<<"Course Name: "<<n.courseName<<endl;
  os<<"Course ID: "<<n.courseID<<endl;
  os<<"Professor's Name: "<<n.profName<<endl;
  os<<"Room Code: "<<n.roomCode<<endl;
  os<<"Current Grade: "<<n.currentGrade<<endl;
  return os;
}
istream& operator>>(istream &is, Course& n){
  string temp;

  getline(is,temp);
  n.databaseID = stoi(temp);
  getline(is,n.courseName);
  getline(is,n.courseID);
  getline(is,n.profName);
  getline(is,temp);
  n.currentGrade = stod(temp);
  getline(is,n.roomCode);
return is;
}
