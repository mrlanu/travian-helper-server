package com.lanu.travian_helper.controllers;

import com.lanu.travian_helper.entities.Account;
import com.lanu.travian_helper.entities.Attack;
import com.lanu.travian_helper.entities.Village;
import com.lanu.travian_helper.models.AttacksString;
import com.lanu.travian_helper.services.AttackService;
import com.lanu.travian_helper.services.BrowsingService;
import com.lanu.travian_helper.services.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private ParsingService parsingService;

    @Autowired
    private BrowsingService browsingService;

    @Autowired
    private AttackService attackService;

    @GetMapping("/testik")
    public Map<Village, List<Map<Village, List<Attack>>>> testik(){
        return attackService.crossAttacksTable();
    }

    @GetMapping("/time")
    public LocalDateTime getServerTime(){
        return browsingService.getServerTime();
    }

    @GetMapping
    public String getGreeting(){
        return "Hello";
    }

    @GetMapping("/players")
    public List<Account> getAll(){
        return browsingService.getAllPlayers();
    }

    @PostMapping("/parse-attacks")
    public List<Attack> getAllAttacks(@Valid @RequestBody AttacksString stringAttacksForParse){
        return parsingService.getAllAttacks(stringAttacksForParse);
    }

    @PostMapping("/attacks")
    public List<Attack> saveAttacks(@RequestBody List<Attack> attackList){
        return attackService.saveAll(attackList);
    }
}
