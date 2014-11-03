package com.baranowski.daniel.git;


//Developed by: Daniel Baranowski
//Version: 1.0 24 October 2013
//Purpose: To keep a track of marks for a single Stage 1 student


public class StudentChart {
	MarkCalculator calc = new MarkCalculator();
	//This variable needs to be changed if the number of modules is to be changed. 
	//Remember to update COUSEWORK_WEIGHTING array in MarkCalculator according to the number of modules. 
	//Also remember to update the amount of bar objects and modules array in draw method if the number of modules exceeds 10
	public static final int NUMBER_OF_MODULES = 6;
	public static final int MIN_FIRST_CLASS_MARK = 70;
	//This is the array for holding Student's coursework and exam marks.
	static int[][] studentMarks = new int[2][NUMBER_OF_MODULES]; 
	//Array used to store module results: "Pass" & "Fail" & "compensatableFail"
	static String[] computedModuleResult;
	//Double used to store stage average
	static double stageAverage;
	//Array used to store results of the computeMarks method
	static int[] computedModuleMarks = new int[NUMBER_OF_MODULES];

	//The constructor of StudentChart class
	public StudentChart(int[][] studentMarks){
		//Computing of module marks using computeMarks method with studentMarks as parameter
		computedModuleMarks = (int[]) calc.computeMarks(studentMarks);
		//Computing of module result using computeModuleResult method with computedModuleMarks as a parameter 
		computedModuleResult = calc.computeModuleResult(computedModuleMarks);

		//Calculate stageAverage
		for (int i = 0; i < NUMBER_OF_MODULES; i++){
			stageAverage = stageAverage + computedModuleMarks[i];
		}
		stageAverage = stageAverage / NUMBER_OF_MODULES;

		//Calls the method draw when the constructor is called
		draw(computedModuleMarks);
		//Prints the table when the constructor is called
		printSummary(studentMarks);

		//Computing stage result using computeResult method
		String result = calc.computeResult();

		//Outputting the stage result
		System.out.println("Stage Average is: " + stageAverage);
		System.out.println("Stage Result: "+ result);

	}

	//Method used to draw a bar chart
	public static void draw(int[] computedModuleMarks){
		//Create objects of class Bar for each module. They will represent each of the modules on the bar chart.
		Bar module1 = new Bar();
		Bar module2 = new Bar();
		Bar module3 = new Bar();
		Bar module4 = new Bar();
		Bar module5 = new Bar();
		Bar module6 = new Bar();
		Bar module7 = new Bar();
		Bar module8 = new Bar();
		Bar module9 = new Bar();
		Bar module10 = new Bar();
		final Bar MODULES[] = {module1,module2,module3,module4,module5,module6,module7,module8,module9,module10};
		//Bars used to represent Y and X axis for the bar chart
		Bar axisX = new Bar();
		Bar axisY = new Bar();
		//This variable is used to adjust bar sizes according to the number of modules
		double sizePercentage =  2.4 - (0.17 * NUMBER_OF_MODULES); 

		//For loop used to minimize the code for drawing the bar chart. 
		for(int i = 0; i < NUMBER_OF_MODULES; i++)
		{
			//Pass marks will be represented as green bars
			MODULES[i].changeColour(Colour.GREEN);
			//Failing marks should be coloured red 
			if(computedModuleMarks[i] < MarkCalculator.MIN_COMPENSATABLE_FAIL_MARK)
				MODULES[i].changeColour(Colour.RED);
			//Compensatable fails are yellow
			if((computedModuleMarks[i] < MarkCalculator.MIN_MODULE_PASS_MARK)&&(computedModuleMarks[i] >=MarkCalculator.MIN_COMPENSATABLE_FAIL_MARK))
				MODULES[i].changeColour(Colour.YELLOW);
			//First class marks (70 or over) should be coloured Magenta.
			if(computedModuleMarks[i] >= MIN_FIRST_CLASS_MARK)
				MODULES[i].changeColour(Colour.MAGENTA);

			//The bar will be as tall as the mark for the module it is representing
			MODULES[i].changeSize((int)Math.round(20 * sizePercentage), computedModuleMarks[i]);
			//The marks will be moved right so they aren't drawn on top of each other
			MODULES[i].moveHorizontal((int)Math.round(30 * sizePercentage) * i);
			//This is used to flip the chart. 
			MODULES[i].moveVertical(100 - computedModuleMarks[i]);
			//Shows the chart
			MODULES[i].makeVisible();
		}

		//Code used to represent X and Y axis of the chart 
		axisY.changeSize(2,300);
		axisY.changeColour(Colour.BLACK);
		axisY.moveVertical(-200);
		axisY.makeVisible();
		axisX.changeSize(300, 2);
		axisX.moveVertical(100);
		axisX.changeColour(Colour.BLACK);
		axisX.makeVisible();
	}


	//Method used to print a neat table of results
	public static void printSummary(int[][] studentMarks){
		System.out.println("____________________________________________________________________________________________________________________________________________");
		System.out.println("||\t\tExam Mark \t \t \tCoursework Mark \t \t \tComputed Module Mark \t \tResult");
		System.out.println("||__________________________________________________________________________________________________________________________________________");
		for(int i = 0; i < NUMBER_OF_MODULES; i++){
			System.out.println("||Module " + (i + 1) + "\t||" + studentMarks[1][i] + "\t\t\t\t||" + studentMarks[0][i] + "\t\t\t\t\t||" +  computedModuleMarks[i] + "\t\t\t\t||" + computedModuleResult[i]);
		}
		System.out.println("||__________________________________________________________________________________________________________________________________________");
	}
}