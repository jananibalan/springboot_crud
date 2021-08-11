package com.janani.springboot_crud.repository;

import com.janani.springboot_crud.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResultRepository extends JpaRepository<Result,Long> {
    Iterable<Result> findByStudentId(long id);
}
