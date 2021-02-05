package it.academy.data;

import java.util.List;

public interface SensorsDataDao {

    void create (it.academy.data.SensorsData sensorsData);

    SensorsData read(int id);

    List<SensorsData> readAll();

    void update (it.academy.data.SensorsData sensorsData);

    void delete (it.academy.data.SensorsData sensorsData);


}
