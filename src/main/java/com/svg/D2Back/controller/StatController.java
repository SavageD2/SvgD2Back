package com.svg.D2Back.controller;

import com.svg.D2Back.entity.DestinyStatDefinition;
import com.svg.D2Back.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stat")
public class StatController {
    @Autowired
    private StatService statService;

    @GetMapping("")
    public List<DestinyStatDefinition> getStats() {
        return statService.getAllStats();
    }
}
