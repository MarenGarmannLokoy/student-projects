/**
 * Denne klassen er en subklasse av Animal og representerer en spesifikk type dyr
 *  i da†asystemet. Klassen utvider Animal-klassen med
 * felter som er unike for denne dyrearten, og benyttes for å modellere data i henhold
 * til databasen.
 *
 * Klassen overstyrer metoden getDetails(), som returnerer en detaljert tekstbeskrivelse
 * av dyret inkludert de arts-spesifikke feltene. Dette demonstrerer bruk av arv og polymorfi.
 *
 * Instanser av klassen brukes i kommunikasjon med databasen via AnimalRepository.
 */

package no.kristiania.model;

public class Dog extends Animal {
    private String dogBreed;
    private boolean isAdoptionReady;
    private String foodType;
    private int foodAmount;

    public Dog(
            int id,
            String name,
            int age,
            boolean isVaccinated,
            int shelterId,
            String animalType,
            String dogBreed,
            boolean isAdoptionReady,
            String foodType,
            int foodAmount
            ) {
        super(id, name, age, isVaccinated, shelterId, animalType);
        this.dogBreed = dogBreed;
        this.isAdoptionReady = isAdoptionReady;
        this.foodType = foodType;
        this.foodAmount = foodAmount;
    }

    public String getDogBreed() {
        return dogBreed;
    }
    public boolean getIsAdoptionReady() {
        return isAdoptionReady;
    }
    public String getFoodType() {
        return foodType;
    }
    public int getFoodAmount() {
        return foodAmount;
    }

    @Override
    public String getDetails() {
        return "Hund: " +
                "ID=" + getId() +
                ", Navn='" + getName() + '\'' +
                ", Alder=" + getAge() +
                ", Vaksinert=" + getIsVaccinated() +
                ", Shelter ID=" + getShelterId() +
                ", Rase='" + getDogBreed() + '\'' +
                ", Klar for adopsjon=" + getIsAdoptionReady() +
                ", Fôrtype='" + getFoodType() + '\'' +
                ", Mengde fôr=" + getFoodAmount() + "g";
    }

}
