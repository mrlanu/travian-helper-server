package com.lanu.travian_helper.services;

import com.lanu.travian_helper.entities.Player;
import com.lanu.travian_helper.models.Attack;

import java.util.List;

public interface BrowsingService {
    List<Player> getAllPlayers();
    List<Attack> injectAttackingAccountName(List<Attack> attacks);
}
