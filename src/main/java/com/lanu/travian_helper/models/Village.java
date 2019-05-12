package com.lanu.travian_helper.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Village {

    private int id;
    private String name;
    private int x;
    private int y;
    private Player player;
}
