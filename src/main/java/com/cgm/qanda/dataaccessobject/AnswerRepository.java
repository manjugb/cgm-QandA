package com.cgm.qanda.dataaccessobject;
/**  
*  
* @author  Manjunath Golla Bala
* @version 0.1 
* @description JpaRepository is JPA specific extension of Repository. It contains the full API of CrudRepository and PagingAndSortingRepository. So it contains API for basic CRUD operations and also API for pagination and sorting 
* @features CRUD operations from h2 database
* *    
*/ 
import com.cgm.qanda.dataobject.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(nativeQuery = true, value = "select * from answer where question_id = ?")
    List<Answer> findByQustionId(Long questionId);
}
