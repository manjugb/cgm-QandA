package com.cgm.qanda.util;

import java.util.regex.Pattern;

/**
 * ValidationUtil.java - This class defines the validation of input as a  String,Validation of input and format
 * 
 * @author Manjunath Golla Bala
 * @version 1.0
 * 
 */

public class ValidationUtil {

	/**
	 * Extracts the user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 * @return boolean as false if 'input' not contains and more than 256 and boolean as true if 'input' contains between 0 and 255
	 */

	public static boolean validateLength(String input) {
		if (input == null || input.length() > 256) {
			return false;
		}
		return true;
	}

	/**
	 * Extracts the user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 * @return boolean as false if not 'input' contains "\"" and boolean as true if  'input' contains "\""
	 */
	public static boolean validateAnswerFormat(String input) {
		if (input.contains("\"")) {
			return true;
		}
		return false;
	}

	/**
	 * Extracts the user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 * @return boolean as false if not 'input' contains regular expression("^[A-Za-z0-9]+") and  boolean as true if  'input' contains regular expression
	 *         ("[A-Za-z0-9]+")
	 */
	public static boolean validateIsAlpanumeric(String input) {
		if (input.matches("[A-Za-z]+?") || input.contains("?")) {
			return true;
		}else
		return false;
	}

	
	
	/**
	 * Extracts the user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments 
	 * @return boolean as true if not 'input' contains is not null or not empty string and boolean as true if input is null or empty
	 */

	public static  boolean validateEmpty(String input) {
		if(input != null && !input.isEmpty())
            return false;
        return true;
	}
	//this is the sample test method to check ParameterisedTest
	public int add(int a,int b) {
		int c = a + b;
		return c;
		
	}
	
		
	
	/**
	 * Extracts the user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 * @return boolean as false if 'input' not contains and more than 256 and boolean as true if 'input' contains between 0 and 255
	 */

	public static boolean validateLengthRegex(String input) {
		if (input == null || input.length() > 256) {
			return false;
		}else if (input.matches("^.{0,255}$")) {
			
			return true;
		}
		return false;
		
	}
	

}
