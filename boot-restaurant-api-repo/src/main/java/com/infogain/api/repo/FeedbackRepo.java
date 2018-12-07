package com.infogain.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{

}
