package com.lanu.travian_helper.models;

import com.lanu.travian_helper.entities.Attack;
import com.lanu.travian_helper.entities.Village;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferAttack {
    private Village offer;
    private List<Attack> attacks;
}