package com.lanu.travian_helper.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Village {

    @Id
    private Integer id;
    private String name;
    private int x;
    private int y;

    @OneToOne
    private Player player;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Village village = (Village) o;
        return x == village.x &&
                y == village.y &&
                Objects.equals(id, village.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y);
    }
}
