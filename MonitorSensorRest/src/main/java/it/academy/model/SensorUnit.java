package it.academy.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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
