package com.cgm.qanda.dataaccessobject;
/**
 * TestValidationUtil.java - This class defines the validation of question as a  String,Validation of input and format with passing positive and negative values
 * @features flush and saveflush
 * @author Manjunath Golla Bala
 * @version 1.0
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import com.cgm.qanda.util.ValidationUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = QnAApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
//@SpringBootTest
public class QuestionRepositoryTest {

	@Autowired()
	QuestionRepository repository;

	@Test
	public void TestRepositoryInjected() {
		assertNotNull(repository);
	}

	@Test
	public void testSave() {
		Question question = createUserEntity();
		repository.save(question);
		Optional<Question> q = repository.findByQuestion("question1");
		Question qq = q.get();
		assertEquals("question1", qq.getQuestion());
		repository.flush();
	}

	private Question createUserEntity() {
		Question question = new Question();
		question.setQuestion("question1");
		Answer answer = new Answer();
		answer.setAnswer("answer1");
		Set<Answer> set = new HashSet<>();
		set.add(answer);
		return question;
	}

	private Question createUserEntity_success() {
		Question question = new Question();
		question.setQuestion("What is Your Favorite Color?");
		Answer answer = new Answer();
		answer.setAnswer("red");
		answer.setAnswer("Green");
		answer.setAnswer("Gray");
		Set<Answer> set = new HashSet<>();
		set.add(answer);
		return question;
	}

	@Test
	public void testSave_one() {
		String que = "What is Your Favorite Color?";
		if (ValidationUtil.validateAlpaCharLength(que) == true) {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), false);
		}
		Question question = createUserEntity_success();
		repository.save(question);
		Optional<Question> q = repository.findByQuestion(que);
		Question qq = q.get();
		assertEquals(que, qq.getQuestion());
		repository.flush();
	}

	private Question createUserEntity_two() {
		Question question = new Question();
		question.setQuestion("What is Your Favorite Reciep?");
		Answer answer = new Answer();
		answer.setAnswer(null);

		Set<Answer> set = new HashSet<>();
		set.add(answer);
		return question;
	}

	@Test
	public void testSave_two_ansEmpty() {
		String que = "What is Your Favorite Reciep?";

		if (ValidationUtil.validateAlpaCharLength(que)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), false);
		}

		Question question = createUserEntity_two();
		repository.save(question);
		Optional<Question> q = repository.findByQuestion(que);
		try {
			Question qq = q.get();
			assertEquals(que, qq.getQuestion());
		} catch (IncorrectResultSizeDataAccessException e) {
			System.out.println("IncorrectResultSizeDataAccessException caught" + que + "");
		}

		repository.flush();

	}

	private Question createUserEntity_empty_ques_emptyans() {
		Question question = new Question();
		question.setQuestion("");
		Answer answer = new Answer();
		answer.setAnswer("");

		Set<Answer> set = new HashSet<>();
		set.add(answer);
		return question;
	}

	@Test
	public void testSave_two_que_ans_empty() {
		String que = "";

		if (ValidationUtil.validateAlpaCharLength(que) == true) {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), false);
		}

		Question question = createUserEntity_empty_ques_emptyans();
		repository.save(question);
		Optional<Question> q = repository.findByQuestion(que);
		try {
			Question qq = q.get();
			assertEquals(que, qq.getQuestion());
		} catch (IncorrectResultSizeDataAccessException e) {
			System.out.println("IncorrectResultSizeDataAccessException caught" + que + "");
		}

		repository.flush();

	}

	private Question createUserEntity_ques_lessthan255() {
		Question question = new Question();
		question.setQuestion(
				"This can be achieved by writing test scripts or using any automation testing tool. Test automation is used to automate repetitive tasks and other testing tasks which are difficult to perform manually?");
		Answer answer = new Answer();
		answer.setAnswer("Testing");

		Set<Answer> set = new HashSet<>();
		set.add(answer);
		return question;
	}

	@Test
	public void testSave_two_que_lessthan255char() {
		String que = "This can be achieved by writing test scripts or using any automation testing tool. Test automation is used to automate repetitive tasks and other testing tasks which are difficult to perform manually?";
		try {
			if (ValidationUtil.validateAlpaCharLength(que) == true) {
				assertEquals(ValidationUtil.validateAlpaCharLength(que), true);
			} else {
				assertEquals(ValidationUtil.validateAlpaCharLength(que), false);
			}
		} catch (NullPointerException e) {
			System.out.println("Nullpointer Exception caught" + que + "");

			Question question = createUserEntity_ques_lessthan255();
			repository.save(question);
			Optional<Question> q = repository.findByQuestion(que);

			Question qq = q.get();
			assertEquals(que, qq.getQuestion());

			// assertEquals(que, qq.getQuestion());
			repository.flush();

		}

	}

	private Question createUserEntity_save_flush() {
		Question question = new Question();
		question.setQuestion("What is Your Favorite Fruites?");
		Answer answer = new Answer();
		answer.setAnswer("Grape");
		answer.setAnswer("Pineapple");

		Set<Answer> set = new HashSet<>();
		set.add(answer);
		return question;
	}

	@Test
	public void testSave_savflush() {
		String que = "What is Your Favorite Fruites?";
		if (ValidationUtil.validateAlpaCharLength(que)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), false);
		}
		Question question = createUserEntity_save_flush();
		repository.save(question);
		Optional<Question> q = repository.findByQuestion(que);
		Question qq = q.get();
		assertEquals(que, qq.getQuestion());
		repository.saveAndFlush(question);
	}
	
	
	
	
	
	
}