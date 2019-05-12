package com.lanu.travian_helper.services;

import com.lanu.travian_helper.models.Attack;
import com.lanu.travian_helper.repositories.AttackRepository;
import com.lanu.travian_helper.repositories.PlayerRepository;
import com.lanu.travian_helper.repositories.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttackServiceImpl implements AttackService {

    @Autowired
    private AttackRepository attackRepository;

    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<Attack> saveAll(List<Attack> attackList){
        return attackList
                .stream()
                .peek(attack -> {
                    playerRepository.save(attack.getDeffer().getPlayer());
                    playerRepository.save(attack.getOffer().getPlayer());
                    villageRepository.save(attack.getOffer());
                    villageRepository.save(attack.getDeffer());
                    attackRepository.save(attack);
                }).collect(Collectors.toList());
    }
}
