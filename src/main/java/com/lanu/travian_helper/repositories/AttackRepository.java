package com.lanu.travian_helper.repositories;

import com.lanu.travian_helper.models.Attack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttackRepository extends JpaRepository<Attack, Integer> {
}
