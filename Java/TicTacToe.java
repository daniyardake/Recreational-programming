// This is simple pseudo-AI program, which
// can smartly play TicTacToe with you.

package tictactoe;
import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        boolean over = false;
        boolean win = false;
        int players;
        int turn = 0;
        int board[] = new int[9]; // array that is printed
        int positions[] = new int[9]; // holds used positions
        int used[] = new int[9]; // holds used numbers

        System.out.println("How to play: ");
        System.out.println("Player 1 uses odd numbers (1, 3, 5, 7, 9), player 2 uses even numbers (2, 4, 6, 8).");
        System.out.println("Play continues until one player wins by totaling 15 in a line or all the spaces are filled.");
        System.out.println("The boards different postions are as follows: ");
        System.out.println();
        System.out.println("  1  |  2  |  3  ");
        System.out.println("-----------------");
        System.out.println("  4  |  5  |  6  ");
        System.out.println("-----------------");
        System.out.println("  7  |  8  |  9  ");
        System.out.println();
        System.out.println("First enter the position that you want to use, then enter the number you want in that position.");

        System.out.print("Enter 1 for single player or 2 for two player: ");
        players = input.nextInt();
        printBoard(board); // prints empty board

        while (over == false) { // loop for going through each turn until the game is over
            if (players == 1 && (turn + 1) % 2 == 0) {
                cpuTurn(board, turn + 1, positions);
            } else {
                System.out.print("\nWhere would you like to place your number? ");
                getPosition(positions, turn, input); // input and validation
                System.out.print("Enter the number. ");
                getNumber(used, turn, input); // input and validation
                updateBoard(board, positions, used, turn); // updates board to be printed
            }

            printBoard(board);

            turn++; // needed for input validation and checking if the game is over/ who won

            win = checkWin(board); 

            if (win == true) {
                over = true; // to end the game 
                if (turn % 2 == 1) {
                    System.out.println("Player 1 has won! ");
                }
                else if (players == 1 ) {
                    System.out.println("The computer has won!");
                }
                else {
                    System.out.println("Player 2 has won!");
                }
                break; //needed to end game in case player 1 wins on the last turn
            }

            if (turn == 9) { //ends game if board is full and no one wins
                over = true; 
                System.out.println("The game is over in a draw!");
            }
        }
        // TODO code application logic here
    }

    public static void printBoard(int array[]) {
        System.out.println("  " + array[0] + " | " + array[1] + " | " + array[2] + "  ");
        System.out.println("-------------");
        System.out.println("  " + array[3] + " | " + array[4] + " | " + array[5] + "  ");
        System.out.println("-------------");
        System.out.println("  " + array[6] + " | " + array[7] + " | " + array[8] + "  ");
    }

    public static void loadArray(int array[], int size) {
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
    }

    public static void getPosition(int positions[], int turn, Scanner input) {
        int position = input.nextInt();
        boolean badPosition = true;

        while (badPosition) { // this loop is necesarry to catch any input mistakes after passing first test
            while (position < 1 || position > 9) {  // tests to make sure position exists
                System.out.print("That position does not exist. Try again: ");
                position = input.nextInt();
            }
            badPosition = false; // to get out of the first loop if input is valid

            for (int i = 0; i < positions.length; i++) { // tests if position was already used
                while (positions[i] == position) {
                    System.out.print("That position already contains a number. Try again: ");
                    badPosition = true; // in case the position is used, whole loop will begin again so improper input goes through all tests
                    position = input.nextInt();
                }
            }
        }
        
        positions[turn] = position; // updates array of positions once input is tested and meets the requirements
    }

    public static void getNumber(int used[], int turn, Scanner input) {
        int number = input.nextInt();
        boolean badNumber = true;

        while (badNumber) { // this loop is necesarry to catch any input mistakes after passing first test
            while (number < 1 || number > 9) {  // tests to see if number was in range
                System.out.print("That number is not between 1 and 9. Try again: ");
                number = input.nextInt();
            }
            badNumber = false;  // to get out of first loop if input is valid

            for (int i = 0; i < used.length; i++) {  // tests if number was already used
                while (used[i] == number) {
                    System.out.print("That number was already used. Try again: ");
                    badNumber = true; // in case number is used, whole loop will begin again so improper input goes through all tests
                    number = input.nextInt();
                }
            }
            if (turn % 2 == 0 && number % 2 == 0) { // validation for player 1
                while (number % 2 == 0) {
                    System.out.print("The number entered was even. Try again: ");
                    badNumber = true; // see line 135
                    number = input.nextInt();
                }
            }
            if (turn % 2 == 1 && number % 2 == 1) { // validation for player 2
                while (number % 2 == 1) {
                    System.out.print("The number entered was odd. Try again: ");
                    badNumber = true; // see line 135
                    number = input.nextInt();
                }
            }
        }
        used[turn] = number; // updates array of used numbers once input is tested and meets the requirements
    }

    public static void updateBoard(int board[], int positions[], int used[], int turn) { // updates board to printed
        int updater = positions[turn] - 1; // - 1 needed because the values will be 1 through 9
        board[updater] = used[turn];
    }

    public static boolean checkWin(int[] boardArray) { // checks each possible case for a win
        boolean win = false;

        if (boardArray[0] + boardArray[1] + boardArray[2] == 15 && boardArray[0] * boardArray[1] * boardArray[2] != 0) {
            win = true;
        }

        if (boardArray[3] + boardArray[4] + boardArray[5] == 15 && boardArray[3] * boardArray[4] * boardArray[5] != 0) {
            win = true;
        }

        if (boardArray[6] + boardArray[7] + boardArray[8] == 15 && boardArray[6] * boardArray[7] * boardArray[8] != 0) {
            win = true;
        }

        if (boardArray[0] + boardArray[3] + boardArray[6] == 15 && boardArray[0] * boardArray[3] * boardArray[6] != 0) {
            win = true;
        }

        if (boardArray[1] + boardArray[4] + boardArray[7] == 15 && boardArray[1] * boardArray[4] * boardArray[7] != 0) {
            win = true;
        }

        if (boardArray[2] + boardArray[5] + boardArray[8] == 15 && boardArray[2] * boardArray[5] * boardArray[8] != 0) {
            win = true;
        }

        if (boardArray[0] + boardArray[4] + boardArray[8] == 15 && boardArray[0] * boardArray[4] * boardArray[8] != 0) {
            win = true;
        }

        if (boardArray[2] + boardArray[4] + boardArray[6] == 15 && boardArray[2] * boardArray[4] * boardArray[6] != 0) {
            win = true;
        }

        return win;
    }

    public static void cpuTurn(int[] boardArray, int turnNum, int[] positions) {
        System.out.println("Computer turn " + turnNum/2 + ".\n");

        int turn = 0; //variable turn stores value of computer's move, which will check depending on current picture
        int coordinate = 0;
        boolean[] counter = new boolean[16];
        int[] sum = new int[8];

        for (int k = 0; k < 15; k++) {
            counter[k] = false; //making all counter_k equal to zero
        }
        for (int i = 0; i < 9; i++) //all arrows
        {
            for (int k = 1; k <= 9; k++) //all possible numbers 1..9
            {
                if (k == boardArray[i]) {
                    counter[k] = true; //counter_k checks if number k appeared somewhere in the table 3x3
                }
            }
        }

        sum[0] = boardArray[0] + boardArray[1] + boardArray[2]; //sum of First Column
        sum[1] = boardArray[3] + boardArray[4] + boardArray[5]; //sum of Second Column
        sum[2] = boardArray[6] + boardArray[7] + boardArray[8]; // sum of Third Column
        sum[3] = boardArray[0] + boardArray[3] + boardArray[6]; //sum First Row
        sum[4] = boardArray[1] + boardArray[4] + boardArray[7]; //sum Second Row
        sum[5] = boardArray[2] + boardArray[5] + boardArray[8]; //sum Third Row
        sum[6] = boardArray[0] + boardArray[4] + boardArray[8]; //main Diagonal
        sum[7] = boardArray[2] + boardArray[4] + boardArray[6]; //secondary Diagonal

        for (int i = 0; i <= 7; i++) //checking if opponent is attacking cpu at this moment
        {
            if ((sum[i] < 15) && ((15 - sum[i]) % 2 == 1) && ((sum[i] > 5) && !(counter[15 - sum[i]]))) {
                //test if one of the possible sums less than 15
                // checking if number that can win you can be used by your opponent
                // there is a number that opponent can use which sum up to 15

                if (i <= 2) {
                    //checking 3 columns of the array for possible attack
                    if (zeroChecker(boardArray[3 * i]) + zeroChecker(boardArray[3 * i + 1]) + zeroChecker(boardArray[3 * i + 2]) == 1) {
                        //if exactly one of 3 numbers in a column is zero
                        for (int j = 3 * i; j <= 3 * i + 2; j++) {
                            //checking which number from 3 is zero
                            if (zeroChecker(boardArray[j]) == 1) {
                                //if we found a zero then make a turn
                                turn = randomizer(counter, turnNum);
                                coordinate = j;
                            }
                        }
                    }
                }

                if ((i >= 3) && (i <= 5)) {
                    //checking 3 rows of the array for possible attack
                    if (zeroChecker(boardArray[i % 3]) + zeroChecker(boardArray[3 + i % 3]) + zeroChecker(boardArray[6 + i % 3]) == 1) {
                        //if exactly one of 3 numbers in a row is zero
                        for (int j = i % 3; j <= i % 3; j = j + 3) {
                            if (zeroChecker(boardArray[j]) == 1) {
                                //if we found a zero then make a turn
                                turn = randomizer(counter, turnNum);
                                coordinate = j;
                            }
                        }
                    }
                }

                if (i == 6) {
                    //checking main diagonal of the array for possible attack
                    if (zeroChecker(boardArray[0]) + zeroChecker(boardArray[4]) + zeroChecker(boardArray[8]) == 1) {
                        //if exactly one of 3 numbers in a diagonal is zero
                        for (int j = 0; j <= 8; j = j + 4) {
                            if (zeroChecker(boardArray[j]) == 1) {
                                //if we found a zero then make a turn
                                turn = randomizer(counter, turnNum);
                                coordinate = j;
                            }
                        }
                    }
                }

                if (i == 7) {
                    //checking second diagonal of the array for possible attack
                    if (zeroChecker(boardArray[2]) + zeroChecker(boardArray[4]) + zeroChecker(boardArray[6]) == 1) {
                        //if exactly one of 3 numbers in a diagonal is zero
                        for (int j = 2; j <= 6; j = j + 2) {
                            //if we found a zero then make a turn
                            if (zeroChecker(boardArray[j]) == 1) {
                                turn = randomizer(counter, turnNum);
                                coordinate = j;
                            }
                        }
                    }
                }
            }
        }

        if (turn == 0) {
            //if there was not an attack from opponent
            turn = randomizer(counter, turnNum);
            //randomly (with set of rules for random) find a number
            for (int i = 0; i <= 8; i++) {
                //check for square to be free
                if (boardArray[i] == 0) {
                    coordinate = i;
                    break;
                }
            }
        }

        boardArray[coordinate] = turn; //make turn
        positions[turnNum-1] = coordinate+1;
    }

    public static int randomizer(boolean[] counter, int turnNum) {
        //function will be generating random number which were not used before
        Random rand = new Random();
        int number = rand.nextInt(8) + 1; //obtaining random number 1..9

        boolean success = false; //will check if we found such number
        while (success == false) //as long as we did not find such number
        {

            if ((counter[number] == true) || (number % 2 == 1)) //test for number appeared in array and number is odd
            {
                number = rand.nextInt(8) + 1;
                //obtaining random number 1..9 if it did not succesfully passed tests

            } else {
                success = true;
            }

        }
        return number;

    }

    public static int zeroChecker(int k) {
        //check if number is zero or not
        //function will be used to find number of zeros at one line (row or column)
        if (k == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}