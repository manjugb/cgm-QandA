package com.cgm.qanda.util;
/**
 * TestValidationUtil.java - This class defines the validation of input as a  String,Validation of input and format with passing positive and negative values
 * 
 * @author Manjunath Golla Bala
 * @version 1.0
 * 
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cgm.qanda.QnAApplication;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(Parameterized.class)
//@RunWith(JUnitParamsRunner.class)

// @ActiveProfiles("test")

//@ContextConfiguration(classes = QnAApplication.class, initializers = ConfigFileApplicationContextInitializer.class)

public class TestValidationUtil {

	// private ValidationUtil testvalid = new ValidationUtil();
	// validate input and out put string is validated and

	/*
	 * @ParameterizedTest
	 * 
	 * @CsvFileSource(resources = "src/test/resources/QuestionData.csv",
	 * numLinesToSkip = 1)
	 * 
	 * @DisplayName("ValidateLength using csv file") void testValidateLength(String
	 * input) { //String input = null; // String exp = "test Str";
	 * 
	 * boolean validate = ValidationUtil.validateLength(input); if (validate ==
	 * true) { assertEquals(true, validate); } else { assertEquals(false, validate);
	 * } // assertEquals(exp.length(),input.length());
	 * 
	 * }
	 * 
	 * @ParameterizedTest
	 * 
	 * @ValueSource(ints = {1,2,3}) public void sampleTest(int a,int b) { int c; c =
	 * testvalid.add(a, b); assertThat(c).isEqualTo(3); }
	 * 
	 * @ParameterizedTest
	 * 
	 * @ValueSource(strings = {"Hello", "JUnit5"})
	 * 
	 * @DisplayName("Testing validlegth using ParameterTest") public void
	 * testValidateLength_null(String input) { //String input = null; // String exp
	 * = null; boolean validate = testvalid.validateLength(input); if (validate ==
	 * true) { assertEquals(true, validate); } else { assertEquals(false, validate);
	 * }
	 * 
	 * }
	 */

	/**
	 * This test verifies the input string is null false if else true Extracts the
	 * user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 * @param boolean as false if input not contain null and boolean as true if input contains String between 0 and 255
	 */

	@Test
	public void testValidateLength_empty_null() throws Throwable {
		String input = null;
		// String exp = "";
		boolean validate = ValidationUtil.validateLength(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

	/**
	 * This test verifies the input string is empty true if else false Extracts the
	 * user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 * @param       boolean as false if 'input' not contain null and boolean as true
	 *              if 'input' contains String between 0 and 255
	 */

	@Test
	public void testValidateLength_empty_string() throws Throwable {
		String input = "";
		// String exp = "";
		boolean validate = ValidationUtil.validateLength(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

	/**
	 * This test verifies the input string is not null and with length less than 256
	 * true if else false Extracts the user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 * @param       boolean as false if 'input' not contain null and boolean as true
	 *              if 'input' contains String between 0 and 255
	 */

	@Test
	public void testValidateLengthLessThan256() throws Throwable {
		String input = "Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications.";
		boolean validate = ValidationUtil.validateLength(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

	/**
	 * This test verifies the input string is not null and with length greater than
	 * 256 false if else true Extracts the user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 * @param       boolean as false if 'input' not contain null and boolean as true
	 *              if 'input' contains String between 0 and 255
	 */

	@Test
	public void testValidateLengthGreaterThan256() throws Throwable {
		String input = "Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications.ava-based framework used to create a micro Service. It is developed by Pivotal Team and ";
		boolean validate = ValidationUtil.validateLength(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

	/**
	 * This test verifies correct format of input string Extracts the user's
	 * question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 */

	@Test
	public void testValidateAnswerFormat() throws Throwable {
		String input = "this is input " + "\"" + "test";
		boolean validate = ValidationUtil.validateAnswerFormat(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

	/**
	 * This test verifies wrong format of input string Extracts the user's question
	 * from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 */

	@Test
	public void testValidateAnswerFormatFailure() throws Throwable {
		String input = "this is wrong input";
		boolean validate = ValidationUtil.validateAnswerFormat(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

	/**
	 * This test verifies of input string is alpha with special character Extracts
	 * the user's question from Precondition: 'input' should contain at least one
	 * element, the question.
	 *
	 * @param input the command-line arguments.
	 */

	@Test
	public void testvalidateIsAlphaSpecialSuccess() throws Throwable {
		String input = "How old are you?";
		boolean validate = ValidationUtil.validateIsAlphaSpecial(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

	/**
	 * This test verifies of input string is not a alpha with special character
	 * Extracts the user's question from Precondition: 'input' should contain at
	 * least one element, the question.
	 *
	 * @param input the command-line arguments.
	 */

	@Test
	public void testvalidateIsAlphaSpecial() throws Throwable {
		String input = "How old are you?1234#$#$#$#";
		boolean validate = ValidationUtil.validateIsAlphaSpecial(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}

	}

	/**
	 * This test verifies of input string is not a alpha with special character
	 * Extracts the user's question from Precondition: 'input' should contain at
	 * least one element, the question.
	 *
	 * @param input the command-line arguments.
	 */

	@Test
	public void testvalidateIsAlphaSpecialFailure() throws Throwable {
		String input = "123";
		boolean validate = ValidationUtil.validateIsAlphaSpecial(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}

	}

	/**
	 * This test verifies of input string is an empty string Extracts the user's
	 * question from Precondition: 'input' should contain at least one element, the
	 * question.
	 *
	 * @param input the command-line arguments.
	 */
	@Test
	public void testvalidateEmptyStringSucces() throws Throwable {
		String input = "";
		boolean validate = ValidationUtil.validateEmpty(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

	/**
	 * This test verifies of input string is an nonempty string Extracts the user's
	 * question from Precondition: 'input' should contain at least one element, the
	 * question.
	 *
	 * @param input the command-line arguments.
	 */

	@Test
	public void testvalidateEmptyStringFailure() throws Throwable {
		String input = "how are you?";
		boolean validate = ValidationUtil.validateEmpty(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}

	}

	/**
	 * This test verifies of input string is an nonempty string with lessthan 256
	 * chars Extracts the user's question from using regular expression
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 */

	@Test
	public void testValidateLengthLessThan256reg() throws Throwable {
		String input = "Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications.";
		boolean validate = ValidationUtil.validateLengthRegex(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

	/**
	 * This test verifies of input string is an nonempty string with greater than
	 * 256 chars Extracts the user's question from using regular expression
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param input the command-line arguments.
	 */

	@Test
	public void testValidateLengthGreaterThan256reg() throws Throwable {
		String input = "Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications.ava-based framework used to create a micro Service. It is developed by Pivotal Team and ";
		boolean validate = ValidationUtil.validateLengthRegex(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

}
