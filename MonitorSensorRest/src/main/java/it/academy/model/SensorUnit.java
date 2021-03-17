package it.academy.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SENSOR_UNIT")
public class SensorUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UNIT_ID")
    private Long id;

    @Column(name = "UNIT")
    private String name;

}
