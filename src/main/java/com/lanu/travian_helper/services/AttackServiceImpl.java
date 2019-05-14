package com.lanu.travian_helper.services;

import com.lanu.travian_helper.entities.Attack;
import com.lanu.travian_helper.repositories.AttackRepository;
import com.lanu.travian_helper.repositories.PlayerRepository;
import com.lanu.travian_helper.repositories.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
    public List<Attack> saveAll(List<Attack> requestedAttacksList){

        return checkIfAttackIsStored(requestedAttacksList)
                .stream()
                .peek(attack -> {
                    playerRepository.save(attack.getDeffer().getPlayer());
                    playerRepository.save(attack.getOffer().getPlayer());
                    villageRepository.save(attack.getOffer());
                    villageRepository.save(attack.getDeffer());
                    attackRepository.save(attack);
                })
                .collect(Collectors.toList());
    }

    // checking whether is attack stored already or not
    private List<Attack> checkIfAttackIsStored(List<Attack> requestedAttacksList){
        // if attack has been stored already, it going to be skipped
        boolean isExist = false;

        List<Attack> result = new ArrayList<>();
        List<Attack> storedAttackList = attackRepository.findAllByUserId(1);

        if (storedAttackList.size() > 0){
            for (Attack attack : requestedAttacksList){
                for (Attack attack1 : storedAttackList){
                    if (attack.equals(attack1)){
                        isExist = true;
                    }
                }
                if (!isExist){
                    result.add(attack);
                }
                isExist = false;
            }
        } else result.addAll(requestedAttacksList);

        return result;
    }

    // delete all expired attacks
    @Override
    public void clean() {
        LocalDateTime serverTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        List<Attack> expiredAttacksList = attackRepository
                .findAllByTimeAttackLessThan(serverTime);
        if (expiredAttacksList.size() > 0){
            attackRepository.deleteAll(expiredAttacksList);
        }
    }
}
