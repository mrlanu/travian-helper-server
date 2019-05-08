package com.lanu.travian_helper.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attack {

    private String attackedAccountName;
    private String attackingAccName;
    private String attackingAllianceName;
    private String attackingVillage;
    private  String attackedVillage;
    private int attackingVillageX;
    private int attackingVillageY;
    private int attackedVillageX;
    private int attackedVillageY;
    private LocalTime duration;
    private LocalTime arrivedTime;
}
