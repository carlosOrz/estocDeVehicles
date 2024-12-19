package cat.xtec.ioc.domain.repository.impl;

import cat.xtec.ioc.domain.Vehicle;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import java.io.*;
import cat.xtec.ioc.domain.repository.VehicleRepository;

/**
 *
 * @author CarlosOO
 */
// Anotació per indicar que aquesta classe és un repositori de Spring.
@Repository
public class InMemoryVehicleRepository implements VehicleRepository {

    // Llista que emmagatzema els vehicles en memòria.
    private List<Vehicle> vehicles = new ArrayList<Vehicle>();

    // Constructor que inicialitza la llista de vehicles amb dades de prova i carrega les dades des d'un fitxer.
    public InMemoryVehicleRepository() {
        // Carrega dues dates de prova a la llista de vehicles.
        Vehicle first = new Vehicle("oct-24", 14000, 15000, "Catalunya");
        Vehicle second = new Vehicle("oct-24", 15000, 16000, "Espanya");
        vehicles.add(first);
        vehicles.add(second);
        loadVehiclesFromFile(); // Carrega les dades addicionals des d'un fitxer CSV.
    }

    // Mètode privat per carregar els vehicles des d'un fitxer CSV.
    private void loadVehiclesFromFile() {
        try {
            // Carrega el fitxer des del directori de recursos.
            InputStream inputStream = getClass().getResourceAsStream("/dades/dades.csv");
            if (inputStream == null) {
                throw new RuntimeException("Fitxer dades.csv no trobat en /resources/dades/");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            // Llegeix el fitxer línia per línia i processa cada línia.
            while ((line = reader.readLine()) != null) {
                // Divideix la línia pel separador ";"
                String[] tempArr = line.split(";");

                // Crea un objecte Vehicle amb les dades llegides.
                Vehicle vehicle = new Vehicle(
                        tempArr[0], // Data
                        Integer.parseInt(tempArr[1]), // Valor 1
                        Integer.parseInt(tempArr[2]), // Valor 2
                        tempArr[3] // Regió
                );

                // Afegeix l'objecte Vehicle a la llista.
                vehicles.add(vehicle);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Imprimeix la traça d'error per facilitar la depuració.
            throw new RuntimeException("Error al llegir el fitxer dades.csv", e);
        }
    }

    // Mètode per obtenir tots els vehicles.
    @Override
    public List<Vehicle> getAll() {
        return vehicles;
    }

    // Mètode per afegir un nou vehicle a la llista.
    @Override
    public void add(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // Mètode per actualitzar un vehicle existent a la llista.
    @Override
    public void update(Vehicle vehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            // Comprova si la data del vehicle coincideix amb l'element de la llista.
            if (vehicles.get(i).getData().equals(vehicle.getData())) {
                vehicles.set(i, vehicle); // Actualitza el vehicle a la posició correcta.
                return;
            }
        }
        // Llança una excepció si el vehicle no es troba a la llista.
        throw new RuntimeException("Vehicle no trobat per actualitzar: " + vehicle.getData());
    }

    // Mètode per eliminar un vehicle de la llista segons la seva data.
    @Override
    public void delete(String data) {
        for (int i = 0; i < vehicles.size(); i++) {
            // Comprova si la data coincideix amb l'element de la llista.
            if (vehicles.get(i).getData().equals(data)) {
                vehicles.remove(i); // Elimina el vehicle de la llista.
                i--;  // Ajusta l'índex perquè s'ha eliminat un element de la llista.
            }
        }
    }
}
