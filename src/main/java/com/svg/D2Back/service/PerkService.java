package com.svg.D2Back.service;

import com.svg.D2Back.entity.DestinySandboxPerkDefinition;
import com.svg.D2Back.repository.PerkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerkService {
    @Autowired
    private PerkRepository perkRepository;

    public List<DestinySandboxPerkDefinition> getALlPerks() {
        return perkRepository.findAll();
    }
}
