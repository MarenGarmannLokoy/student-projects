/**
 * AnimalImporter er ansvarlig for å lese inn data om dyrene fra animals.txt-filen
 * og sende disse videre til AnimalRepository for lagring i databasen.
 *
 * Klassen bruker BufferedReader til å lese filen linje for linje.
 * Først hoppes det over shelter-informasjonen da disse importeres av ShelterImporter.java.
 * Deretter leses og tolkes informasjon om hvert dyr:
 * - Felles data for alle dyr (id, navn, alder, vaksinert, shelterId, dyretype)
 * - Dyretype-spesifikke data (avhengig av om det er Hund, Katt eller Fugl)
 *
 * Klassen demonstrerer filhåndtering og bruk av kontrollstrukturer
 * for å skille mellom de ulike typene dyr (via switch).
 *
 * Import-logikken er delt ut i egen klasse for å følge prinsippet om
 * "en klasse – ett ansvar" (single responsibility principle).
 */

package no.kristiania.importer;

import no.kristiania.db.AnimalRepository;
import no.kristiania.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AnimalImporter {
    private final AnimalRepository animalRepository;

    public AnimalImporter(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void importFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            // Hopp over shelter-delen
            int numberOfShelters = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < numberOfShelters; i++) {
                reader.readLine();
                reader.readLine();
                reader.readLine();
                reader.readLine();
                reader.readLine();
            }

            int numberOfAnimals = Integer.parseInt(reader.readLine().trim());

            for (int i = 0; i < numberOfAnimals; i++) {
                int id = Integer.parseInt(reader.readLine().trim());
                String name = reader.readLine().trim();
                int age = Integer.parseInt(reader.readLine().trim());
                boolean vaccinated = Boolean.parseBoolean(reader.readLine().trim());
                int shelterId = Integer.parseInt(reader.readLine().trim());
                String animalType = reader.readLine().trim();

                /*
                Bruker switch for å identifisere dyretypen og opprette riktig objekt.
                Switch delen er basert på ExampleSwitchCases.java fra PGR112 Masterfilen fra undervisningen 2023
                 */
                switch (animalType) {
                    case "Hund" -> {
                        String breed = reader.readLine().trim();
                        boolean adoptionReady = Boolean.parseBoolean(reader.readLine().trim());
                        String foodType = reader.readLine().trim();
                        int foodAmount = Integer.parseInt(reader.readLine().trim());
                        animalRepository.insertDog(new Dog(id, name, age, vaccinated, shelterId, animalType,
                                breed, adoptionReady, foodType, foodAmount));
                    }
                    case "Katt" -> {
                        String breed = reader.readLine().trim();
                        boolean neutered = Boolean.parseBoolean(reader.readLine().trim());
                        String litterType = reader.readLine().trim();
                        int litterAmount = Integer.parseInt(reader.readLine().trim());
                        animalRepository.insertCat(new Cat(id, name, age, vaccinated, shelterId, animalType,
                                breed, neutered, litterType, litterAmount));
                    }
                    case "Fugl" -> {
                        String species = reader.readLine().trim();
                        boolean canFly = Boolean.parseBoolean(reader.readLine().trim());
                        String cageSize = reader.readLine().trim();
                        animalRepository.insertBird(new Bird(id, name, age, vaccinated, shelterId, animalType,
                                species, canFly, cageSize));
                    }
                    // Error print for å catche feil
                    default -> System.err.println("Ukjent dyretype: " + animalType);
                }

                reader.readLine();
            }

        } catch (IOException e) {
            // Error print for å catche feil
            System.err.println("Feil ved lesing av dyr fra fil: " + e.getMessage());
        }
    }
}
