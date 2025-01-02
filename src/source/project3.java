/*
 * Author: Joshua Leonard
 * Course: COP3530
 * Project #: 3
 * Title: Stacks and Priority Queues with Linked Lists
 * Due Date: 10/28/2022
 *
 * This program demonstrates the use of:
 * 1: Stacks w/Linked List
 * 		a)Setting up Stack class with a Linked List
 * 		b)Pushing onto the stack with a Linked List
 * 		c)Popping from the stack with a Linked List
 * 2: Priority Queues w/Linked List
 * 		a)Setting up Priority Queue class with a Linked List
 * 		b)Inserting into the Priority queue with a Linked List
 * 		c)Removing from the Priority queue with a Linked List
 * 
 */
package source;

import java.io.IOException;
import java.util.Scanner;

/**
 * <b>COP 3530: Project 3 – Stacks and Priority Queues with Linked Lists</b>
 * <p>
 * This is the main class file that handles the display menu, reading in the
 * user input for the file creating the stacks and priority queues, and printing
 * formatted strings with country data.
 * 
 * @author Joshua Leonard
 * @version 10/28/2022
 */
public class project3 {
	private static FileHandler fileHandler = new FileHandler();
	private static String fileName;
	private static Scanner scnr = new Scanner(System.in);
	public static Stack cStack = new Stack();
	public static pQueue pQueue = new pQueue();

	/**
	 * <b>!Entry Point!</b>
	 * <p>
	 * This is the main function.
	 * <p>
	 * It prints out the the Project #, Who the instructor is, who the student is,
	 * students N#:, and the name of the project.
	 * <p>
	 * The program will loop until a correct file name is given, it will create a
	 * new stack and priority queue of country objects using the Linked Lists data
	 * structure.
	 * <p>
	 * If the file was successfully read it will first create the stack push the
	 * objects onto the stack, print the stack and finally pop all the objects from
	 * the stack and into the priority queue, the country objects will get sorted
	 * into the priority queue as the countries popped from the stack.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(
				"COP3530 Project 3\nInstructor: Professor Liu\nStudent: Joshua Leonard\nN#: N01476308\nStacks and Priority Queues with Linked Lists\n");

		boolean flag = false;
		while (!flag) {
			try {
				System.out.print("Enter the file name: ");
				fileName = scnr.nextLine();
				fileHandler.readCountry(fileName);
				System.out.println("Stack Contents:");
				printData("Stack");
				stackTOpQueue(cStack);
				System.out.println("Priority Queue Contents:");
				printData("Queue");
				System.out.println();
				displayMenu();
				flag = true;
			} catch (IOException e) {
				System.out.println("File not found: " + e.getMessage());
			}
		} // end while loop
		scnr.close();
	}

	/**
	 * This methods is the display menu, it will loop until the user selects 3 to
	 * quit the program. Each input will call on another method or methods to get
	 * the correct data.
	 * <p>
	 * 
	 * <pre>
	 * 1: Will prompt the user for input to delete countries from the priority queue.
	 * 2: Print the priority queue.
	 * 3: Quits program
	 * </pre>
	 */
	public static void displayMenu() {
		// variable declarations
		boolean flag = true;
		boolean inputFlag = false;

		// Loop user options until user types "3" sets the flag
		// to false will call a new displayMenu.
		do {
			// user options print statements
			System.out.println("Please make a selection: ");
			System.out.println("1) Enter a DR interval for deletions on priority queue.");
			System.out.println("2) Print the priority queue.");
			System.out.println("3) Exit");
			// prints out what the user selected
			String input = "";
			while (!inputFlag) {
				input = scnr.nextLine();
				if (input.matches("1|2|3")) {
					inputFlag = true;
				} else {
					System.out.print("Invalid Choice! Enter your choice:");
				} // end if/else
			} // end while
			inputFlag = false;
			switch (input) {
			case "1":
				drChoice();
				break;
			case "2":
				printData("Queue");
				break;
			case "3":
				System.out.println("Program Exiting");
				flag = false;
			}
		} while (flag);// end do-while
	}// end displayMenu

	/**
	 * The stackTOpQueue method is basically a while loop that will pop links from
	 * the stack until empty and insert into the priority queue.
	 * 
	 * @param cStack
	 */
	public static void stackTOpQueue(Stack cStack) {
		while (cStack.isEmpty() == false) {
			pQueue.insert(cStack.pop());
		}
	}

