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
    @JoinColumn(name = "SENSORS_TYPE_ID", referencedColumnName = "TYPE_ID")
    private SensorType sensorType;

    @ManyToOne
    @JoinColumn(name = "SENSORS_UNIT_ID", referencedColumnName = "UNIT_ID")
    private SensorUnit sensorUnit;

    public SensorInfo(String sensorsName, String sensorsModel, String sensorsRangeFrom, String sensorsRangeTo, String sensorsLocation, String descriptions, SensorType sensorType, SensorUnit sensorUnit) {
    }
}
