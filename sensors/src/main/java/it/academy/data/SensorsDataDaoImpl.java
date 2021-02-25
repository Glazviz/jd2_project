package it.academy.data;

import it.academy.model.SensorsData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorsDataDaoImpl implements SensorsDataDao {

    private final Connection connection;

    public SensorsDataDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(SensorsData sensorsData) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into sensors_data" +
                        "(id, sensors_model, sensors_type, sensors_range_from, sensors_range_to, sensors_unit, sensors_location, sensors_descriptions)" +
                        "values (?,?,?,?,?,?,?,?,?)")) {
            System.out.println(sensorsData);
            preparedStatement.setInt(1, sensorsData.getId());
            preparedStatement.setString(2, sensorsData.getSensorsName());
            preparedStatement.setString(3, sensorsData.getSensorsModel());
            preparedStatement.setString(4, sensorsData.getSensorsType());
            preparedStatement.setInt(5, sensorsData.getSensorsRangeFrom());
            preparedStatement.setInt(6, sensorsData.getSensorsRangeTo());
            preparedStatement.setString(7, sensorsData.getSensorsUnit());
            preparedStatement.setString(8, sensorsData.getSensorsLocation());
            preparedStatement.setString(9, sensorsData.getSensorsDescriptions());
            System.out.println("create = " + preparedStatement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public SensorsData read(int id) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "select * from sensors_data where id = " + id)) {
            resultSet.next();
            SensorsData sensorsData = new SensorsData();
            sensorsData.setId(resultSet.getInt("id"));
            sensorsData.setSensorsName(resultSet.getString("sensors_name"));
            sensorsData.setSensorsModel(resultSet.getString("sensors_model"));
            sensorsData.setSensorsType(resultSet.getString("sensors_type"));
            sensorsData.setSensorsRangeFrom(resultSet.getInt("sensors_range_from"));
            sensorsData.setSensorsRangeTo(resultSet.getInt("sensors_range_to"));
            sensorsData.setSensorsUnit(resultSet.getString("sensors_unit"));
            sensorsData.setSensorsLocation(resultSet.getString("sensors_location"));
            sensorsData.setSensorsDescriptions(resultSet.getString("sensors_descriptions"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<SensorsData> readAll() {
        List<SensorsData> sensorsData = new ArrayList<>();
        try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sensors_data")) {
            while (resultSet.next()) {
                SensorsData sensorsDataAll = new SensorsData();
                sensorsDataAll.setId(resultSet.getInt("id"));
                sensorsDataAll.setSensorsName(resultSet.getString("sensors_name"));
                sensorsDataAll.setSensorsModel(resultSet.getString("sensors_model"));
                sensorsDataAll.setSensorsType(resultSet.getString("sensors_type"));
                sensorsDataAll.setSensorsRangeFrom(resultSet.getInt("sensors_range_from"));
                sensorsDataAll.setSensorsRangeTo(resultSet.getInt("sensors_range_to"));
                sensorsDataAll.setSensorsUnit(resultSet.getString("sensors_unit"));
                sensorsDataAll.setSensorsLocation(resultSet.getString("sensors_location"));
                sensorsDataAll.setSensorsDescriptions(resultSet.getString("sensors_descriptions"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sensorsData;
    }

    @Override
    public void update(SensorsData sensorsData) {

    }

    @Override
    public void delete(SensorsData sensorsData) {

    }
}
