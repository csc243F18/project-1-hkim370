/************************************************************************/
/* Author: Harry Kim 													*/
/* Course: CSC243-10 													*/
/* Program: Project 1	 												*/
/* Due Date: Sep 18, 2018 												*/
/* Filename: Project1.java												*/
/* Purpose: This application prompts the user for the number of years	*/
/*          that the user has until retirement and the amount of money 	*/
/*          the user can save annually. The user's entries are 			*/
/* 			validated. There is a 5% interest rate on 					*/
/*          the balance every year. The amount of savings for 			*/
/* 			retirement will be calculated using the compound interest	*/
/* 			formula with additional payments added on every year. The 	*/
/*			amount of money the user will have at retirement will be 	*/
/*			displayed.													*/
/* Actual Effort: 12 hours												*/											
/************************************************************************/
import java.util.Scanner;                  // Importing java libraries
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
public class Project1 {             // Class Project1
/****************************************************************/
/* Name: void main												*/
/* Description: Calls the functions								*/
/* Parameters: String[] args - arguments for the command line	*/
/* Return value: none											*/
/****************************************************************/
public static void main(String[] args) {
  Scanner input = new Scanner(System.in);           					// Creating a Scanner variable named input.
  int years = userYears(input);	  									// Creating a variable for the user's input of the number of years left until retirement.
  double savings = retirementAmount(years, input);                    // Creating a variable for the user's input of the amount of money able to be saved annually.
  double retirement = calculateRetirement(years, savings);            // Creating a variable for the amount of money that the user will have saved for retirement.       
  printValues(years, retirement);										// Print the values.
  input.close();														// Close the scanner variable.
}	
/****************************************************************/
/* Name: int userYears											*/
/* Description: Prompts the user to enter the number of years	*/
/*              left until retirement							*/
/* Parameters: Scanner input - user input						*/
/* Return value: int years - the number of years the user has 	*/
/* 						     left until retirement				*/										
/****************************************************************/
public static int userYears(Scanner input)  {                  
  int inputYears = 0;                                        // Creating an integer variable from the user input for number of years left until retirement.
  boolean userYears = false;
  while (inputYears <= 0 || BigDecimal.valueOf(inputYears).scale() > 0 || userYears == false) {                         // While loop to validate user enters a positive integer with no decimal places.
    try {                     // Try catch block to catch exceptions.
      System.out.print("Enter the number of year(s) left until your retirement as a positive integer: ");
      inputYears = input.nextInt();
      if (inputYears > 0 && BigDecimal.valueOf(inputYears).scale() == 0) {              // If the user input is validated, the while loop will break.
        break;
      }
      input.nextLine();                                      // Clear the scanner buffer.
      System.out.println("Invalid entry. Please enter a positive integer.");
    }
    catch (InputMismatchException e) {                    // If user enters wrong data type, catch the exception.
      input.nextLine();
      System.out.println("Invalid entry. Please enter a positive integer.");
    }
    }
    return inputYears;
}
    
/****************************************************************/
/* Name: double retirementAmount								*/
/* Description: Prompts the user to enter the amount of money	*/
/* 				able to be saved annually for retirement		*/
/* Parameters: int years - the number of years the user has 	*/
/* 						   left until retirement				*/
/* 			   Scanner input - user input						*/
/* Return value: double savings - the amount of money the user 	*/
/*  			                  is able to save annually		*/ 											
/****************************************************************/
public static double retirementAmount(int years, Scanner input) {
  double inputSavings = 0;                                    // Creating a double variable from the user input for amount of money able to be saved annually. 
  boolean userSavings = false;
  while (inputSavings <= 0 || BigDecimal.valueOf(inputSavings).scale() > 2 || userSavings == false) {                       // While loop to validate user enters a positive number with at most two decimal places.
    try {
      System.out.print("Enter the amount of money you can save for retirement as a positive number with at most two decimal points: ");
      inputSavings = input.nextDouble(); 
      if (inputSavings > 0 && BigDecimal.valueOf(inputSavings).scale() <= 2) {                                // If the user input is validated, the while loop will break.
    	break;
      }
      input.nextLine();                            // Clear the scanner buffer.
      System.out.println("Invalid entry. Please enter a positive number with at most two decimal points.");
    }
    catch (InputMismatchException e){                                    // If user enters wrong data type, catch the exception.
      input.nextLine();
      System.out.println("Invalid entry. Please enter a positive number with at most two decimal points.");
    }                                                      
  }
  return inputSavings;
}
/****************************************************************/
/* Name: double calculateRetirement								*/
/* Description: Calculates the amount of money the user will	*/
/* 				have at retirement								*/
/* Parameters: int years - the number of years the user has 	*/
/* 						   left until retirement				*/
/*  		   double savings - the amount of money the user 	*/
/*  			                is able to save annually		*/
/* Return value: double retirement - the amount of money the	*/
/* 									 user will have at 			*/  
/* 									 retirement					*/
/****************************************************************/
public static double calculateRetirement(int years, double savings) {
  double annualSavings = savings;
  double userRetirement = 0;                   		// Creating a double variable to calculate the amount of money available for retirement.
  for (int x = 0; x < years; x++) {						// For loop to go through the amount of years left until retirement.
    userRetirement = savings * 1.05;              // Using the compound interest formula to calculate the savings for retirement.
    if (x < years-1) {									// If statement so that an extra year of savings is not added on to the total savings.
      savings = userRetirement + annualSavings;					// Adding the additional money saved annually.
    }
  }
  return userRetirement;
}
/****************************************************************/
/* Name: void printValues										*/
/* Description: Prints the number of years the user has left	*/
/* 				until retirement and the amount of money saved 	*/
/*              for retirement									*/
/* Parameters: int years - the number of years the user has 	*/
/* 						   left until retirement				*/
/*  		   double retirement - the amount of money the		*/
/* 									user will have at 			*/
/* 									retirement					*/
/* Return value: none											*/
/****************************************************************/
public static void printValues(int years, double retirement) {
  DecimalFormat df = new DecimalFormat("0.00");				// Print values to two decimal places.
  System.out.print("The amount of money you will have after " + years + " year(s) of savings for retirement: ");
  System.out.print("$" + df.format(retirement));
}
}