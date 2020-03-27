package com.cgm.qanda.dataaccessobject;
/**
 * QuestionRepository.java - CRUD operations related question data objects
 * @author Manjunath Golla Bala
 * @version 1.0
 * 
 */


import com.cgm.qanda.dataobject.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(nativeQuery = true, value = "select * from question where question = ?")
    Optional<Question> findByQuestion(String question);
}
