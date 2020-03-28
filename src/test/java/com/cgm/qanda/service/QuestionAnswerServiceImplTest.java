package com.cgm.qanda.service;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataaccessobject.QuestionRepository;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
		question.setQuestion("question1");
		Answer answer = new Answer();
		answer.setAnswer("answer1");
		answer.setAnswer("answer2");
		answer.setAnswer("answer2");
		Set<Answer> set = new HashSet<>();
		set.add(answer);
		return question;
	}

	@Test
	public void addQuestionTest_original() {
		Question q = createQuestionEntity();
		q.setQuestion("question");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("question")).thenReturn(Optional.ofNullable(q));
		// answers.addAll(arg0, arg1)
		service.addQuestion("question", "answer1");
		List<String> answers = service.getAnswers("question");
		answers.add("answer2");
		assertNotNull(answers);
		assertEquals("answer1", answers.get(0));
		assertEquals("answer2", answers.get(1));

	}

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

	@Test
	public void testGetAnswers_success() {
		Question q = createQuestionEntity();
		q.setQuestion("What is Your Favorite Vacation Spot?");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("What is Your Favorite Vacation Spot?")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("What is Your Favorite Vacation Spot?", "Interlaken");
		List<String> answers = service.getAnswers("What is Your Favorite Vacation Spot?");
		if (answers != null) {
			answers.add("Paris");
			answers.add("Amestardam");
			// Assertions
			assertNotNull(answers);
			assertEquals("Interlaken", answers.get(0));
			assertEquals("Paris", answers.get(1));
			assertEquals("Amestardam", answers.get(2));
			assertEquals(3, answers.size());
			assertNotEquals(-1, answers.size());
			assertNotEquals(null, answers.size());
			assertEquals(true, answers.contains("Interlaken"));
			assertEquals(true, answers.contains("Paris"));
			assertEquals(true, answers.contains("Amestardam"));
		} else {
			assertEquals(
					"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"",
					answers.get(0));

		}
	}

	@Test
	// @ParameterizedTest
	// @ValueSource(strings = {"Hello", "JUnit5"})
	public void testGetAnswers_null() {
		Question q = createQuestionEntity();
		q.setQuestion(null);
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion(null)).thenReturn(Optional.ofNullable(q));
		service.addQuestion(null, null);
	
		List<String> answers = service.getAnswers(null);
		if (answers == null) {
		// assertions
		assertNotNull(answers);
		@SuppressWarnings("unused")
		String expans = "\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"";
		assertEquals(expans,answers.get(0));
		assertNotEquals("Paris", answers.get(0));
		assertNotEquals("Amestardam", answers.get(0));
		assertEquals(1, answers.size());
		assertNotEquals(-1, answers.size());
		assertNotEquals(null, answers.size());
		assertEquals(true, answers.contains(
				"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
		assertEquals(false, answers.contains("Paris"));}
		
	}

	@Test
	public void testGetAnswers_emptyString() {
		Question q = createQuestionEntity();
		q.setQuestion("");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("", "Interlaken");
		List<String> answers = service.getAnswers("");
		if (answers == null) {
			answers.add("Paris");
			answers.add("London");
		// assertions
		assertNotNull(answers);
		@SuppressWarnings("unused")
		String expans = "\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"";
		assertEquals(expans,answers.get(0));
		assertEquals("Interlaken", answers.get(0));
		assertEquals("Paris", answers.get(1));
		assertEquals("London", answers.get(1));
		assertEquals(3, answers.size());
		assertNotEquals(-1, answers.size());
		assertNotEquals(null, answers.size());
		assertEquals(true, answers.contains(
				"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
		assertEquals(false, answers.contains("Paris"));}
	}

	@Test
	public void addQuestionTest() {
		Question q = createQuestionEntity();
		q.setQuestion("What Your Favorite Vacation Spot?");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("What Your Favorite Vacation Spot?")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("What Your Favorite Vacation Spot?", "Interlaken,Paris,Amestardam");
		List<String> answers = service.getAnswers("What Your Favorite Vacation Spot?");
		assertNotNull(answers);
		assertEquals("Interlaken,Paris,Amestardam", answers.get(0));
		assertNotEquals("Paris", answers.get(0));
		assertNotEquals(null, answers.get(0));
		assertNotEquals("3424sfsfs", answers.get(0));
		// assertNotEquals("answer2", answers.get(0));
		// assertEquals("answer2", answers.get(1));

	}

	

	@Test
	public void addQuestionTest_empty_special() {
		Question q = createQuestionEntity();
		q.setQuestion("?");
		Mockito.when(repo.save(q)).thenReturn(q);
		Mockito.when(repo.findByQuestion("?")).thenReturn(Optional.ofNullable(q));
		service.addQuestion("?", "Interlaken");
		List<String> answers = service.getAnswers("?");
		
			answers.add("Paris");
			answers.add("London");
		// assertions
		assertNotNull(answers);
		//@SuppressWarnings("unused")
		//String expans = "\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\"";
		//assertEquals(expans,answers.get(0));
		assertEquals("Interlaken", answers.get(0));
		assertEquals("Paris", answers.get(1));
		assertEquals("London", answers.get(2));
		assertEquals(3, answers.size());
		assertNotEquals(-1, answers.size());
		assertNotEquals(null, answers.size());
		assertEquals(false, answers.contains(
				"\"the answer to life, universe and everything is 42\" according to\"The hitchhikers guide to the Galaxy\""));
		assertEquals(false, answers.contains("Paris"));
	
	}

	@Test
	public void addQuestionTest_null_message() {
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
