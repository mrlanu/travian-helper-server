package com.lanu.travian_helper.services;

import com.lanu.travian_helper.entities.Attack;
import com.lanu.travian_helper.models.AttacksString;

import java.util.List;

public interface ParsingService {
    List<Attack> getAllAttacks(AttacksString attacksString);
}
