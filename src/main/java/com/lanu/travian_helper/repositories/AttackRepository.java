package com.lanu.travian_helper.repositories;

import com.lanu.travian_helper.entities.Attack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AttackRepository extends JpaRepository<Attack, Integer> {

    List<Attack> findAllByUserId(Integer userId);
    List<Attack> findAllByTimeAttackLessThan(LocalDateTime localDateTime);
}
