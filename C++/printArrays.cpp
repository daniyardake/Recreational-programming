/*
 
 Create a program that creates an array of 500 random numbers between -2000 and 2000.
 Write several functions:
	•	Function 1 - prints the array with a certain number of elements per line.  Prototype: void printArray(int array[], int numElements, int numPerLine, ostream &os)
	•	Function 2 prints only the array elements that are evenly divisible by an input number. Prototype: void printDivBy(int array[], int numElements, int numPerLine, int divBy, ostream &os)
	•	Function 3 takes the input array and returns the maximum, the next-to-maximum, the minimum, and the next-to-minimum values
	•	Function 4 returns the sum of the values in the array
 Also calculate the average of the array. Your program should call each function; use the number 8 for numPerLine for the printArray function, and use 5 for numPerLine and the number 3 for the divBy value when calling the printDivBy function. Print the max, next-to-max, min, and next-to-min, the sum, and the average of the numbers from main, not from the function.
 Your program MUST use arrays.  Your program should NOT call a sort function.
*/

#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstdlib>
#include <string>
#include <cmath>
#include <ctime>


using namespace std;

//Function that uses ostream to print numElements elements with numPerLine elements per line
void printArray(int array[], int numElements, int numPerLine, ostream &os)
{
	//loop that linearly run through all elements of array
	for(int i=0; i<numElements; i++)
	{
		os<<array[i]<<' ';

		//loop will add new line after every numPerline elements
		if((i+1)%numPerLine==0)
			os<<endl;
	}

}

//Function that uses ostream to print numElements elements which are divisible by divBy number with numPerLine elements per line
void printDivBy(int array[], int numElements, int numPerLine, int divBy, ostream &os)
{
	int count = 0; //counter for number of elements in newArray

	//linear loop that checks if an element of array is divisible by divBy and ++ counter if yes
	for (int i=0; i<500; i++)
		if (array[i]%divBy == 0)
			count++;


	int newArray[count]; //new array to store elements of array that are divisible by divBy
	int t = 0; //variable to declaration of newArray

	//loop that assigns values of elements of array that are divisible by divBy to newArray
	for (int i=0; i<500; i++)
	{
		if ((array[i]%divBy == 0) )
		{
			newArray[t] = array[i];
			t++;
		}

	}

	//function prints newArray with evenly divisble number by property of function printArray
	printArray(newArray, numElements, numPerLine, os);
}

//Function that calculates sum of all elements of array
int arraySum(int array[], int numElements)
{
	int sum = 0; //variable for storing sum of elements

	//linear loop that adds values of every elements to variable sum
	for (int i =0; i<numElements; i++)
		sum = sum + array[i];

	return sum;
}

//Function takes the input array and returns the maximum, the next-to-maximum, the minimum, and the next-to-minimum values as a single number
long long int maxMin(int array[], int numElements)
{
	int max = array[0];
	int min = array[0];
	int almostMax = array[0];
	int almostMin = array[0]; //assuming that a[0] is the maximum, the next-to-maximum, the minimum, and the next-to-minimum

	//loop finds max and minimum elements by checking if an element more (or less) that current maximum (or minimum)
	for(int i=0; i<numElements; i++)
	{
		if (array[i]>max)
			max = array[i];
		if (array[i]<min)
			min = array [i];
	}
	//loop finds elements which are less (or more) than maximum (or minimum) of the array, but more (or less) that every other
	for(int i=0; i<numElements; i++)
	{
		if ((array[i]<max) && (array[i]>almostMax))
			almostMax = array[i];
		if ((array[i]>min) && (array[i]<almostMin) )
			almostMin = array [i];
	}

	// new variable for storing 4 numbers (max, min, almostMax, almostMin) that need to be returned
	// technique is following:
	// 1) add 2000 to all of 4 variables so that they become positive (now the are in 0..4000 interval)
	// 2) add 12 zeros (multiply by 10^12) after first (4-digit) number
	// 3) by suming min variable (which is also 4 digit number) to result we obtain number which first 4 digits are form max variable and next 4 digits are form min variable
	// 4) do the same for almostMin and almostMax
	long long int result = (max+2000)*pow(10,12) + (min+2000)*pow(10,8) + (almostMax+2000)*pow(10,4) + (almostMin+2000);

	return result;
}

int main()
{

	srand(time(0));
    ofstream fileout ("Test.txt"); //create new file

	int randomArray [500]; //create array of size 500
    for (int i=0; i<500; i++) //fill it with random numbers in interval of -2000..2000
    {
    	int ok = rand()%4000-2000;
    	randomArray [i] = ok;
    }

    printArray(randomArray, 500, 8, fileout); //calling first function
    fileout<<"\n\n\n\n\n\n\n\n";
    printDivBy(randomArray, 100, 5, 3, fileout);//calling second function

    double average= arraySum(randomArray,500)/500.0; //printing average using arraySum function
    fileout<<"\n\n\n\n\n\n\n\nSum is:"<<arraySum(randomArray, 500);
    fileout<<"\n\n\n\n\n\n\n\nAverage is:"<<average;

    fileout<<"\n\n\n The maximum is:"<<maxMin(randomArray, 500)/1000000000000 - 2000; //obtaining first 4 digits of return result of maxMin function
    fileout<<"\nThe minimum is:"<<(maxMin(randomArray, 500)/100000000)%1000 - 2000; //obtaining second 4 digits of return result of maxMin function
    fileout<<"\n\n\n The next before maximum is:"<<(maxMin(randomArray, 500)/10000) % 10000 - 2000; //obtaining three 4 digits of return result of maxMin function
    fileout<<"\n\n\n The next after minimum is:"<<(maxMin(randomArray, 500) % 10000) - 2000; //obtaining last 4 digits of return result of maxMin function


    fileout.close();
    return 0;
}
