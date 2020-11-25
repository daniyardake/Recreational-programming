#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <chrono>
#include <ctime>
#include "course.cpp"


using namespace std;

string input(){
  string s;
  getline(cin, s);

  string newS = "";
  for(int i = 0; i<s.length(); i++){
    if(s[i]!=' ' ){newS += tolower(s[i]);}
  }
  return newS;

}
string read(){
  string a;
  cout<<"Your input: ";
  getline(cin,a);
  return a;

}

bool isNumber(string s){
    bool isNum = true;
    for (int i = 0; i < s.length(); i++)
        if (!isdigit(s[i]))
            isNum = false;
    return isNum;
}
void printer(string type){
  if(type == "start") { cout<<"\n*****************************\n";}
  if(type == "end" ) { cout<<"\n-----------------------------\n";}
  if(type == "splash"){
    printer ("date");
    cout<<"This is you SPLASH area\n";
    printer("start");

    cout<<"What would you like to do today?\n";

    cout<<"0: Exit (-exit)\n";
    cout<<"1: View my Courses (-all courses)\n";
    cout<<"2: View my Classmates (-all classmates)\n";
    cout<<"3: View my Assignments (- all assignments)\n";

    cout<<"4: Search by phone (-search phone)\n";
    cout<<"5: Search by first name (- search first name)\n";
    cout<<"6: Search by last name (- search last name)\n";

    cout<<"7: Add new Course (- add course)\n";
    cout<<"8: Add new Classmate (- add classmate)\n";
    cout<<"9: Add new Assignment (- add assignment)\n";
  }
  if(type == "date") {
  cout<<"Today's date: ";
  time_t my_time = time(NULL);
  time_t t = std::time(0);   // get
  tm* now = std::localtime(&t);
  cout<<now->tm_mon<<"/"<<now->tm_mday<<"/"<<now->tm_year+1900<<endl;
}
  if(type == "error") {cout<<"Wrong input\n";}
}
string splashUI(){
  string choice;
  printer("splash");

  choice = read();
  while( (!isNumber(choice)) || (choice=="0") ){
    cout<<"Wrong input\n";
    choice = read();
  }
  printer("end");
  return choice;
}
void loginUI(){
  system("clear");
  cout<<"\nTHIS IS YOUR LOGIN SCREEN";
  cout<<"\nPlease Enter your Password: ";
  printer("start");
  string pass;
  bool isFirstAttempt = true;
  while(pass!="123"){
    if(!isFirstAttempt) { cout<<"Sorry, wrong password\nPLEASE TYPE IN YOUR PASSWORD (or type -exit to exit):\n"; }
      else { isFirstAttempt = false; }
    getline(cin,pass);
    if(pass=="-exit") { exit(0); }
  }
  printer("end");
}

int getNumberOfCourses(){
  ifstream file("courses.txt", ios_base::beg);
  int i = 0;
  string temp;
  while(!file.eof()){
    getline(file, temp);
    i++;
  }
  file.close();
  return i/6;
}

void addCourseToDatabase(Course* course){
  fstream file;
  file.open("courses.txt", fstream::out | fstream::app);

  file<<course->getDatabaseID()<<endl;
  file<<course->getName()<<endl;
  file<<course->getID()<<endl;
  file<<course->getProfName()<<endl;
  file<<course->getGrade()<<endl;
  file<<course->getRoomCode()<<endl;
  file.close();
}
void addCourseUI(){

  string temp;
  int databaseID;
  string courseName;
  string courseID;
  string profName;
  string roomCode;
  double currentGrade;

  int id = getNumberOfCourses() + 1;

  cout<<"Course Name: ";
  getline(cin, courseName);

  cout<<"Course ID: ";
  getline(cin, courseID);

  cout<<"Professor's Name: ";
  getline(cin, profName);

  cout<<"Room's Code: ";
  getline(cin, roomCode);

  cout<<"Current Grade: ";
  getline(cin, temp);
  currentGrade = stod (temp);
  Course *course = new Course (id, courseName, courseID, profName, roomCode, currentGrade);
  addCourseToDatabase(course);
  cout<<"Course succesfully added \n";
  printer("splash");
}

Course* getCourse(int tt){
  ifstream file;
  file.open("courses.txt", ios_base::beg);
  file.clear();

  Course *course = new Course();
  for(int i = 1; i<=tt; i++){
    file>>*course;
  }
  file.close();

  return course;
}

void viewCourses(){ for(int i = 1; i<=getNumberOfCourses(); i++){ cout<<*getCourse(i)<<endl; } }

void action (string s){

    if ( s == "-start") { printer("splash"); }
    else if ( s == "-exit" ) { exit(0); }
    else if ( s == "-allcourses" ) { viewCourses(); }
    else if ( s == "-allclassmates" ) {
      string tempID;
      cout<<"ID of course: ";
      getline(cin, tempID);
      int id = stoi(tempID);
      Course* newCourse = getCourse(id);
      newCourse->showAllClassmates();

    }
    else if ( s == "-allassignments" ) {
      string tempID;
      cout<<"ID of course: ";
      getline(cin, tempID);
      int id = stoi(tempID);
      Course* newCourse = getCourse(id);
      newCourse->showAllAssignments();
    }

    else if ( s == "-searchphone") {
      string phone;
      cout<<"Which number are you looking for? \n";
      getline(cin, phone);

      for(int i = 1; i<=getNumberOfCourses(); i++){
        Course *course = getCourse(i);
        course->searchPhone(phone);
        delete course;
      }
     }
    else if ( s == "-searchfirstname") {
      string fn;
      cout<<"Which First Name are you looking for? \n";
      getline(cin, fn);

      for(int i = 1; i<=getNumberOfCourses(); i++){
        cout<<"Course "<<i<<" mathes: \n";
        Course *course = getCourse(i);
        course->searchFirstName(fn);
        delete course;
      }

     }
    else if ( s == "-searchlastname") {
      string ln;
      cout<<"Which Last Name are you looking for? \n";
      getline(cin, ln);

      for(int i = 1; i<=getNumberOfCourses(); i++){
        Course *course = getCourse(i);
        course->searchLastName(ln);
        delete course;
      }
      }

    else if ( s == "-addcourse") { addCourseUI(); }//DONE
    else if ( s == "-addclassmate") {
      string tempID;

      cout<<"ID of course: ";
      getline(cin, tempID);
      int id = stoi(tempID);
      Course* course = getCourse(id);
      course->addClassMateUI();} //DONE
    else if ( s == "-addassignment") {
      string tempID;
      cout<<"ID of course: ";
      getline(cin, tempID);
      int id = stoi(tempID);
      Course* newCourse = getCourse(id);

      newCourse->addAssignmentUI();

    }//DONE
    else {printer("error");}

  }

int main(){

  loginUI();

  string choice;
  cout<<"Welcome back, Daniyar\nI am your personal academic assistant.\nType -start if you want to start\nType -exit if you want to exit program\n";
  while(true){
    cout<<"You input:    ";
    choice = input();
    action(choice);
  }

  return 0;
}
