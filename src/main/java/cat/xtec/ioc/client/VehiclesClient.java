/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.xtec.ioc.client;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa un client de vehicles, utilitzada per a la manipulació
 * de dades de vehicles. Aquesta classe inclou atributs de vehicle amb els seus
 * getters, setters i un mètode toString per a la representació de l'objecte.
 *
 * @author CarlosOO
 */
public class VehiclesClient {

    // Atributs de la classe
    private String data;
    private int turismes;
    private int total;
    private String origen;

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
    public String toString() {
        return "Informacio {"
                + "data='" + data + '\''
                + ", turismes=" + turismes
                + ", total=" + total
                + ", origen='" + origen + '\''
                + '}';
    }
}
