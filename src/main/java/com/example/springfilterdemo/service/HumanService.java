package com.example.springfilterdemo.service;

import com.example.springfilterdemo.entity.Human;
import com.example.springfilterdemo.repository.HumanRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HumanService {

    private final HumanRepository repository;

    public List<Human> findAll(Specification<Human> spec) {
        return repository.findAll(spec);
    }

}
