package ua.mk.berkut.te.dao;

import ua.mk.berkut.te.entities.Country;
import ua.mk.berkut.te.entities.Vendor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendorDAO {
    private Connection connection;

    public VendorDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Vendor> findAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select *\n" +
                    "   from vendor v\n" +
                    "   join country c on c.id = v.country_id");
            List<Vendor> vendors = new ArrayList<>();
            while (resultSet.next()) {
                int vendorID = resultSet.getInt("v.id");
                String vendorName = resultSet.getString("v.name");
                int countryId = resultSet.getInt("c.id");
                String countryName = resultSet.getString("c.name");
                String countryShortName = resultSet.getString("short_name");
                Vendor vendor = new Vendor(vendorID, vendorName);
                if (countryId != 0) {
                    Country country = new Country(countryId, countryName, countryShortName);
                    vendor.setCountry(country);
                }
                vendors.add(vendor);
            }
            return vendors;
        } catch (SQLException e) {
            return null;
        }
    }
}
