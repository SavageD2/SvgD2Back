package com.svg.D2Back.service;

import com.svg.D2Back.entity.DestinyStatDefinition;
import com.svg.D2Back.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatService {

    @Autowired
    private StatRepository statRepository;

    public List<DestinyStatDefinition> getAllStats() {
        return statRepository.findAll();
    }
}
