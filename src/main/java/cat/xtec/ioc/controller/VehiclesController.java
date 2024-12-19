package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.Vehicle;
import cat.xtec.ioc.domain.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador que gestiona les peticions relacionades amb els vehicles. Exposa
 * diversos endpoints per a la creació, lectura, actualització i eliminació
 * (CRUD) de vehicles.
 *
 * @author CarlosOO
 */
@RestController
public class VehiclesController {

    @Autowired
    private VehicleRepository vehicleRepository; // Repositori de vehicles injectat per a la gestió de dades

    public VehiclesController() {
        // Constructor per defecte
    }

    /**
     * Constructor amb injecció de dependències.
     *
     * @param vehicleRepository Repositori de vehicles per a la injecció.
     */
    public VehiclesController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Mètode auxiliar per afegir les capçaleres CORS a la resposta HTTP.
     *
     * @param response L'objecte HttpServletResponse per establir les
     * capçaleres.
     */
    private void addCORSHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
    }

    /**
     * Endpoint per obtenir tots els vehicles.
     *
     * @param response L'objecte HttpServletResponse per afegir capçaleres CORS.
     * @return Una llista de tots els vehicles.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/vehicles")
    @ResponseBody
    public List<Vehicle> getAllVehicles(HttpServletResponse response) {
        addCORSHeaders(response);
        return vehicleRepository.getAll(); // Retorna tots els vehicles emmagatzemats al repositori
    }

    /**
     * Endpoint per obtenir vehicles per data.
     *
     * @param data La data per filtrar els vehicles.
     * @param response L'objecte HttpServletResponse per afegir capçaleres CORS.
     * @return Una llista de vehicles que coincideixen amb la data
     * proporcionada.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/vehicles/{data}")
    @ResponseBody
    public List<Vehicle> getVehiclesByDate(@PathVariable("data") String data, HttpServletResponse response) {
        addCORSHeaders(response);
        List<Vehicle> filteredVehicles = new ArrayList<>();

        // Filtra la llista de vehicles per la data proporcionada
        for (Vehicle vehicle : vehicleRepository.getAll()) {
            if (vehicle.getData().equals(data)) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }

    /**
     * Endpoint per obtenir vehicles per origen.
     *
     * @param origen L'origen per filtrar els vehicles.
     * @param response L'objecte HttpServletResponse per afegir capçaleres CORS.
     * @return Una llista de vehicles que coincideixen amb l'origen
     * proporcionat.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/origen/{origen}")
    @ResponseBody
    public List<Vehicle> getVehiclesByOrigin(@PathVariable("origen") String origen, HttpServletResponse response) {
        addCORSHeaders(response);
        List<Vehicle> filteredVehicles = new ArrayList<>();

        // Filtra la llista de vehicles per l'origen proporcionat
        for (Vehicle vehicle : vehicleRepository.getAll()) {
            if (vehicle.getOrigen().equals(origen)) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }

    /**
     * Endpoint per afegir un vehicle. Rep un String JSON com a paràmetre.
     *
     * @param data El String JSON amb les dades del vehicle a afegir.
     * @param response L'objecte HttpServletResponse per afegir capçaleres CORS.
     * @return Missatge de confirmació de l'addició del vehicle.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/vehicles")
    @ResponseBody
    public String addVehicle(@RequestBody String data, HttpServletResponse response) {
        addCORSHeaders(response);

        // Converteix el String JSON a un objecte JSONArray
        JSONArray array = new JSONArray(data);
        JSONObject object = array.getJSONObject(0); // Suposa que el JSON conté un únic objecte

        // Extreu els valors del JSON
        String vehicleData = object.getString("data");
        int total = Integer.parseInt(object.getString("total"));
        int turismes = Integer.parseInt(object.getString("turismes"));
        String origen = object.getString("origen");

        // Crea un nou objecte Vehicle
        Vehicle vehicle = new Vehicle(vehicleData, total, turismes, origen);

        // Afegeix el vehicle al repositori
        vehicleRepository.add(vehicle);

        return "Vehicle added successfully!";
    }

    /**
     * Endpoint per actualitzar un vehicle.
     *
     * @param vehicles Llista de vehicles a actualitzar.
     * @param response L'objecte HttpServletResponse per afegir capçaleres CORS.
     * @return Missatge de confirmació de l'actualització dels vehicles.
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/vehicles")
    @ResponseBody
    public String updateVehicle(@RequestBody List<Vehicle> vehicles, HttpServletResponse response) {
        addCORSHeaders(response);
        try {
            for (Vehicle vehicle : vehicles) {
                vehicleRepository.update(vehicle); // Actualitza cada vehicle individualment
            }
            return "Vehicle(s) updated successfully!";
        } catch (RuntimeException e) {
            return "Error: Vehicle(s) not found.";
        }
    }

    /**
     * Endpoint per eliminar vehicles per data.
     *
     * @param data La data per filtrar els vehicles a eliminar.
     * @param response L'objecte HttpServletResponse per afegir capçaleres CORS.
     * @return Missatge de confirmació de l'eliminació dels vehicles.
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/{data}")
    @ResponseBody
    public String deleteVehicle(@PathVariable("data") String data, HttpServletResponse response) {
        addCORSHeaders(response);
        vehicleRepository.delete(data); // Esborra els vehicles segons la data proporcionada
        return "Vehicle(s) deleted successfully!";
    }
}
