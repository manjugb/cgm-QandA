package com.cgm.qanda.dataaccessobject;
/*@author:Manjunath Golla Bala
 *@Description:JpaRepository is JPA specific extension of Repository. It contains the full API of CrudRepository and PagingAndSortingRepository. So it contains API for basic CRUD operations and also API for pagination and sorting
 *@Current Actions:CRUD operations related question data objects 
 */

import com.cgm.qanda.dataobject.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(nativeQuery = true, value = "select * from question where question = ?")
    Optional<Question> findByQuestion(String question);
}
