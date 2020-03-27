package com.cgm.qanda.service;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataaccessobject.QuestionRepository;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = QnAApplication.class,
initializers = ConfigFileApplicationContextInitializer.class)
@SpringBootTest
public class QuestionAnswerServiceImplTest {

    @Autowired
    QuestionAnswerService service;

    @Mock
    QuestionRepository repo;

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
        Set<Answer> set = new HashSet<>();
        set.add(answer);
        return question;
    }

    @Test
    public void testGetAnswers1() {
        Question q = createQuestionEntity();
        Mockito.when(repo.findByQuestion("question1")).thenReturn(Optional.ofNullable(q));
        List<String> answers = service.getAnswers("question1");
        assertNotNull(answers);
        assertEquals(1, answers.size());
        //assertNotEquals(2, answers.size());
    }

    @Test
    public void addQuestionTest() {
        Question q = createQuestionEntity();
        q.setQuestion("question");
        Mockito.when(repo.save(q)).thenReturn(q);
        Mockito.when(repo.findByQuestion("question")).thenReturn(Optional.ofNullable(q));
        service.addQuestion("question", "answer1");
        List<String> answers = service.getAnswers("question1");
        assertNotNull(answers);
        assertNotEquals("answer1", answers.get(0));

    }
    @Test
    public void testGetAnswers() {
        Question q = createQuestionEntity();
        Mockito.when(repo.findByQuestion("What Your Favorite Vacation Spot?")).thenReturn(Optional.ofNullable(q));
        List<String> answers = service.getAnswers("What Your Favorite Vacation Spot?");
        assertNotNull(answers);
        assertNotEquals(0, answers.size());
        assertEquals(1, answers.size());
        assertEquals(2, answers.size());
        assertEquals(4,answers.size());
    }
    
    @Test
    public void testGetAnswers_success() {
        Question q = createQuestionEntity();
        Mockito.when(repo.findByQuestion("What is Your Favorite Vacation Spot?")).thenReturn(Optional.ofNullable(q));
        List<String> answers = service.getAnswers("What is Your Favorite Vacation Spot?");
        assertNotNull(answers);
        assertEquals(1, answers.size());
        assertNotEquals(0,answers.size());
    }
    
    
    @Test
    public void testGetAnswers_empty() {
        Question q = createQuestionEntity();
        Mockito.when(repo.findByQuestion("")).thenReturn(Optional.ofNullable(q));
        List<String> answers = service.getAnswers("");
        assertNotNull(answers);
        assertEquals(1, answers.size());
        assertNotEquals(0,answers.size());
    }
    

    @Test
    public void addQuestionTest1() {
        Question q = createQuestionEntity();
        q.setQuestion("What Your Favorite Vacation Spot?");
        Mockito.when(repo.save(q)).thenReturn(q);
        Mockito.when(repo.findByQuestion("What Your Favorite Vacation Spot?")).thenReturn(Optional.ofNullable(q));
        service.addQuestion("What Your Favorite Vacation Spot?", "switzerland");
        List<String> answers = service.getAnswers("What Your Favorite Vacation Spot?");
        assertNotNull(answers);
        assertEquals("switzerland", answers.get(0));
        assertNotEquals("", answers.get(0));
        assertNotEquals(null, answers.get(0));
        assertNotEquals("3424sfsfs", answers.get(0));
        //assertNotEquals("answer2", answers.get(0));
        //assertEquals("answer2", answers.get(1));
        

    }
    @Test
    public void addQuestionTest_empty_special() {
        Question q = createQuestionEntity();
        q.setQuestion("?");
        Mockito.when(repo.save(q)).thenReturn(q);
        Mockito.when(repo.findByQuestion("")).thenReturn(Optional.ofNullable(q));
        service.addQuestion("?", "answer1");
        List<String> answers = service.getAnswers("?");
        assertNotNull(answers);
        assertEquals("answer1", answers.get(0));
        assertNotEquals("", answers.get(0));
        assertNotEquals(null, answers.get(0));
        assertNotEquals("3424sfsfs", answers.get(0));
        //assertNotEquals("answer2", answers.get(0));
        //assertEquals("answer2", answers.get(1));
        

    } 
    
    @Test
    public void addQuestionTest_null_message() {
        Question q = createQuestionEntity();
        q.setQuestion(null);
        Mockito.when(repo.save(q)).thenReturn(q);
        Mockito.when(repo.findByQuestion("")).thenReturn(Optional.ofNullable(q));
        service.addQuestion(null, "answer1");
        List<String> answers = service.getAnswers(null);
        assertNotNull(answers);
        String expanswer="the answer to life, universe and everything is 42"+ "according to"+"The hitchhikers guide to the Galaxy";
        assertNotEquals(expanswer, answers.get(0));
            

    } 

    
}
