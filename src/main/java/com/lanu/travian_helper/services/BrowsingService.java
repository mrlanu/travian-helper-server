package com.lanu.travian_helper.services;

import com.lanu.travian_helper.entities.Account;
import com.lanu.travian_helper.entities.Attack;

import java.util.List;

public interface BrowsingService {
    List<Account> getAllPlayers();
    List<Attack> injectAttackingAccountName(List<Attack> attacks);
}
