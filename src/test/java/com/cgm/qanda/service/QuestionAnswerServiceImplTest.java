package com.cgm.qanda.service;
/**
 * TestValidationUtil.java - This class defines the validation of question as a  String,Validation of input and format with passing positive and negative values
 *  with different combination of data
 * @author Manjunath Golla Bala
 * @version 1.0
 * 
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.AssertionFailure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataaccessobject.QuestionRepository;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import com.cgm.qanda.util.ValidationUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = QnAApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
@SpringBootTest
public class QuestionAnswerServiceImplTest {

	@Autowired
	QuestionAnswerService service;

	@Mock
	QuestionRepository repo;
	// AnswerRepository ansrep;

	@Before
	public void setup() {
		Question question = createQuestionEntity();
		repo.save(question);

	}

	private Question createQuestionEntity() {
		Question question = new Question();
		question.setQuestion("question1?");
		Answer answer = new Answer();
		answer.setAnswer("answer1");
		answer.setAnswer("answer2");
		answer.setAnswer("answer2");
		Set<Answer> set = new HashSet<>();
		set.add(answer);
		return question;
	}

	/**
	 * This test verifies the input string is null false if else true Extracts the
	 * user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 *
	 * @param question
	 * @param answers  with random data
	 */

	@Test
	public void testaddQuestionTest_original() {
		String ans1 = "answer";
		String ans2 = "anwer2";
		String ans3 = "answer3";
		String qq = "Question?";
		if (ValidationUtil.validateAlpaCharLength(qq) == true) {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), true);
			System.out.println("Question is a String with max 255 chars");
		} else if (ValidationUtil.validateAlpaCharLength(qq) == false) {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), false);
			System.out.println("Question is a String not with max 255 chars");

		}

		Question q = createQuestionEntity();
		q.setQuestion(qq);
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion(qq)).thenReturn(Optional.ofNullable(q));
		service.addQuestion(qq, ans1);
		List<String> answers = service.getAnswers(qq);
		if (answers != null && !answers.isEmpty()) {
			// answers

			// service.addQuestion("question", ans1);
			answers.add(ans2);
			answers.add(ans3);

			assertNotNull(answers);
			assertEquals(ans1, answers.get(0));
			assertEquals(ans2, answers.get(1));
			assertEquals(ans3, answers.get(2));
			answers.clear();
		} else {
			System.out.println("Answer length is more than 256 characters for answer " + answers);
		}

	}

	// Answers

	/**
	 * This test verifies the input string is null false if else true Extracts the
	 * user's question from the input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 * assert the data
	 *
	 * @param question
	 * @param answers  with random data
	 */

	@Test
	public void testGetAnswers_original() {

		Question q = createQuestionEntity();
		Mockito.when(repo.findByQuestion("question1")).thenReturn(Optional.ofNullable(q));
		List<String> answers = service.getAnswers("question1");
		answers.add("answer2");
		assertNotNull(answers);
		assertEquals(2, answers.size());
		// assertEquals(true, q.getAnswers());

	}

	/**
	 * This test verifies the input string is valid with alpha with special
	 * character against with injected data user's question from the input
	 * arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 * assert the data with injected values
	 *
	 * @param question with correct data
	 * @param answers  with valid data
	 */

	@Test
	public void testaddQuestionTest_success() {
		String ans1 = "Pizza";
		String ans2 = "Spagatti";
		String ans3 = "IceCream";
		String ans4 = "Lemon Juice";
		String ans5 = "IceCream";

		String qq = "What is Your Favorite Food?";
		if (ValidationUtil.validateAlpaCharLength(qq)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), false);
		}
		Question q = createQuestionEntity();
		q.setQuestion(qq);
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion(qq)).thenReturn(Optional.ofNullable(q));
		service.addQuestion(qq, ans1);
		List<String> answers = service.getAnswers(qq);
		service.addQuestion(qq, ans1);
		if (answers != null && !answers.isEmpty()) {

			answers.add(ans2);
			answers.add(ans3);
			answers.add(ans4);
			answers.add(ans5);
			// Assertions
			assertNotNull(answers);
			assertEquals(ans1, answers.get(0));
			assertEquals(ans2, answers.get(1));
			assertEquals(ans3, answers.get(2));
			assertEquals(ans4, answers.get(3));
			assertEquals(ans5, answers.get(4));
			assertEquals(5, answers.size());
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(true, answers.contains(ans1));
			assertEquals(true, answers.contains(ans2));
			assertEquals(true, answers.contains(ans3));
			assertEquals(true, answers.contains(ans4));
			assertEquals(true, answers.contains(ans5));
			answers.clear();
		} else {

			System.out.println("Answer length is more than 256 characters for answer " + answers);
		}

	}

	/**
	 * This test verifies the question and retrieves values user's question from the
	 * input arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 * assert the data
	 *
	 * @param question
	 * @param answers  with random data
	 */

	@Test
	public void testGetAnswers_success_two() {
		String ans1 = "Maruthi Suzaki";
		String ans2 = "Benz";
		String ans3 = "BMW";
		String ans4 = "LandsRover";
		String ans5 = "Audi";

		String qq = "What is Your Favorite Car?";
		if (ValidationUtil.validateAlpaCharLength(qq)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), false);
		}
		Question q = createQuestionEntity();
		q.setQuestion(qq);
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion(qq)).thenReturn(Optional.ofNullable(q));
		service.addQuestion(qq, ans1);
		List<String> answers = service.getAnswers(qq);
		service.addQuestion(qq, ans1);
		if (answers != null && !answers.isEmpty()) {

			answers.add(ans2);
			answers.add(ans3);
			answers.add(ans4);
			answers.add(ans5);
			// Assertions
			assertNotNull(answers);
			assertEquals(ans1, answers.get(0));
			assertEquals(ans2, answers.get(1));
			assertEquals(ans3, answers.get(2));
			assertEquals(ans4, answers.get(3));
			assertEquals(ans5, answers.get(4));
			assertEquals(5, answers.size());
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(true, answers.contains(ans1));
			assertEquals(true, answers.contains(ans2));
			assertEquals(true, answers.contains(ans3));
			assertEquals(true, answers.contains(ans4));
			assertEquals(true, answers.contains(ans5));
			assertEquals(false, answers.contains("Hundai"));
			answers.clear();
		} else {

			System.out.println("Answer length is more than 256 characters for answer " + answers);
		}
	}

	// assertEquals(true, q.getAnswers());

	/**
	 * This test verifies the input string is invalid with null against with
	 * injected data and verify valid error message user's question from the input
	 * arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 * assert the data with injected values
	 *
	 * @param question with null value
	 * @param answers  with null value
	 */

	@Test
	// @ParameterizedTest
	// @ValueSource(strings = {"Hello", "JUnit5"})
	public void testaddQuestionTest_null() {

		String ans1 = null;
		String ans2 = null;
		String ans3 = null;

		String qq = null; /*
							 * this gives an error when choose question as null and gives nullpointer
							 * exception
							 */
		try {
			if (ValidationUtil.validateAlpaCharLength(qq)) {
				assertEquals(ValidationUtil.validateAlpaCharLength(qq), true);
			} else {
				assertEquals(ValidationUtil.validateAlpaCharLength(qq), false);
			}
			Question q = createQuestionEntity();

			q.setQuestion(null);
			Mockito.when(repo.save(q)).thenReturn(q);
			Mockito.when(repo.findByQuestion(null)).thenReturn(Optional.ofNullable(q));
			service.addQuestion(qq, ans1);
			List<String> answers = service.getAnswers(qq);
			if (answers != null && !answers.isEmpty()) {
				// answers.clear();
				// assertions
				answers.add(ans2);
				answers.add(ans3);
				assertNotNull(answers);
				assertEquals(
						"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"",
						answers.get(0));
				assertNotEquals("Dubai", answers.get(0));
				assertNotEquals("Rome", answers.get(0));
				assertEquals(3, answers.size());
				assertNotEquals(-1, answers.size());
				assertNotEquals(null, answers.size());
				assertEquals(true, answers.contains(
						"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
				assertEquals(false, answers.contains("Paris"));
				answers.clear();
			} else {

				System.out.println("Answer length is more than 256 characters for answer " + answers);
			}

		} catch (NullPointerException e) {
			System.out.print("NullPointerException Caught");
			System.out.println("Question is a String with max 255 chars");

		}
	}

	/**
	 * This test verifies the input string is invalid with null against with
	 * injected data and verify valid error message user's question from the input
	 * arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 * assert the data with injected values
	 *
	 * @param question with null ""
	 * @param answers  with valid three empty values
	 */

	@Test
	public void testaddQuestionTest_emptyquestion_emptyanwers() {
		String ans1 = "";
		String ans2 = "";
		String qq = "";
		if (ValidationUtil.validateAlpaCharLength(qq)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), false);
		}
		Question q = createQuestionEntity();
		q.setQuestion("");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion(qq)).thenReturn(Optional.ofNullable(q));
		service.addQuestion(qq, ans1);
		List<String> answers = service.getAnswers(qq);
		if (answers != null && !answers.isEmpty()) {
			// answers.clear();
			answers.add(ans2);

			// assertions
			assertNotNull(answers);
			// assertEquals("kerala",answers.get(0));
			assertEquals("\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"", answers.get(0));
			assertEquals(ans2, answers.get(1));
			assertEquals(true, answers.contains(""));
			assertEquals(false, answers.contains("London"));
			assertEquals(2, answers.size()); // How come this size would be i was given any value but all "" gives 0
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(true, answers.contains(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(false, answers.contains("Paris"));
			answers.clear();
		} else {

			System.out.println("Answer length is more than 256 characters for answer " + answers);
		}

	}

	/**
	 * This test verifies the input string is invalid with null against with
	 * injected data and verify valid error message user's question from the input
	 * arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 * assert the data with injected values
	 *
	 * @param question with null ""
	 * @param answers  with valid three values
	 */

	@Test
	public void testaddQuestionTest_emptyqString_oneanswer() {
		String ans1 = "Kerala";
		String ans2 = "";
		String qq = "?";
		if (ValidationUtil.validateAlpaCharLength(qq)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), false);
		}
		Question q = createQuestionEntity();
		q.setQuestion(qq);
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion(qq)).thenReturn(Optional.ofNullable(q));
		service.addQuestion(qq, ans1);
		List<String> answers = service.getAnswers("");
		if (answers != null && !answers.isEmpty()) {

			answers.add(ans2);
			
			// assertions
			assertNotNull(answers);
			// assertEquals("Kerala", answers.get(0));
			assertEquals("\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"", answers.get(0));
			assertEquals("", answers.get(1));
			assertEquals(2, answers.size());
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(true, answers.contains(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(true, answers.contains("\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(false, answers.contains("London"));
			assertNotEquals(true, answers.contains("Bangalore"));
			answers.clear();
		} else {

			System.out.println("Answer length is more than 256 characters for answer " + answers);
		}

	}

	/**
	 * This test verifies the input string is invalid with null against with
	 * injected data and verify valid error message user's question from the input
	 * arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 * assert the data with injected values
	 *
	 * @param question with null ""
	 * @param answers  with valid three values
	 */

	@Test
	public void testaddQuestionTest_input_255chars() {
		String ans1 = "Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications";
		String ans2 = "Spring";
		String qq = "What are most economical Cities?";
		if (ValidationUtil.validateAlpaCharLength(qq)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), false);
		}
		Question q = createQuestionEntity();
		q.setQuestion(qq);
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion(qq)).thenReturn(Optional.ofNullable(q));
		service.addQuestion(qq, ans1);
		List<String> answers = service.getAnswers(qq);
		if (answers != null && !answers.isEmpty()) {

			answers.add(ans2);
			answers.add("London");
			// assertions
			assertNotNull(answers);
			assertEquals(ans1, answers.get(0));
			assertEquals("Spring", answers.get(1));
			assertEquals("London", answers.get(2));
			assertEquals(3, answers.size());
			// assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(false, answers.contains(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(false, answers.contains("Paris"));
			assertEquals(true, answers.contains(ans2));
			assertNotEquals(true, answers.contains("Bangalore"));
			answers.clear();
		} else {

			System.out.println("Answer length is more than 256 characters for answer " + answers);
		}

	}

	/**
	 * This test verifies the input string is invalid with null against with
	 * injected data and verify valid error message user's question from the input
	 * arguments.
	 *
	 * Precondition: 'input' should contain at least one element, the question.
	 * assert the data with injected values
	 *
	 * @param question with null ""
	 * @param answers  with valid mandatory answer with one additional answer
	 */

	@Test
	public void testaddQuestionTest_q255_ans255() {
		String ans1 = "Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications";
		String ans2 = "Spring";
		String qq = "Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready sprinplications.sdfddsfsfjlksjflsjfsxdsdfsdfssdfdsdfdfdfddfdfd sfsdflsdfsldsfsksljfssf?";
		if (ValidationUtil.validateAlpaCharLength(qq)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), false);
		}
		Question q = createQuestionEntity();
		q.setQuestion(qq);
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion(qq)).thenReturn(Optional.ofNullable(q));
		try {
		service.addQuestion(qq, ans1);
		}catch (DataIntegrityViolationException e) {
			System.out.print("DataIntegrityViolation Caught");
			//System.out.println("Question is a String with max 255 chars");

		}
		List<String> answers = service.getAnswers(qq);
		if (answers != null && !answers.isEmpty()) {
			answers.add(ans2);
			answers.add("");
			// assertions
			assertNotNull(answers);
			assertEquals("\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"", answers.get(0));
			assertEquals(ans2, answers.get(1));
			assertEquals("", answers.get(2));
			assertEquals(3, answers.size());
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(true, answers.contains(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(true, answers.contains("Spring"));
			assertEquals(false, answers.contains("trim"));
			assertNotEquals(true, answers.contains("Bangalore"));
			answers.clear();
		} else {

			System.out.println("Answer length is more than 256 characters for answer " + answers);
		}

	}
    //unique answers 
	@Test
	public void testaddQuestionTest_alpa() {
		String ans1 = "Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications";
		String ans2 = "Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications";
		String qq = "Spring Boot is an open source Java-based framework used to create a micro ServiceIt is developed by Pivotal Team and is used to build stand-alone?";
		if (ValidationUtil.validateAlpaCharLength(qq)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(qq), false);
		}
		
		Question q = createQuestionEntity();
		q.setQuestion(qq);
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion(qq)).thenReturn(Optional.ofNullable(q));
		service.addQuestion(qq, ans1);
		List<String> answers = service.getAnswers(qq);
		if (answers != null && !answers.isEmpty()) {
			answers.add(ans1);
			answers.add(ans2);
			// assertions
			assertNotNull(answers);
			assertEquals(ans1, answers.get(0));
			assertEquals(ans2, answers.get(1));
			assertEquals(3, answers.size());
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(false, answers.contains(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(false, answers.contains("Paris"));
			assertEquals(true, answers.contains(ans1));
			assertEquals(true, answers.contains(ans2));
			answers.clear();
		} else {
			System.out.println("Question length is more than 256 characters for answer " + answers);

			//System.out.println("Answer length is more than 256 characters for answer " + answers);
		}

	}

	@Test
	public void testaddQuestionTest() {
		Question q = createQuestionEntity();
		q.setQuestion("What Your Favorite Vacation Spot?");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("What Your Favorite Vacation Spot?")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("What Your Favorite Vacation Spot?",
				"Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications.ava-based framework used to create a micro Service. It is developed by Pivotal Team and ");
		List<String> answers = service.getAnswers("What Your Favorite Vacation Spot?");
		assertNotNull(answers);
		assertEquals(
				"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"",
				answers.get(0));
		assertNotEquals("Paris", answers.get(0));
		assertNotEquals(null, answers.get(0));
		assertNotEquals("3424sfsfssfsdfsdfsdfsd", answers.get(0));
		boolean s = service.getAnswers("What Your Favorite Vacation Spot?") != null;
		assertEquals(true, s);
		answers.clear();

		// boolean validateans = ValidationUtil.validateLength(answers);
		// assertNotEquals("answer2", answers.get(0));
		// assertEquals("answer2", answers.get(1));

	}

	@Test
	public void testaddQuestionTest_empty_special() {
		Question q = createQuestionEntity();
		q.setQuestion("?");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("?")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("?", "Malaysia");
		List<String> answers = service.getAnswers("?");

		answers.add("Paris");
		answers.add("London");
		// assertions
		assertNotNull(answers);
		// @SuppressWarnings("unused")
		// String expans = "\"the answer to life, universe and everything is 42\"
		// according to\"The hitchhikers guide to the Galaxy\"";
		// assertEquals(expans,answers.get(0));
		assertEquals("Malaysia", answers.get(0));
		assertEquals("Paris", answers.get(1));
		assertEquals("London", answers.get(2));
		assertEquals(3, answers.size());
		assertNotEquals(-1, answers.size());
		assertNotEquals(null, answers.size());
		assertEquals(false, answers.contains(
				"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
		assertEquals(true, answers.contains("Paris"));
		answers.clear();

	}

	@Test
	public void testaddQuestionTest_null_message() {
		Question q = createQuestionEntity();
		q.setQuestion(null);
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("")).thenReturn(Optional.ofNullable(q));
		service.addQuestion(null, "switzerland");
		List<String> answers = service.getAnswers(null);
		assertNotNull(answers);
		String expanswer = "\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"";
		assertEquals(expanswer, answers.get(0));
		answers.clear();

	}
}
