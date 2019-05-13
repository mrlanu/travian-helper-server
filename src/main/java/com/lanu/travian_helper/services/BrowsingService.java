package com.lanu.travian_helper.services;

import com.lanu.travian_helper.entities.Account;
import com.lanu.travian_helper.entities.Attack;

import java.time.LocalTime;
import java.util.List;

public interface BrowsingService {
    LocalTime getServerTime();
    List<Account> getAllPlayers();
    List<Attack> injectAttackingAccountName(List<Attack> attacks);
}