	/**
	 * This method is used to get the user input for which Death Rate interval they
	 * would like to delete.
	 * <p>
	 * The method works in conjunction with two other methods.<br>
	 * 
	 * <pre>
	 * The method uses custom exceptions, and a numbers format exception to
	 * distinguish the validity of user input input.
	 * 
	 * Variables: x = [X,y] y = [x,Y]
	 * 
	 * <b>correct</b>: Will be set to true when everything is satisfied.
	 * 
	 * <b>notEmpty</b>: is used for the interval delete and will be sent either
	 * false or true, if true will print out the # of countries deleted within dr
	 * range, otherwise will tell the user the priority queue is empty. <p>
	 * </pre>
	 * 
	 * @see #checkFormat(String)
	 * @see #checkDR(String[], int[])
	 * 
	 */
	public static void drChoice() {

		Boolean notEmpty;
		boolean correct = false;
		String[] splitNum = new String[10];
		int[] drInterval = new int[2];

		do {
			System.out.println("     EXAMPLES");
			System.out.println(" ----------------- ");
			System.out.println("| [20,100]  VGOOD |");
			System.out.println("| [100,200] GOOD  |");
			System.out.println("| [200,350] FAIR  |");
			System.out.println(" ----------------- ");
			System.out.print("Enter a DR interval like: [x,y]: ");
			String userInput = scnr.nextLine();

			try {

				splitNum = checkFormat(userInput);
				notEmpty = checkDR(splitNum, drInterval);

				if (notEmpty == true) {
					System.out.println(pQueue.getCounter() + " countries in the priority queue with a DR range of ["
							+ drInterval[0] + "," + drInterval[1] + "] were deleted.\n");
				} else {
					System.out.println("There was nothing in the priority queue to delete.\n");
				}
				pQueue.setCounter(0);
				correct = true;
			} catch (IntervalException e) {
				System.out.println("Invalid interval, first number must be no bigger than the second.\n");
			} catch (NumberFormatException e) {
				System.out.println("Invalid interval, please enter a number.\n");
			} catch (IntervalFormatException e) {
				System.out.println(
						"Invalid interval format, please ensure you are using the proper formatting. EX.[X,Y]");
			}
		} while (correct == false);
		scnr = new Scanner(System.in);
	}

	/**
	 * This method will convert the string array splitNum to a double and cast those
	 * to int, this is done in case the user inputs numbers with decimals, they will
	 * get floored and converted to an int. If the method is unable to parse a
	 * double from the string it will throw a number format exception. If the x
	 * interval is greater than the y interval it will throw a interval exception.
	 * 
	 * <pre>
	 * <b>
	 * drInterval[0] = x (NOTE: drInterval[0] indicates the first index of the integer array)
	 * drInterval[1] = x (NOTE: drInterval[1] indicates the second index of the integer array)
	 * </b>
	 * </pre>
	 * 
	 * @param splitNum
	 * @param drInterval
	 * @return true or false if the the countries with a dr within range is deleted.
	 * @throws IntervalException
	 */
	public static Boolean checkDR(String[] splitNum, int[] drInterval) throws IntervalException {
		drInterval[0] = (int) Double.parseDouble(splitNum[0]);
		drInterval[1] = (int) Double.parseDouble(splitNum[1]);

		if (drInterval[0] > drInterval[1]) {
			throw new IntervalException();
		}

		Boolean notEmpty = pQueue.intervalDelete(drInterval[0], drInterval[1]);
		return notEmpty;
	}

	/**
	 * The method reads user input as a string, checks if the square brackets are
	 * used and removes the square brackets as, throws an error if they are not
	 * used. It will replace the square brackets, trim, and split the user input
	 * into a string array.
	 * 
	 * @param userInput
	 * @return the intervals in [x,y] as a string x and y, each will have its own
	 *         index.
	 * @throws IntervalFormatException
	 */
	public static String[] checkFormat(String userInput) throws IntervalFormatException {
		String num;
		String[] splitNum;
		userInput = userInput.trim(); // if user puts spaces before or after the input it will remove these

		if (!(userInput.charAt(0) == '[' && userInput.charAt(userInput.length() - 1) == ']')) {
			throw new IntervalFormatException();
		}

		num = userInput.replaceAll("\\[|\\]|,", " ");
		splitNum = num.trim().split(" ");

		if (splitNum.length <= 1 || splitNum.length > 2) {
			throw new IntervalFormatException();
		}
		return splitNum;
	}

	/**
	 * This methods prints out the header followed by dash line to indicate
	 * separation.
	 * <p>
	 * The second half of this method will print out each country in the stack or
	 * priority in the specified format.
	 * <p>
	 * <b>NAME CAPTIAL GDPPC CASERATE DEATHRATE POPDENS</b>
	 * <p>
	 * 
	 * @param type String either "stack" or "queue"
	 */
	public static void printData(String type) {
		System.out.println();
		String name = "Name";
		String capital = "Capital";
		String gdppc = "GDPPC";
		String cfr = "CFR";
		String cr = "CaseRate";
		String dr = "DeathRate";
		String den = "PopDensity";
		System.out.printf("%-39s %s %11s %11s %13s %13s %13s\n", name, capital, gdppc, cfr, cr, dr, den);
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------");
		if (type.equals("Stack")) {
			cStack.printStack(cStack.getTop());
		} else {
			pQueue.printpQueue(pQueue.getFirst());
		}
		System.out.println("\n");
	}// end printData
}
