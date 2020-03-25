package com.cgm.qanda.util;

import com.cgm.qanda.QnAApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = QnAApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)*/
public class TestValidationUtil {
	//validate input and out put string is validated and 
    @Test
    public void testValidateLength() {
        String input = "test String";
        //String exp = "test Str";
        boolean validate = ValidationUtil.validateLength(input);
        assertEquals(true, validate);
        //assertEquals(exp.length(),input.length());
        
    }
    
    @Test
    public void testValidateLength_empty() {
        String input = "test String";
        String exp = "";
        boolean validate = ValidationUtil.validateLength(input);
        assertEquals(true, validate);
        assertNotEquals(exp, input);
    }
    @Test
    public void testValidateLength_empty_empty() {
        String input = "";
        String exp = "";
        boolean validate = ValidationUtil.validateLength(input);
        assertEquals(true, validate);
        assertEquals(exp, input);
    }

    @Test
    public void testValidateLengthFailed() {
        String input = null;
        boolean validate = ValidationUtil.validateLength(input);
        assertEquals(false, validate);
    }

    @Test
    public void testValidateAnswerFormat() {
        String input = "this is input " + "\"" + "test";
        boolean validate = ValidationUtil.validateAnswerFormat(input);
        assertEquals(true, validate);
    }

    @Test
    public void testValidateAnswerFormatFailure() {
        String input = "this is wrong input";
        boolean validate = ValidationUtil.validateAnswerFormat(input);
        assertEquals(false, validate);
    }
}
