package com.example.demo.repository;

import com.example.demo.model.CategorizationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CategorizationRuleRepository extends JpaRepository<CategorizationRule, Long> {

    // Custom HQL/JPQL to find rules where the description matches the keyword logic.
    // Note: The SRS asks for "findMatchingRulesByDescription". 
    // This logic often checks if the description contains the rule's keyword.
    @Query("SELECT r FROM CategorizationRule r WHERE :description LIKE concat('%', r.keyword, '%')")
    List<CategorizationRule> findMatchingRulesByDescription(@Param("description") String description);
}