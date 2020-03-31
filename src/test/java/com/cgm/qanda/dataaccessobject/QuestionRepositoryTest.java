 package com.cgm.qanda.dataaccessobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;

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
        question.setQuestion("What are the highest mountains?");
        Answer answer = new Answer();
        answer.setAnswer("answer1");
        answer.setAnswer("answer2");
        answer.setAnswer("answer3");
        Set<Answer> set = new HashSet<>();
        set.add(answer);
        return question;
    }
    
    @Test
    public void testSaveFlush() {
        Question question = createUserEntity();
        repository.save(question);
        Optional<Question> q = repository.findByQuestion("What are the highest mountains?");
        Question qq = q.get();
        assertEquals("What are the highest mountains?", qq.getQuestion());
        repository.saveAndFlush(question);
    } 
}
