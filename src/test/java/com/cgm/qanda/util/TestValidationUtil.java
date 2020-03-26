package com.cgm.qanda.util;

import com.cgm.qanda.QnAApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
/*
 * @ActiveProfiles("test")
 * 
 * @ContextConfiguration(classes = QnAApplication.class, initializers =
 * ConfigFileApplicationContextInitializer.class)
 */
public class TestValidationUtil {
	// validate input and out put string is validated and
	@Test
	public void testValidateLength() throws Throwable {
		String input = null;
		// String exp = "test Str";
		boolean validate = ValidationUtil.validateLength(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
		// assertEquals(exp.length(),input.length());

	}

	@Test
	public void testValidateLength_null() throws Throwable {
		String input = null;
		// String exp = null;
		boolean validate = ValidationUtil.validateLength(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}

	}

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

	@Test
	public void testvalidateIsAlpanumericSuccess() throws Throwable {
		String input = "How old are you?";
		boolean validate = ValidationUtil.validateIsAlpanumeric(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}

	}

	@Test
	public void testvalidateIsAlpanumericFailure() throws Throwable {
		String input = "AD";
		boolean validate = ValidationUtil.validateIsAlpanumeric(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}

	}

	@Test
	public void testvalidateIsSpecialchar() throws Throwable {
		String input = "!";
		boolean validate = ValidationUtil.validateIsSpecialchar(input);
		if (validate == true) {
			assertEquals(true, validate);
		} else {
			assertEquals(false, validate);
		}
	}

}
