package com.lanu.travian_helper.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attack {

    private String attackingVillage;
    private int attackingVillageX;
    private int attackingVillageY;
    private LocalDateTime duration;
    private LocalDateTime arivedTime;
}
