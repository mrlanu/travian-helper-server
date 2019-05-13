package com.lanu.travian_helper.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Village offer;

    @OneToOne
    private Village deffer;

    private LocalTime time;

    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attack attack = (Attack) o;
        return Objects.equals(offer.getName(), attack.offer.getName()) &&
                Objects.equals(deffer.getName(), attack.deffer.getName()) &&
                Objects.equals(time, attack.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offer, deffer, time);
    }
}
