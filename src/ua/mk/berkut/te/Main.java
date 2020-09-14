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
        main: while (true) {
            System.out.println("0. exit");
            System.out.println("1. Country list");
            System.out.println("2. Add country");
            int m = in.nextInt();
            switch (m) {
                case 0: break main;
                case 1: listCountries(); break;
                case 2: addCountry(); break;
            }
        }
    }

    private void addCountry() {

    }

    private void listCountries() {
        System.out.println("--- All countries ---");
        List<Country> countries = countryDAO.findAll();
        countries.forEach(System.out::println);
    }
}
