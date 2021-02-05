package it.academy.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Oriver");
    }

    public SensorsDataDao getSensorsDataDao() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/product?serverTimezone=UTC",
                "root",
                "root"
        );
        return new SensorsDataDaoImpl(connection);
    }

    public static DaoFactory getInstance(DatabaseName databaseName) throws ClassNotFoundException, IllegalAccessException {
        switch (databaseName) {
            case MYSQL: {
                if (daoFactory == null) {
                    daoFactory = new DaoFactory();
                }
                return daoFactory;
            }
            case ORACLE: {
                return null;
            }
        }
        throw new IllegalAccessException("Sorry database not founded");
    }
}
