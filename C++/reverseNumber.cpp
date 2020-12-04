#include <iostream>

using namespace std;

int reverseNumber(int num){
    int copy = num;
    int lastDigit, result = 0;

    while (copy != 0){
        lastDigit = copy%10;
        result = result *10 + lastDigit;
        copy /= 10;
    }
    return result;
}

int main()
{
    int num;
    cout << "Number to reverse: ";
    cin>>num;
    cout << num << " reversed: " << reverseNumber(num)<<endl;
    return 0;
}
