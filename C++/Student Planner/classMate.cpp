#include "Classmate.h"

ostream& operator<<(ostream &os, const ClassMate& n){
  os<<"Classmate ID: "<<n.databaseID<<endl;
  os<<"First Name: "<<n.firstName<<endl;
  os<<"Last name: "<<n.lastName<<endl;
  os<<"Phone: "<<n.phone<<endl;
  os<<"Email: "<<n.email<<endl;
  os<<"Note: "<<n.note<<endl;
  return os;
}
