/**
 * ShelterImporter er ansvarlig for å lese inn data om dyrene fra animals.txt-filen
 * og sender disse videre til ShelterRepository for lagring i databasen.
 *
 * Klassen fungerer veldig likt som AnimalImporter, men uten like mye som skal håndteres.
 */
package no.kristiania.importer;

import no.kristiania.db.ShelterRepository;
import no.kristiania.model.Shelter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ShelterImporter {
    private final ShelterRepository shelterRepository;

    public ShelterImporter(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public void importFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int numberOfShelters = Integer.parseInt(reader.readLine().trim());

            for (int i = 0; i < numberOfShelters; i++) {
                int id = Integer.parseInt(reader.readLine().trim());
                String name = reader.readLine().trim();
                String address = reader.readLine().trim();
                String phone = reader.readLine().trim();
                reader.readLine();

                Shelter shelter = new Shelter(id, name, address, phone);
                shelterRepository.insertShelter(shelter);
            }

        } catch (IOException e) {
            // Error print for å catche feil
            System.err.println("Feil ved lesing av shelters fra fil: " + e.getMessage());
        }
    }
}
