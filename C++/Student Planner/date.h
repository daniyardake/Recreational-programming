using namespace std;

struct date{
  int month;
  int day;
  int year;
};

date stringToDate(string s){
  date result;
  bool firstFound = false;
  int pos1;
  int pos2;

  for(int i = 0; i < s.length(); i++){
    if(s[i]=='/' && !firstFound){pos1 = i; firstFound = true;}
    if(s[i]=='/' && firstFound) {pos2 = i;}
  }

  string month = s.substr(0,pos1);
  string day = s.substr(pos1+1, pos2-pos1-1);
  string year = s.substr(pos2+1, s.length()-pos2-1);

  result.month = stoi(month);
  result.day = stoi(day);
  result.year = stoi(year);

  return result;
  }

string toString(date d){
  string s = to_string(d.month)+"/"+to_string(d.day)+"/"+to_string(d.year);
  return s;
}

string toLow(string s){
  //char temp;
  for (int i = 0; i<s.length(); i++){
    s[i] = tolower(s[i]);
  }
  return s;
}
