package it.academy.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_SENSORSDESC")
public class SensorInfo {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "SENSORS_ID")
    private Long id;

    @Column(name = "SENSORS_NAME")
    private String sensorsName;

    @Column(name = "SENSORS_MODEL")
    private String sensorsModel;

    @Column(name = "SENSORS_RANGE_FROM")
    private String sensorsRangeFrom;

    @Column(name = "SENSORS_RANGE_TO")
    private String sensorsRangeTo;

    @Column(name = "SENSORS_LOCATION")
    private String sensorsLocation;

    @Column(name = "SENSORS_DESC")
    private String descriptions;

    @ManyToOne
    private SensorType sensorType;

    @ManyToOne
    private SensorUnit sensorUnit;

}
