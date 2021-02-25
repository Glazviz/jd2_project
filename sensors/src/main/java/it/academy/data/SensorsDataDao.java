package it.academy.data;

import it.academy.model.SensorsData;

import java.util.List;

public interface SensorsDataDao {

    void create (SensorsData sensorsData);

    SensorsData read(int id);

    List<SensorsData> readAll();

    void update (SensorsData sensorsData);

    void delete (SensorsData sensorsData);


}
