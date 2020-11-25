#include "Assignment.h"

ostream& operator<<(ostream &os, const Assignment& n){
  os<<"Assignment ID: "<<n.databaseID<<endl;
  os<<"Description: "<<n.description<<endl;
  os<<"Due Date: "<<toString(n.dueDate)<<endl;
  os<<"Note: "<<n.note<<endl;
  os<<"Weight: "<<n.weight<<endl;
  os<<"Grade: "<<n.grade<<endl;
  return os;
}
