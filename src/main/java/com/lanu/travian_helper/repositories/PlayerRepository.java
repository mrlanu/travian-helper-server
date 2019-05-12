package com.lanu.travian_helper.repositories;

import com.lanu.travian_helper.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
