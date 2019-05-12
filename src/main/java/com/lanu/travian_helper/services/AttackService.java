package com.lanu.travian_helper.services;

import com.lanu.travian_helper.models.Attack;

import java.util.List;

public interface AttackService {
    List<Attack> saveAll(List<Attack> attackList);
}
