package com.svg.D2Back.controller;

import com.svg.D2Back.Errors.RessourceNotFoundException;
import com.svg.D2Back.entity.DestinySandboxPerkDefinition;
import com.svg.D2Back.repository.PerkRepository;
import com.svg.D2Back.service.PerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/perks")
public class PerkController {

    @Autowired
    private PerkService perkService;

    @Autowired
    private PerkRepository perkRepository;


    @GetMapping("")
    public List<DestinySandboxPerkDefinition> getAllPerks() {
        return perkService.getALlPerks();
    }

    @GetMapping("/{perkId}")
    public ResponseEntity<DestinySandboxPerkDefinition> getPerkById(@PathVariable Integer perkId) {
        Optional<DestinySandboxPerkDefinition> perk = perkRepository.findById(perkId);
        if (!perk.isPresent()) {
            throw new RessourceNotFoundException("Perk","not found", perkId);
        }
        return ResponseEntity.ok(perk.get());
    }
}
