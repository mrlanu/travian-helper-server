package com.lanu.travian_helper.services;

import com.lanu.travian_helper.entities.Attack;

import java.util.List;

public interface AttackService {
    List<Attack> saveAll(List<Attack> requestedAttacksList);
}
