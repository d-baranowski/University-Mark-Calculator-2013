package com.baranowski.daniel.git;

//Developed by: Daniel Baranowski
//Version: 1.0 24 October 2013
//Purpose: To keep a track of marks for a single Stage 1 student


import java.util.Scanner;

//Summary class with a studentSummary method that allows the user to input exam and coursework marksand 
//then uses a StudentChart object to produce a thin bar chart and the corresponding table.
public class Summary {
	
	static Scanner keyboard = new Scanner(System.in);

	//This static method will let the user input data into the studentMarks array
	public static void studentSummary(){
		final String[] MODULE_TITLE = {"Programming 1", "Programming 2", "The Software Engineering", "Computer Architecture", "Mathematics for Computer Science", "Website Design and Construction"};
		MarkCalculator calc = new MarkCalculator();
		System.out.println("WELCOME TO MARKS TRACK");
		System.out.println("This program will help you to keep a track of marks for a single Stage 1 student");
		System.out.println("____________________________________________________________________________________________________");
		System.out.println("");
		System.out.println("The system will let you input the student marks");
		System.out.println("____________________________________________________________________________________________________");
		System.out.println("");
		
		for (int i = 0; i < StudentChart.NUMBER_OF_MODULES; i++){			
			System.out.print("Please enter the coursework mark for Module " + (i + 1) + " "  +  MODULE_TITLE[i]  + ": ");
			StudentChart.studentMarks[0][i] = validation(keyboard.nextLine());
			//If the coursework weighting is 100 this means that there is no exam mark for that module. 
			if (calc.getWeighting(i) < 100){
				System.out.print("Please enter the exam mark for Module " +  + (i + 1) + " "  +  MODULE_TITLE[i]  + ": ");
				StudentChart.studentMarks[1][i] = validation(keyboard.nextLine()); 
			}
			else 
				StudentChart.studentMarks[1][i] = 0;

		}
		//Call student chart constructor
		new StudentChart(StudentChart.studentMarks);
	}

	//Method used to validate user input in order to avoid crashing the program and minimising human error. 
	public static int validation(String validated){
		//initialise the returned variable
		int returned = 999;
		//If the input is an integer
		if(isInteger(validated)){
			
			//If the number meets the validation rules 
			if((Integer.valueOf(validated) >= 0) && (Integer.valueOf(validated) <= 100)){
				returned = Integer.valueOf(validated);
			}
			
			//If the number starts with 0 the user will be asked to input the number again. 
			if((validated.startsWith("0") && (Integer.valueOf(validated) > 0))){
				System.out.println("ERROR!");
				System.out.println("The number cannot start with 0. Please input the number again");
				returned = validation(keyboard.nextLine());
			}

			//if the number is smaller than 0 give an error
			if(Integer.valueOf(validated) < 0){
				System.out.println("ERROR!");
				System.out.println("The mark cannot be smaller than 0. Please input mark again");
				returned = validation(keyboard.nextLine());
			}

			//If its bigger than 100 give an error
			if(Integer.valueOf(validated) > 100){
				System.out.println("ERROR!");
				System.out.println("Marks cannot be larger than 100. Please insert a smaller number");
				returned = validation(keyboard.nextLine());
			}


		}

		//If the input is not a number, give an error 
		if(isInteger(validated) == false){
			System.out.println("ERROR!");
			System.out.println("The input value is not a number. Please input a number.");
			returned = validation(keyboard.nextLine());
		}

		return returned;


	}

	//Method used to validate input to the program to avoid crashing the program with accidental string input to the integer field 
	public static boolean isInteger( String input )  //Based on the code found at: http://bytes.com/topic/java/answers/541928-check-if-input-integer
	{  
		try  
		{  
			Integer.parseInt( input );  
			return true;  
		}  
		catch(NumberFormatException nFE)  
		{  
			return false;  
		}  
	}  
}


