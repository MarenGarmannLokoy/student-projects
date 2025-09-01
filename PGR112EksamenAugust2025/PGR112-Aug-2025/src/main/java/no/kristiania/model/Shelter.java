package no.kristiania.model;

/**
 * Klassen Shelter representerer et dyrehjem i systemet.
 * Den inneholder informasjon som ID, navn, adresse og telefonnummer.
 *
 * Klassen demonstrerer:
 * - Innkapsling
 * - God objektmodellering
 * - Datahåndtering med JDBC
 */

public class Shelter {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;

    public Shelter (int id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Gettere – viser innkapsling: feltet er private, men tilgjengelig via metode
    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public String toString() {
        return "Dyrehjem ID:" + getId() +
                "Dyrehjem: " + getName() + '\'' +
                ", Adresse = " + getAddress() + '\'' +
                ", Telefonnummer = " + getPhoneNumber() ;
    }
}
