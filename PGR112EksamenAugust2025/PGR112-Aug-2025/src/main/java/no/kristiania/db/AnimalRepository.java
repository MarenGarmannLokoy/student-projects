/**
 * AnimalRepository håndterer all kommunikasjon med databasen for delen som har med dyrene å gjøre.
 * Den er ansvarlig for:
 * - Å lagre objekter av typene Dog, Cat og Bird i databasen via JDBC
 * - Å hente ut data om dyr fra databasen
 *
 * Klassen demonstrerer:
 * - JDBC-bruk uten ORM
 * - Bruk av PreparedStatement og try-with-resources for ressurskontroll og sikkerhet
 * - Unntakshåndtering (try/catch med SQLException)
 * - Bruk av polymorfi gjennom returtypen List<Animal>
 *
 * Repository-klassen er bevisst laget for å skille datatilgang fra resten av logikken,
 * i tråd med prinsippet om lav kobling.
 */


package no.kristiania.db;

import no.kristiania.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {
    private final Connection connection;

    public AnimalRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertDog(Dog dog) {
        String sql = "INSERT INTO Dog (AnimalID, Breed, Name, Age, Vaccinated, ReadyForAdoption, ShelterID, FoodType, DailyFoodAmount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, dog.getId());
            stmt.setString(2, dog.getDogBreed());
            stmt.setString(3, dog.getName());
            stmt.setInt(4, dog.getAge());
            stmt.setBoolean(5, dog.getIsVaccinated());
            stmt.setBoolean(6, dog.getIsAdoptionReady());
            stmt.setInt(7, dog.getShelterId());
            stmt.setString(8, dog.getFoodType());
            stmt.setInt(9, dog.getFoodAmount());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Feil ved insertDog: " + e.getMessage());
        }
    }

    public void insertCat(Cat cat) {
        String sql = "INSERT INTO Cat (AnimalID, Breed, Name, Age, Vaccinated, Neutered, ShelterID, LitterType, DailyLitterAmount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cat.getId());
            stmt.setString(2, cat.getCatBreed());
            stmt.setString(3, cat.getName());
            stmt.setInt(4, cat.getAge());
            stmt.setBoolean(5, cat.getIsVaccinated());
            stmt.setBoolean(6, cat.getIsCastrated());
            stmt.setInt(7, cat.getShelterId());
            stmt.setString(8, cat.getSandType());
            stmt.setInt(9, cat.getSandAmount());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Feil ved insertCat: " + e.getMessage());
        }
    }

    public void insertBird(Bird bird) {
        String sql = "INSERT INTO Bird (AnimalID, Species, Name, Age, Vaccinated, CanFly, ShelterID, CageSize) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bird.getId());
            stmt.setString(2, bird.getBirdType());
            stmt.setString(3, bird.getName());
            stmt.setInt(4, bird.getAge());
            stmt.setBoolean(5, bird.getIsVaccinated());
            stmt.setBoolean(6, bird.getCanFly());
            stmt.setInt(7, bird.getShelterId());
            stmt.setString(8, bird.getCageSize());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Feil ved insertBird: " + e.getMessage());
        }
    }

    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Dog");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Dog dog = new Dog(
                        rs.getInt("AnimalID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getBoolean("Vaccinated"),
                        rs.getInt("ShelterID"),
                        "Hund",
                        rs.getString("Breed"),
                        rs.getBoolean("ReadyForAdoption"),
                        rs.getString("FoodType"),
                        rs.getInt("DailyFoodAmount")
                );
                animals.add(dog);
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av hunder: " + e.getMessage());
        }

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Cat");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cat cat = new Cat(
                        rs.getInt("AnimalID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getBoolean("Vaccinated"),
                        rs.getInt("ShelterID"),
                        "Katt",
                        rs.getString("Breed"),
                        rs.getBoolean("Neutered"),
                        rs.getString("LitterType"),
                        rs.getInt("DailyLitterAmount")
                );
                animals.add(cat);
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av katter: " + e.getMessage());
        }

        // Birds
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Bird");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bird bird = new Bird(
                        rs.getInt("AnimalID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getBoolean("Vaccinated"),
                        rs.getInt("ShelterID"),
                        "Fugl",
                        rs.getString("Species"),
                        rs.getBoolean("CanFly"),
                        rs.getString("CageSize")
                );
                animals.add(bird);
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av fugler: " + e.getMessage());
        }

        return animals;
    }

    public List<Bird> getBirdsThatCanFly() {
        List<Bird> birds = new ArrayList<>();

        String sql = "SELECT * FROM Bird WHERE CanFly = true";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bird bird = new Bird(
                        rs.getInt("AnimalID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getBoolean("Vaccinated"),
                        rs.getInt("ShelterID"),
                        "Fugl",
                        rs.getString("Species"),
                        rs.getBoolean("CanFly"),
                        rs.getString("CageSize")
                );
                birds.add(bird);
            }

        } catch (SQLException e) {
            System.err.println("Feil ved henting av fugler som kan fly: " + e.getMessage());
        }

        return birds;
    }

    public List<Animal> getVaccinatedAnimals() {
        List<Animal> animals = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Dog WHERE Vaccinated = true");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Dog dog = new Dog(
                        rs.getInt("AnimalID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getBoolean("Vaccinated"),
                        rs.getInt("ShelterID"),
                        "Hund",
                        rs.getString("Breed"),
                        rs.getBoolean("ReadyForAdoption"),
                        rs.getString("FoodType"),
                        rs.getInt("DailyFoodAmount")
                );
                animals.add(dog);
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av hunder: " + e.getMessage());
        }

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Cat WHERE Vaccinated = true");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cat cat = new Cat(
                        rs.getInt("AnimalID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getBoolean("Vaccinated"),
                        rs.getInt("ShelterID"),
                        "Katt",
                        rs.getString("Breed"),
                        rs.getBoolean("Neutered"),
                        rs.getString("LitterType"),
                        rs.getInt("DailyLitterAmount")
                );
                animals.add(cat);
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av katter: " + e.getMessage());
        }

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Bird WHERE Vaccinated = true");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bird bird = new Bird(
                        rs.getInt("AnimalID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getBoolean("Vaccinated"),
                        rs.getInt("ShelterID"),
                        "Fugl",
                        rs.getString("Species"),
                        rs.getBoolean("CanFly"),
                        rs.getString("CageSize")
                );
                animals.add(bird);
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av fugler: " + e.getMessage());
        }

        return animals;
    }

    public List<Dog> getDogsReadyForAdoption() {
        List<Dog> dogs = new ArrayList<>();

        String sql = "SELECT * FROM Dog WHERE ReadyForAdoption = true";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Dog dog = new Dog(
                            rs.getInt("AnimalID"),
                            rs.getString("Name"),
                            rs.getInt("Age"),
                            rs.getBoolean("Vaccinated"),
                            rs.getInt("ShelterID"),
                            "Hund",
                            rs.getString("Breed"),
                            rs.getBoolean("ReadyForAdoption"),
                            rs.getString("FoodType"),
                            rs.getInt("DailyFoodAmount")
                    );
                    dogs.add(dog);
                }
            }

        } catch (SQLException e) {
            System.err.println("Feil ved henting av hunder som er klar for adopsjon: " + e.getMessage());
        }

        return dogs;
    }

}
