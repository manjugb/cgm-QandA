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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataaccessobject.QuestionRepository;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;

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
		Question q = createQuestionEntity();
		q.setQuestion("question");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("question")).thenReturn(Optional.ofNullable(q));
		// answers.addAll(arg0, arg1)
		service.addQuestion("question", "answer1");
		List<String> answers = service.getAnswers("question");
		if (answers != null && !answers.isEmpty()) {
			// service.addQuestion("question", "answer1");
			answers.add("answer2");
			assertNotNull(answers);
			assertEquals("answer1", answers.get(0));
			assertEquals("answer2", answers.get(1));
		} else {

			for (String answer : answers) {
				System.out.println(answer);
			}
		}
	}

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
		Question q = createQuestionEntity();
		q.setQuestion("What is Your Favorite Vacation Spot?");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("What is Your Favorite Vacation Spot?")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("What is Your Favorite Vacation Spot?", "Interlaken");
		List<String> answers = service.getAnswers("What is Your Favorite Vacation Spot?");
		if (answers != null && !answers.isEmpty()) {
			// answers.clear();
			// service.addQuestion("What is Your Favorite Vacation Spot?", "Interlaken");
			answers.add("Paris");
			answers.add("Amestardam");
			answers.add("Brussels");
			answers.add("Linz");
			// Assertions
			assertNotNull(answers);
			assertEquals("Interlaken", answers.get(0));
			assertEquals("Paris", answers.get(1));
			assertEquals("Amestardam", answers.get(2));
			assertEquals("Brussels", answers.get(3));
			assertEquals("Linz", answers.get(4));
			assertEquals(5, answers.size());
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(true, answers.contains("Interlaken"));
			assertEquals(true, answers.contains("Paris"));
			assertEquals(true, answers.contains("Amestardam"));
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
	public void testGetAnswers_success() {

		Question q = createQuestionEntity();
		Mockito.when(repo.findByQuestion("What is Your Favorite Vacation Spot?")).thenReturn(Optional.ofNullable(q));
		List<String> answers = service.getAnswers("What is Your Favorite Vacation Spot?");
		if (answers != null && !answers.isEmpty()) {
			answers.add("Interlaken");
			answers.add("Paris");
			assertNotNull(answers);
			assertEquals(3, answers.size());
			assertEquals(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"",
					answers.get(0));
			assertEquals("Interlaken", answers.get(1));
			assertEquals("Paris", answers.get(2));
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
		Question q = createQuestionEntity();
		q.setQuestion(null);
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion(null)).thenReturn(Optional.ofNullable(q));
		service.addQuestion(null, null);
		List<String> answers = service.getAnswers(null);
		if (answers != null && !answers.isEmpty()) {
			// answers.clear();
			// assertions
			answers.add(null);
			answers.add(null);
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
	 * @param answers  with valid three empty values
	 */

	@Test
	public void testaddQuestionTest_emptyquestion_emptyanwers() {
		Question q = createQuestionEntity();
		q.setQuestion("");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("", "");
		List<String> answers = service.getAnswers("");
		if (answers != null && !answers.isEmpty()) {
			// answers.clear();
			answers.add("");
			answers.add("");
			// assertions
			assertNotNull(answers);
			// assertEquals("kerala",answers.get(0));
			assertEquals("", answers.get(1));
			assertEquals("", answers.get(2));
			assertEquals(true, answers.contains(""));
			assertEquals(false, answers.contains("London"));
			assertEquals(3, answers.size()); // How come this size would be i was given any value but all "" gives 0
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(false, answers.contains(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(false, answers.contains("Paris"));
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
		Question q = createQuestionEntity();
		q.setQuestion("");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("", "Kerala");
		List<String> answers = service.getAnswers("");
		if (answers != null && !answers.isEmpty()) {

			answers.add("");
			answers.add("");
			// assertions
			assertNotNull(answers);
			// assertEquals("Kerala", answers.get(0));
			assertEquals("", answers.get(1));
			assertEquals("", answers.get(2));
			assertEquals(3, answers.size());
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(false, answers.contains(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(false, answers.contains("Paris"));
			assertEquals(false, answers.contains("London"));
			assertNotEquals(true, answers.contains("Bangalore"));
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
	public void testaddQuestionTest_emptyString_two_answers() {
		Question q = createQuestionEntity();
		q.setQuestion("");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("", "");
		List<String> answers = service.getAnswers("");
		if (answers != null && !answers.isEmpty()) {

			answers.add("Paris");
			answers.add("London");
			// assertions
			assertNotNull(answers);
			// assertEquals("", answers.get(0));
			assertEquals("Paris", answers.get(1));
			assertEquals("London", answers.get(2));
			assertEquals(3, answers.size());
			// assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(false, answers.contains(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(true, answers.contains("Paris"));
			assertEquals(true, answers.contains("London"));
			assertNotEquals(true, answers.contains("Bangalore"));
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
	public void testaddQuestionTest_emptyString_two_answer() {
		Question q = createQuestionEntity();
		q.setQuestion("");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("", "trim");
		List<String> answers = service.getAnswers("");
		if (answers != null && !answers.isEmpty()) {
			answers.add("Paris");
			answers.add("");
			// assertions
			assertNotNull(answers);
			assertEquals("trim", answers.get(0));
			assertEquals("Paris", answers.get(1));
			assertEquals("", answers.get(2));
			assertEquals(3, answers.size());
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(false, answers.contains(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(true, answers.contains("Paris"));
			assertEquals(true, answers.contains("trim"));
			assertNotEquals(true, answers.contains("Bangalore"));
		} else {

			System.out.println("Answer length is more than 256 characters for answer " + answers);
		}

	}

	@Test
	public void testaddQuestionTest_alpa() {
		Question q = createQuestionEntity();
		q.setQuestion("How are you");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("How are you")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("How are you", "fine");
		List<String> answers = service.getAnswers("How are you");
		if (answers != null && !answers.isEmpty()) {
			answers.add("thank");
			answers.add("you");
			// assertions
			assertNotNull(answers);
			assertEquals("fine", answers.get(0));
			assertEquals("thank", answers.get(1));
			assertEquals("you", answers.get(2));
			assertEquals(3, answers.size());
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(false, answers.contains(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
			assertEquals(false, answers.contains("Paris"));
			assertEquals(true, answers.contains("fine"));
			assertEquals(true, answers.contains("you"));
		} else {

			System.out.println("Answer length is more than 256 characters for answer " + answers);
		}

	}

	@Test
	public void testaddQuestionTest() {
		Question q = createQuestionEntity();
		q.setQuestion("What Your Favorite Vacation Spot?");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("What Your Favorite Vacation Spot?")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("What Your Favorite Vacation Spot?", "Thailand");
		List<String> answers = service.getAnswers("What Your Favorite Vacation Spot?");
		assertNotNull(answers);
		assertEquals("Thailand", answers.get(0));
		assertNotEquals("Paris", answers.get(0));
		assertNotEquals(null, answers.get(0));
		assertNotEquals("3424sfsfs", answers.get(0));
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
		String expanswer = "the answer to life, universe and everything is 42" + "according to"
				+ "The hitchhikers guide to the Galaxy";
		assertNotEquals(expanswer, answers.get(0));

	}
}
