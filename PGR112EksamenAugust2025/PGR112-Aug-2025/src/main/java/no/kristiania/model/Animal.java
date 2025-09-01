/**
 * Abstrakt superklasse som representerer alle dyrene og brukes som en felles base.
 * Denne klassen demonstrerer:
 * - Abstraksjon
 * - Arv
 * - Innkapsling
 * - Polymorfi
 */

package no.kristiania.model;

public abstract class Animal {
    private int id;
    private String name;
    private int age;
    private boolean isVaccinated;
    private int shelterId;
    private String animalType;

    /**
     * Konstruktør som brukes av alle subklassene.
     * Setter felles felter for alle dyrene.
     */
    public Animal (int id, String name, int age, boolean isVaccinated, int shelterId, String animalType) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isVaccinated = isVaccinated;
        this.shelterId = shelterId;
        this.animalType = animalType;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public boolean getIsVaccinated() { return isVaccinated; }
    public int getShelterId() { return shelterId; }
    public String getAnimalType() { return animalType; }

    public abstract String getDetails();
}
