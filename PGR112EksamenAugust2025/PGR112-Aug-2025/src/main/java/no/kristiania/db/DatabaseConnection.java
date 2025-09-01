/**
 * DatabaseConnection håndterer tilkoblingen til SQLite-databasen.
 * Mye av koden er viderebygget på Database forelesningene 18 og 19
 * fra PGR112 Masterfilen fra 2023.
 *
 * Dette er et eksempel på:
 *  * - Innkapsling: selve tilkoblingsdetaljene er skjult for resten av programmet
 *  * - Gjenbruk: andre klasser gjenbruker connect() i stedet for å duplisere kode
 */
package no.kristiania.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:items.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void dropTables(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            /**
             * Fjern tabellene i riktig rekkefølge (på grunn av foreign keys)
             * Dette ga meg litt error i started når jeg prøvde å kjøre programmet,
             * da jeg glemte å ha det med.
              */
            stmt.executeUpdate("DROP TABLE IF EXISTS Dog");
            stmt.executeUpdate("DROP TABLE IF EXISTS Cat");
            stmt.executeUpdate("DROP TABLE IF EXISTS Bird");
            stmt.executeUpdate("DROP TABLE IF EXISTS Shelter");
        }
    }

    public static void initializeTables(Connection connection) throws SQLException {
        // Først slett eksisterende tabeller
        dropTables(connection);
        
        // Deretter opprett tabellene på nytt
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS Shelter (
                ShelterID INTEGER PRIMARY KEY,
                Name TEXT NOT NULL,
                Address TEXT NOT NULL,
                PhoneNumber TEXT NOT NULL
            )
        """);

            stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS Dog (
                AnimalID INTEGER PRIMARY KEY,
                Breed TEXT NOT NULL,
                Name TEXT NOT NULL,
                Age INTEGER NOT NULL,
                Vaccinated INTEGER NOT NULL,
                ReadyForAdoption INTEGER NOT NULL,
                ShelterID INTEGER,
                FoodType TEXT NOT NULL,
                DailyFoodAmount INTEGER NOT NULL,
                FOREIGN KEY (ShelterID) REFERENCES Shelter(ShelterID)
            )
        """);

            stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS Cat (
                AnimalID INTEGER PRIMARY KEY,
                Breed TEXT NOT NULL,
                Name TEXT NOT NULL,
                Age INTEGER NOT NULL,
                Vaccinated INTEGER NOT NULL,
                Neutered INTEGER NOT NULL,
                ShelterID INTEGER,
                LitterType TEXT NOT NULL,
                DailyLitterAmount INTEGER NOT NULL,
                FOREIGN KEY (ShelterID) REFERENCES Shelter(ShelterID)
            )
        """);

            stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS Bird (
                AnimalID INTEGER PRIMARY KEY,
                Species TEXT NOT NULL,
                Name TEXT NOT NULL,
                Age INTEGER NOT NULL,
                Vaccinated INTEGER NOT NULL,
                CanFly INTEGER NOT NULL,
                ShelterID INTEGER,
                CageSize TEXT NOT NULL,
                FOREIGN KEY (ShelterID) REFERENCES Shelter(ShelterID)
            )
        """);
        }
    }
}