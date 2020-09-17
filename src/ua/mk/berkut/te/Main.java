package ua.mk.berkut.te;

import ua.mk.berkut.te.dao.CountryDAO;
import ua.mk.berkut.te.entities.Country;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private CountryDAO countryDAO;

    public static void main(String[] args) throws Exception {
	    new Main().run();
    }

    private void run() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("watches.properties"));
        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
            countryDAO = new CountryDAO(connection);
            processDBQueries();
        }
    }

    private void processDBQueries() {
        Scanner in = new Scanner(System.in);
        int m;
        do {
            System.out.println("0. exit");
            System.out.println("1. Country list");
            System.out.println("2. Add country");
            System.out.println("3. Update country");
            System.out.println("4. Add vendor");
            System.out.println("5. Vendors list");
            m = in.nextInt();
            switch (m) {
                case 1: listCountries(); break;
                case 2: addCountry(); break;
                case 3: updateCountry(); break;
                case 4: addVendor(); break;
                case 5: showVendors(); break;
            }
        } while (m > 0);
    }

    private void showVendors() {

    }

    private void addVendor() {
        //TODO add vendor
    }

    private void updateCountry() {
        Scanner in = new Scanner(System.in);
        int countryId = Integer.parseInt(in.nextLine());
        Country country = countryDAO.find(countryId);
        if (country == null) {
            System.err.println("Error while searching");
            return;
        }
        if (country.getId() > 0) {
            System.out.println(country);
            String newName = in.nextLine();
            String newShortName = in.nextLine();
            if (!newName.isBlank()) {
                country.setName(newName);
            }
            if (!newShortName.isBlank()) {
                country.setShortName(newShortName);
            }
            int result = countryDAO.update(country);
            if (result < 0) {
                System.err.println("Error while updating");
            }
        }
    }

    private void addCountry() {
        Scanner in = new Scanner(System.in);
        System.out.println("--- new country ---");
        System.out.print("Country name: ");
        String name = in.nextLine();
        System.out.print("Country short name: ");
        String shortName = in.nextLine();
        int res = countryDAO.add(new Country(name, shortName));
        if (res < 0) {
            System.err.println("Error while adding");
        }
    }

    private void listCountries() {
        System.out.println("--- All countries ---");
        List<Country> countries = countryDAO.findAll();
        countries.forEach(System.out::println);
    }
}
