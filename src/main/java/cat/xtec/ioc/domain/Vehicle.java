package cat.xtec.ioc.domain;

import java.util.Objects;

/**
 *
 * @author CarlosOO
 */
// Definició de la classe Vehicle, que representa un vehicle amb les seves propietats.
public class Vehicle {

    // Atributs de la classe que emmagatzemen la informació del vehicle.
    private String data;
    private int turismes;
    private int total;
    private String origen;

    // Constructor per defecte (sense paràmetres).
    public Vehicle() {
    }

    // Constructor amb paràmetres per inicialitzar un objecte Vehicle.
    public Vehicle(String data, int turismes, int total, String origen) {

        this.data = data;
        this.turismes = turismes;
        this.total = total;
        this.origen = origen;
    }

    // Getters i Setters
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTurismes() {
        return turismes;
    }

    public void setTurismes(int turismes) {
        this.turismes = turismes;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }

        final Vehicle other = (Vehicle) o;
        return other.getData().equals(this.data);

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.data);
    }

    @Override
    public String toString() {
        return "Vehicle {"
                + "data=" + data
                + '}';
    }

}
