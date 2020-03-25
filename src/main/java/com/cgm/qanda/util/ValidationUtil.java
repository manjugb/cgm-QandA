package com.cgm.qanda.util;

/**
 * ValidationUtil.java - This class defines the validation of input as a
 * String,Validation of input and format
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
	 * @return boolean as false if 'input' not contains and more than 256 and boolean as true if 'input' contains between 0 and 25
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
	 * @return boolean as false if not 'input' contains regular expression("^[0-9]+[a-zA-Z][a-zA-Z0-9]*$") and  boolean as true if  'input' contains regular expression
	 *         ("^[0-9]+[a-zA-Z][a-zA-Z0-9]*$")
	 */
	public static boolean validateIsAlpanumeric(String input) {
		if (input.contains("^[0-9]+[a-zA-Z][a-zA-Z0-9]*$")) {
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
	 * @return boolean as false if not 'input' contains regular expression (("^[a-zA-Z0-9]+$")) and  boolean as true if  'input' contains regular expression
	 *         (("^[a-zA-Z0-9]+$"))
	 */

	public static boolean validateIsNonAlpanumeric(String input) {
		if (input.contains("^[a-zA-Z0-9]+$")) {
			return true;
		}
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

	public static boolean validateEmpty(String input) {
		if(input != null && !input.isEmpty())
            return false;
        return true;
	}


}
