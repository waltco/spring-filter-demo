package com.example.springfilterdemo.controller;

import com.example.springfilterdemo.entity.Human;
import com.example.springfilterdemo.service.HumanService;

import java.util.List;

import com.turkraft.springfilter.boot.Filter;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/humans")
public class ListHumanController {

    private final HumanService humanService;

    @GetMapping
    public List<Human> getHumans(@Filter Specification<Human> spec) {
        return humanService.findAll(spec);
    }

}
