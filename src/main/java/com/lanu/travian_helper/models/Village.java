package com.lanu.travian_helper.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Village {

    @Id
    private int id;
    private String name;
    private int x;
    private int y;

    @OneToOne
    private Player player;
}
