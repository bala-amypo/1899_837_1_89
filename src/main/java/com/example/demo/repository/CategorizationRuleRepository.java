package com.example.demo.repository;

import com.example.demo.entity.CategorizationRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorizationRuleRepository
        extends JpaRepository<CategorizationRule, Long> {

    List<CategorizationRule> findMatchingRulesByDescription(String description);
}
