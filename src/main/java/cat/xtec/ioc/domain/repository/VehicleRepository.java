package cat.xtec.ioc.domain.repository;

import cat.xtec.ioc.domain.Vehicle;
import java.util.List;

/**
 * Interfície per gestionar les operacions de repositori de vehicles. Aquesta
 * interfície defineix els mètodes bàsics que qualsevol classe de repositori ha
 * de implementar per a la gestió de vehicles.
 *
 * @author CarlosOO
 */
public interface VehicleRepository {

    /**
     * Recupera tots els vehicles emmagatzemats.
     *
     * @return Una llista de tots els vehicles.
     */
    List<Vehicle> getAll();

    /**
     * Afegeix un nou vehicle al repositori.
     *
     * @param vehicle El vehicle a afegir.
     */
    void add(Vehicle vehicle);

    /**
     * Actualitza un vehicle existent al repositori. Modifica els valors del
     * vehicle guardat, tenint en compte la data i l'origen.
     *
     * @param vehicle El vehicle amb les dades actualitzades.
     */
    void update(Vehicle vehicle);

    /**
     * Elimina un vehicle del repositori segons la seva data. No s'hi passa
     * l'objecte vehicle, sinó el valor que representa la data. Esborra tots els
     * vehicles amb la data passada.
     *
     * @param data La data que identifica els vehicles a eliminar.
     */
    void delete(String data);
}
