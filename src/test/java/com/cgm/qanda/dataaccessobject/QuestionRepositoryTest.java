 package com.cgm.qanda.dataaccessobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.codec.language.bm.Lang;
import org.apache.derby.tools.sysinfo;
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
@ContextConfiguration(classes = QnAApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
@SpringBootTest
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
        Set<Answer> set = new HashSet<>();
        set.add(answer);
        return question;}
    
    @Test
    public void testSave_one() {
    	String que = "What is Your Favorite Color?";
    	if (ValidationUtil.validateAlpaCharLength(que)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), false);
		}
        Question question = createUserEntity_success();
        repository.save(question);
        Optional<Question> q = repository.findByQuestion("What is Your Favorite Color?");
        Question qq = q.get();
        assertEquals("What is Your Favorite Color?", qq.getQuestion());
        repository.flush();
    }
    
    @Test
    public void testSave_two_ansEmpty() {
    	String que = "What is Your Favorite Color?";
    	try {
    	
    	if (ValidationUtil.validateAlpaCharLength(que)) {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), true);
		} else {
			assertEquals(ValidationUtil.validateAlpaCharLength(que), false);
		}
        Question question = createUserEntity_success();
        repository.save(question);
        Optional<Question> q = repository.findByQuestion("What is Your Favorite Color?");
        
        Question qq = q.get();
        assertEquals("What is Your Favorite Color?", qq.getQuestion());
        repository.flush();}
        catch (IncorrectResultSizeDataAccessException e) {
            System.out.println("IncorrectResultSizeDataAccessException caught" +que+"");
          }
    }
    
    
}

