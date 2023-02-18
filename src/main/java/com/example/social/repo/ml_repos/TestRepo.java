package com.example.social.repo.ml_repos;

import com.example.social.entities.ml_domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepo extends JpaRepository<Test,Integer> {
}
