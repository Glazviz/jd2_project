package it.academy.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SENSOR_TYPE")
public class SensorType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TYPE_ID")
    private Long id;

    @Column(name = "TYPE")
    private String name;

}
