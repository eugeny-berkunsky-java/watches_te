package ua.mk.berkut.te.dao;

import ua.mk.berkut.te.entities.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountryDAO {
    private Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Country> findAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from country");
            List<Country> result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String shortName = rs.getString("short_name");
                result.add(new Country(id, name, shortName));
            }
            return result;
        } catch (SQLException e) {
            return null;
        }
    }

    public int add(Country country) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into country (name, short_name) values (?,?)")) {
            preparedStatement.setString(1, country.getName());
            preparedStatement.setString(2, country.getShortName());
            preparedStatement.executeUpdate();
            return 0;
        } catch (SQLException e) {
            return -1;
        }
    }
}
