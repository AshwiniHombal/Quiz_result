package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.QuizResult;
@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Integer> {

}
