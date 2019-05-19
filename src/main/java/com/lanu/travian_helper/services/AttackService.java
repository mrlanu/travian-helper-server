package com.lanu.travian_helper.services;

import com.lanu.travian_helper.entities.Attack;
import com.lanu.travian_helper.models.CrossAttack;

import java.util.List;

public interface AttackService {
    List<Attack> saveAll(List<Attack> requestedAttacksList);
    List<CrossAttack> getCrossAttacks();
    void clean();

    /*Map<Village, List<Map<Village, List<Attack>>>> crossAttacksTable();*/
}
