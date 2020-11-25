// minimum number of coins needed to give a change

#include <iostream>
using namespace std;
struct numOfCoins{
  int nickels;
  int dimes;
  int quarters;
  int fiftycents;
  int dollars;
};

int maxNumOfCoinsLessThanGiven(int sum, int num){
  //checks the maximum number of coins needed to change SUM using no more than NUM coins (100,50,25,10,5)

  int result; //variable to store the result
  int minNumOfCoins = sum/100 + (sum%100)/50 + (sum%50)/25 + (sum%25)/10 + (sum - sum/25*25)%10/5; // minimim number of coins needed
  if( (sum==0) && (num==0) ){result = -1;}
  else if( (sum%5!=0)){result = 0;} //check if divisible by 5
  else if ( (num>sum/5) || (minNumOfCoins>num) ){ result = 0; } //bouns checking
  else if( (sum!=50) && (sum!=25) ) { result = num; }
  else if(sum==25){
    if(num==2){result = 0;}
    else {result = num;}
  }
  else if (sum == 50){
    if(num==3){result = 0;}
    else {result = num;}
  }
  return result;
}

numOfCoins changeCoins(int sum, int num){
  numOfCoins result;
  result.nickels = 0;
  result.dimes = 0;
  result.quarters = 0;
  result.fiftycents = 0;
  result.dollars = 0;

  if ( (sum == 0) && (num==0) ) {
    result.nickels = 0;
    result.dimes = 0;
    result.quarters = 0;
    result.fiftycents = 0;
    result.dollars = 0;
  }
  else {


    if( maxNumOfCoinsLessThanGiven(sum, num) != 0 ){
      if( (sum>=100) && ( maxNumOfCoinsLessThanGiven(sum-100,num-1)!=0 )){
        result = changeCoins(sum-100,num-1);
        result.dollars = changeCoins(sum-100,num-1).dollars + 1;
      }
      else if ( (sum>=50) && ( maxNumOfCoinsLessThanGiven(sum-50,num-1)!=0 ) ){
        result = changeCoins(sum-50,num-1);
        result.fiftycents = changeCoins(sum-50,num-1).fiftycents + 1;
      }
      else if ( (sum>=25) && ( maxNumOfCoinsLessThanGiven(sum-25,num-1)!=0 ) ){
        result = changeCoins(sum-25,num-1);
        result.quarters = changeCoins(sum-25,num-1).quarters + 1;
      }
      else if ( (sum>=10) && ( maxNumOfCoinsLessThanGiven(sum-10,num-1)!=0 ) ){
        result = changeCoins(sum-10,num-1);
        result.dimes = changeCoins(sum-10,num-1).dimes + 1;
      }
      else if ( (sum>=5) && ( maxNumOfCoinsLessThanGiven(sum-5,num-1)!=0 ) ){
        result = changeCoins(sum-25,num-1);
        result.nickels = changeCoins(sum-5,num-1).nickels + 1;
      }
    }
  }

  return result;
  }

int main(){
  int sum,num;
  std::cout<<"\nMoney needed to change: ";
  std::cin>>sum;
  std::cout<<"\nNumber of coins available: ";
  std::cin>>num;

  numOfCoins change = changeCoins(sum, num);

  if(maxNumOfCoinsLessThanGiven(sum,num)!=0) {
    cout<<"\nMaximum Number of Coins: "<<maxNumOfCoinsLessThanGiven(sum,num)<<endl;

    cout<<endl<<"----------------------"<<endl;
    cout<<"Number of\n"<<"Nickels: "<<change.nickels<<endl;
    cout<<"Dimes: "<<change.dimes<<endl;
    cout<<"Quarters: "<<change.quarters<<endl;
    cout<<"Fifties: "<<change.fiftycents<<endl;
    cout<<"Hundreds: "<<change.dollars<<endl;
    cout<<sum<<" = "<<" 5*"<<change.nickels<<" + "<<" 10*"<<change.dimes<<" + "<<" 25*"<<change.quarters<<" + "<<" 50*"
    <<change.fiftycents<<" + "<<" 100* "<<change.dollars<<endl<<"----------------------"<<endl;
  }
  else { cout<<"\nSorry, it is impossible"<<endl; }


  //printChange(sum,num);
  //cout<<maxNumOfCoinsLessThanGiven(sum,num)<<endl;
  return 0;
}
