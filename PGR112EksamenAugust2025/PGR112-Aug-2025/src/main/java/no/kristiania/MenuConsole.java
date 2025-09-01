/**
* Jeg valgte å lage en egen klasse for å håndtere menyen og brukergrensesnitt. Dette gjorde jeg istedenfor å ha det i
* Main.java da dette gir meg flere fordeller:
* - Det holder Main.java ryddig og oversiktelig.
* - Jeg følger prinsipper "En klasse- ett ansvar".
* - Jeg kan gjøre endringer i menyen uten å rote med tilkobling og setup i Main.
* - Det holder koden min strukturert.
 */

package no.kristiania;

import no.kristiania.db.AnimalRepository;
import no.kristiania.model.Animal;
import no.kristiania.model.Bird;
import no.kristiania.model.Dog;

import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private final AnimalRepository animalRepository;

    public MenuConsole(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMeny");
            System.out.println("1. Vis alle dyr");
            System.out.println("2. Vis alle fugler som kan fly");
            System.out.println("3. Vis alle vaksinerte dyr");
            System.out.println("4. Vis alle hunder som er klar til å adopteres");
            System.out.println("5. Avslutt");

            System.out.print("Velg et alternativ: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    List<Animal> animals = animalRepository.getAllAnimals();
                    animals.forEach(a -> System.out.println(a.getDetails()));
                }
                case "2" -> {
                    List<Bird> birds = animalRepository.getBirdsThatCanFly();
                    birds.forEach(b -> System.out.println(b.getDetails()));
                }
                case "3" -> {
                    List<Animal> vaccinated = animalRepository.getVaccinatedAnimals();
                    vaccinated.forEach(v -> System.out.println(v.getDetails()));
                }
                case "4" -> {
                    List<Dog> adoptable = animalRepository.getDogsReadyForAdoption();
                    adoptable.forEach(d -> System.out.println(d.getDetails()));
                }
                case "5" -> {
                    System.out.println("Avslutter programmet.");
                    running = false;
                }
                default -> System.out.println("Ugyldig valg, prøv igjen.");
            }
        }
    }
}
