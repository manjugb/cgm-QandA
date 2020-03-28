 package com.cgm.qanda.dataaccessobject;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        Optional<Question> q = repository.findByQuestion("What is Your Hobbies?");
        Question qq = q.get();
        assertEquals("What is Your Hobbies?", qq.getQuestion());
        repository.flush();
    }
    
    /*@Test
    public void testSave_one() {
        Question question = createUserEntity();
        repository.save(question);
        Optional<Question> q = repository.findByQuestion("What Your Favorite Holiday Spot?");
        Question qq = q.get();
        assertEquals("What Your Favorite Holiday Spot?", qq.getQuestion());
        repository.flush();
    }*/

    private Question createUserEntity() {
        Question question = new Question();
        question.setQuestion("What is Your Hobbies?");
        Answer answer = new Answer();
        answer.setAnswer("reading,travelling,trucking");
        Set<Answer> set = new HashSet<>();
        set.add(answer);
        return question;
    }
    
}
