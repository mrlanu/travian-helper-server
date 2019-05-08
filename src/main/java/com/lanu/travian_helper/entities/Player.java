package com.lanu.travian_helper.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Player {
    private int id;
    private int ratio;
    private String name;
    private String alliance;
    private int population;
    private int villages;
}
