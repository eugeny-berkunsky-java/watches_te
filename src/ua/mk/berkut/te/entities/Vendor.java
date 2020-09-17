package ua.mk.berkut.te.entities;

public class Vendor {
    private int id;
    private String name;
    private Country country;

    public Vendor(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Vendor(int id, String name) {
        this(id, name, null);
    }

    public Vendor(String name) {
        this(0, name, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country.getShortName() +
                '}';
    }
}
