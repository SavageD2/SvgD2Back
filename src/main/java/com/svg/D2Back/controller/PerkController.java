package com.svg.D2Back.controller;

import com.svg.D2Back.entity.DestinySandboxPerkDefinition;
import com.svg.D2Back.service.PerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/perks")
public class PerkController {

    @Autowired
    private PerkService perkService;


    @GetMapping("")
    public List<DestinySandboxPerkDefinition> getAllPerks() {
        return perkService.getALlPerks();
    }
}
