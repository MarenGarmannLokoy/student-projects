/**
 * ShelterRepository håndterer all kommunikasjon med databasen for delen som har med dyrene å gjøre.
 * Denne baserer seg mye på hvordan AnimalRepository er satt opp, men da en mye enklere
 */

package no.kristiania.db;

import no.kristiania.model.Shelter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShelterRepository {
    private final Connection connection;

    public ShelterRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertShelter(Shelter shelter) {
        String sql = "INSERT INTO Shelter (ShelterID, Name, Address, PhoneNumber) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shelter.getId());
            stmt.setString(2, shelter.getName());
            stmt.setString(3, shelter.getAddress());
            stmt.setString(4, shelter.getPhoneNumber());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Feil ved insertShelter: " + e.getMessage());
        }
    }
}
