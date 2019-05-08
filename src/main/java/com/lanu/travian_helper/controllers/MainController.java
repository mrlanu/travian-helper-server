package com.lanu.travian_helper.controllers;

import com.lanu.travian_helper.entities.Player;
import com.lanu.travian_helper.models.Attack;
import com.lanu.travian_helper.models.AttacksString;
import com.lanu.travian_helper.services.BrowsingService;
import com.lanu.travian_helper.services.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private ParsingService parsingService;

    @Autowired
    private BrowsingService browsingService;

    @GetMapping
    public String getGreeting(){
        return "Hello";
    }

    @GetMapping("/players")
    public List<Player> getAll(){
        return browsingService.getAllPlayers();
    }

    @PostMapping("/attacks")
    public List<Attack> getAllAttacks(@Valid @RequestBody AttacksString stringAttacksForParse){
        return parsingService.getAllAttacks(stringAttacksForParse);
    }
}
