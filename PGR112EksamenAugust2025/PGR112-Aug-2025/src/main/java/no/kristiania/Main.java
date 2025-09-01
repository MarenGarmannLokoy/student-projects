/**
 *Se rapporten for detaljer rundt kildebruk og inspirasjon til prosjektet.
 *
 * Main-klassen er startpunktet for programmet mitt. Her gjør jeg følgende:
 * Punkt 1: Knytter til databasen via DatabaseConnection.
 * Punkt 2: Initialiserer nødvendige tabeller.
 * Punkt 3: Setter opp repositories for å kunne kommunisere med databasen.
 * Punkt 4: Importerer data fra animals.txt ved hjelp av ShelterImporter og AnimalImporter.
 * Punkt 5: Starter menyen via MenuConsole, som tilbyr ulike funksjoner som bruker kan velge.
 */

package no.kristiania;

import no.kristiania.db.AnimalRepository;
import no.kristiania.db.ShelterRepository;
import no.kristiania.db.DatabaseConnection;
import no.kristiania.importer.AnimalImporter;
import no.kristiania.importer.ShelterImporter;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Punkt 1: Knytter til databasen via DatabaseConnection.
        try (Connection connection = DatabaseConnection.connect()) {
            // Punkt 2: Initialiserer nødvendige tabeller.
            DatabaseConnection.initializeTables(connection);

            // Punkt 3: Setter opp repositories for å kunne kommunisere med databasen.
            ShelterRepository shelterRepository = new ShelterRepository(connection);
            AnimalRepository animalRepository = new AnimalRepository(connection);

            ShelterImporter shelterImporter = new ShelterImporter(shelterRepository);
            AnimalImporter animalImporter = new AnimalImporter(animalRepository);

            // Punkt 4: Importerer data fra animals.txt ved hjelp av ShelterImporter og AnimalImporter.
            shelterImporter.importFromFile("src/main/resources/animals.txt");
            animalImporter.importFromFile("src/main/resources/animals.txt");

            // Punkt 5: Starter menyen via MenuConsole, som tilbyr ulike funksjoner som bruker kan velge.
            MenuConsole menu = new MenuConsole(animalRepository);
            menu.start();

        } catch (SQLException e) {
            System.err.println("Database-feil: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Generell feil: " + e.getMessage());
            e.printStackTrace();
        }
    }
}