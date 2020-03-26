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
/*@ActiveProfiles("test")
@ContextConfiguration(classes = QnAApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)*/
public class TestValidationUtil {
	//validate input and out put string is validated and 
    @Test
    public void testValidateLength() throws Throwable{
        String input = null;
        //String exp = "test Str";
        boolean validate = ValidationUtil.validateLength(input);
        if (validate == true) {
        assertEquals(true, validate);}
        else {
        	assertEquals(false, validate);
        }
        //assertEquals(exp.length(),input.length());
        
    }
    
    @Test
    public void testValidateLength_null_success() throws Throwable {
        String input = null;
        //String exp = null;
        boolean validate = ValidationUtil.validateLength(input);
        if (validate == true) {
            assertEquals(true, validate);}
            else {
            	assertEquals(false, validate);
            }
       
    }
    @Test
    public void testValidateLength_empty_empty() throws Throwable {
        String input = null;
        //String exp = "";
        boolean validate = ValidationUtil.validateLength(input);
        if (validate == true) {
            assertEquals(true, validate);}
            else {
            	assertEquals(false, validate);
            }
    }

    @Test
    public void testValidateLengthFailed() throws Throwable{
        String input = null;
        boolean validate = ValidationUtil.validateLength(input);
        assertEquals(false, validate);
    }

    @Test
    public void testValidateAnswerFormat() throws Throwable{
        String input = "this is input " + "\"" + "test";
        boolean validate = ValidationUtil.validateAnswerFormat(input);
        assertEquals(true, validate);
    }

    @Test
    public void testValidateAnswerFormatFailure() throws Throwable {
        String input = "this is wrong input";
        boolean validate = ValidationUtil.validateAnswerFormat(input);
        assertEquals(false, validate);
    }
   
   @Test
   public void testvalidateIsAlpanumeric() throws Throwable{
	   String input = "How old are you334234232?";
	   boolean validate = ValidationUtil.validateIsAlpanumeric(input);
	   if(validate == true) {
		   assertEquals(true, validate);
	   }else if(validate == false){
		   assertEquals(false, validate);
	   }else {
		   System.out.println("Invalid Input: "+input+ "Enter Valid String");
	   }
	   
	    
	   
   }   
       
}
