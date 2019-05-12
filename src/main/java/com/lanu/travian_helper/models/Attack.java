package com.lanu.travian_helper.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attack {

    private Village offer;
    private Village deffer;
    private LocalTime time;

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
