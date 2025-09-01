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

public class Cat extends Animal {
    private String catBreed;
    private boolean isCastrated;
    private String sandType;
    private int sandAmount;

    public Cat(
            int id,
            String name,
            int age,
            boolean isVaccinated,
            int shelterId,
            String animalType,
            String catBreed,
            boolean isCastrated,
            String sandType,
            int sandAmount
    ) {
        super(id, name, age, isVaccinated, shelterId, animalType);
        this.catBreed = catBreed;
        this.isCastrated = isCastrated;
        this.sandType = sandType;
        this.sandAmount = sandAmount;
    }

    public String getCatBreed() {
        return catBreed;
    }
    public boolean getIsCastrated() {
        return isCastrated;
    }
    public String getSandType() {
        return sandType;
    }
    public int getSandAmount() {
        return sandAmount;
    }

    // Overstyrer getDetails() for å vise kattespesifikk informasjon
    @Override
    public String getDetails() {
        return "Katt: " +
                "ID =" + getId() +
                ", Navn ='" + getName() + '\'' +
                ", Alder =" + getAge() +
                ", Vaksinert =" + getIsVaccinated() +
                ", Shelter ID =" + getShelterId() +
                ", Rase ='" + getCatBreed() + '\'' +
                ", Er kastrert =" + getIsCastrated() +
                ", Sandtype ='" + getSandType() + '\'' +
                ", Mengde sand =" + getSandAmount() + "g";
    }

}
