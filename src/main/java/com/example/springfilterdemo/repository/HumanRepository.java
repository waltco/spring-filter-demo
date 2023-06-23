package com.example.springfilterdemo.repository;

import com.example.springfilterdemo.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends JpaRepository<Human, String>, JpaSpecificationExecutor<Human> {

}
