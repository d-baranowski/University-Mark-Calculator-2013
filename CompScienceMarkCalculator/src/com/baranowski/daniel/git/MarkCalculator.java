package com.baranowski.daniel.git;

//Developed by: Daniel Baranowski
//Version: 1.0 24 October 2013
//Purpose: To keep a track of marks for a single Stage 1 student

public class MarkCalculator {
	//This is the array of constants for storing coursework weighting
	//{Programming 1, Programming 2, The Software Engineering, Computer Architecture, Mathematics for Computer Science, Website Design and Construction}
	final int[] COURSEWORK_WEIGHTING = {50,40,100,60,20,35,40,90,90,100};
	static final int MIN_MODULE_PASS_MARK = 40;
	static final int MIN_COMPENSATABLE_FAIL_MARK = 35;
	//Constant used to store the minimum stage average to pass the year
	static final int MIN_STAGE_AVERAGE = 40;
	//Constant used to store the maximal amount of module results at Compenstable fail to pass the year
	final int MAX_COMPENSATABLE_PASS = 2;
	//These are counters that will increase every time a corresponding module result is computed.
	static int compensatableFailCount; 
	static int failCount;
	static int passCount;

	
	public static void main(String[] args) {
		//Calling the studentSummary method. 
		Summary.studentSummary();
	}




	//This method given an array of student exam and coursework marks, returns an array of returned module marks.
	public int[] computeMarks(int[][] studentMarks){
		double[] computationModuleMarks = new double[StudentChart.NUMBER_OF_MODULES]; //Local variable used to round to the nearest whole number. 
		int[] localComputedModuleMarks = new int[StudentChart.NUMBER_OF_MODULES]; //Local variable used to store calculation results and than returning it.

		for(int i = 0; i < StudentChart.NUMBER_OF_MODULES; i++){
			if ((studentMarks[0][i] >= MIN_COMPENSATABLE_FAIL_MARK)&&(studentMarks[1][i] >= MIN_COMPENSATABLE_FAIL_MARK)){
				//Calculating the mark for each module according to the specification. round method is used to round the calculation result to the nearest whole number. 
				computationModuleMarks[i] = ((studentMarks[0][i] * COURSEWORK_WEIGHTING[i]) + (studentMarks[1][i] * (100 - COURSEWORK_WEIGHTING[i])));
				localComputedModuleMarks[i] = (int) Math.round(computationModuleMarks[i] / 100.0);
			}

			//If either coursework mark or exam mark is lover than 35 than the maximal mark for the module is capped at 35
			if ((studentMarks[0][i] < MIN_COMPENSATABLE_FAIL_MARK)||(studentMarks[1][i] < MIN_COMPENSATABLE_FAIL_MARK)){
				computationModuleMarks[i] = ((studentMarks[0][i] * COURSEWORK_WEIGHTING[i]) + (studentMarks[1][i] * (100 - COURSEWORK_WEIGHTING[i])));
				localComputedModuleMarks[i] = (int) Math.round(computationModuleMarks[i] / 100.0);
				if((localComputedModuleMarks[i] > MIN_COMPENSATABLE_FAIL_MARK) && (COURSEWORK_WEIGHTING[i] != 100))
					localComputedModuleMarks[i] = MIN_COMPENSATABLE_FAIL_MARK;
			}
		}
		//Method return the computed array. 
		return localComputedModuleMarks;
	}

	//Method used to compute module result according to rules from the specification. 
	public String[] computeModuleResult(int[]computedModuleMarks){
		String[] computedModuleResults = new String[StudentChart.NUMBER_OF_MODULES];

		for(int i = 0; i < StudentChart.NUMBER_OF_MODULES; i++){			
			//Pass, if the module mark is at least 40
			if (computedModuleMarks[i] >= MIN_MODULE_PASS_MARK){
				computedModuleResults[i] = "Pass";
				passCount++;
			}
			//Compensatable Fail, if the module mark is less than 40 but at least 35
			if ((computedModuleMarks[i] < MIN_MODULE_PASS_MARK)&&(computedModuleMarks[i] >=MIN_COMPENSATABLE_FAIL_MARK)){
				computedModuleResults[i] = "Compensatable Fail";
				compensatableFailCount++;
			}

			//Fail, otherwise
			if (computedModuleMarks[i] < MIN_COMPENSATABLE_FAIL_MARK){
				computedModuleResults[i] = "Fail";
				failCount++;
			}

		}

		//Returns the string array as a result. 
		return computedModuleResults;		
	}

	//Given an array of student exam and coursework marks, returns a Stage Result for that student.
	public String computeResult(){
		String result = "initialized"; //Line used to avoid unresolved variable compilation error. 
		//Pass, if all modules are recorded as a Pass
		if ((passCount == StudentChart.NUMBER_OF_MODULES) && (StudentChart.stageAverage >= MIN_STAGE_AVERAGE))
			result =  "Pass";
		//Pass By Compensation, if the Stage average is at least 40,  no module is recorded as a Fail, and there are one or two modules totalling at most 40 credits recorded as a Compensatable Fail.
		if((StudentChart.stageAverage >= MIN_STAGE_AVERAGE)&&(failCount == 0)){
			if((compensatableFailCount == 1)||(compensatableFailCount == MAX_COMPENSATABLE_PASS))
				result = "Pass By Compensation";
		}

		//Fail, otherwise
		if((failCount > 0)||(compensatableFailCount > MAX_COMPENSATABLE_PASS)||(StudentChart.stageAverage < MIN_STAGE_AVERAGE))
			result = "Fail";
		//Returns a result in form of a String "Pass", "Pass By Compensation" or "Fail"
		return result;
	}

	//Method used to getWeighting in case the values from the array is required in any other class. 
	public int getWeighting(int index){
		return COURSEWORK_WEIGHTING[index];
	}

}
