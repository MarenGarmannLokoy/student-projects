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

public class Bird extends Animal {
    private String birdType;
    private boolean canFly;
    private String cageSize;

    public Bird(
            int id,
            String name,
            int age,
            boolean isVaccinated,
            int shelterId,
            String animalType,
            String birdType,
            boolean canFly,
            String cageSize
    ) {
        super(id, name, age, isVaccinated, shelterId, animalType);
        this.birdType = birdType;
        this.canFly = canFly;
        this.cageSize = cageSize;
    }

    public String getBirdType() {
        return birdType;
    }
    public boolean getCanFly() {
        return canFly;
    }
    public String getCageSize() {
        return cageSize;
    }

    @Override
    public String getDetails() {
        return "Fugl: " +
                "ID =" + getId() +
                ", Navn ='" + getName() + '\'' +
                ", Alder =" + getAge() +
                ", Vaksinert =" + getIsVaccinated() +
                ", Shelter ID =" + getShelterId() +
                ", Art ='" + getBirdType() + '\'' +
                ", Kan fly =" + getCanFly() +
                ", Bur størrelse ='" + getCageSize() ;
    }

}
