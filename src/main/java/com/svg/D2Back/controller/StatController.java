package com.svg.D2Back.controller;

import com.svg.D2Back.Errors.RessourceNotFoundException;
import com.svg.D2Back.entity.DestinyStatDefinition;
import com.svg.D2Back.repository.StatRepository;
import com.svg.D2Back.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stats")
public class StatController {
    @Autowired
    private StatService statService;

    @Autowired
    private StatRepository statRepository;

    @GetMapping("")
    public List<DestinyStatDefinition> getStats() {
        return statService.getAllStats();
    }

    @GetMapping("/{statId}")
    public ResponseEntity<DestinyStatDefinition> getStatById(@PathVariable Integer statId) {
        Optional<DestinyStatDefinition> stat = statRepository.findById(statId);
        if (!stat.isPresent()) {
            throw new RessourceNotFoundException("Stat not found", "statId", statId);
        }
        return ResponseEntity.ok(stat.get());
    }

}
