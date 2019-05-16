package com.lanu.travian_helper.services;

import com.lanu.travian_helper.entities.Attack;
import com.lanu.travian_helper.entities.Village;

import java.util.List;
import java.util.Map;

public interface AttackService {
    List<Attack> saveAll(List<Attack> requestedAttacksList);
    Map<Village, List<Map<Village, List<Attack>>>> crossAttacksTable();
    void clean();
}
