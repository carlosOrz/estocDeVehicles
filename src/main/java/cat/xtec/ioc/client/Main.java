/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.xtec.ioc.client;

import org.springframework.web.client.RestTemplate;

/**
 * Classe principal per a consumir el servei REST que proporciona informació
 * sobre vehicles. Utilitza Spring's RestTemplate per realitzar peticions HTTP a
 * l'API.
 *
 * @author CarlosOO
 */
public class Main {

    public static void main(String[] args) {
        // URL del endpoint REST que es vol consultar
        String url = "http://localhost:8080/vehiclescatalunya/vehicles";

        // Crear una instància de RestTemplate per realitzar la petició HTTP
        RestTemplate restTemplate = new RestTemplate();

        // Realitzar una sol·licitud GET i obtenir un array de VehiclesClient com a resposta
        try {
            // Es realitza una petició GET i es mapeja la resposta a un array de VehiclesClient
            VehiclesClient[] vehicles = restTemplate.getForObject(url, VehiclesClient[].class);

            // Comprovar si s'ha rebut una resposta
            if (vehicles != null) {
                System.out.println("Vehicles de Catalunya i Espanya a l'octubre de 2022:");
                // Iterar sobre el array de vehicles i filtrar-los per la data "oct-22"
                for (VehiclesClient vehicle : vehicles) {
                    if ("oct-22".equals(vehicle.getData())) {  // Filtrar per la data especificada
                        System.out.println(vehicle);  // Mostrar la informació del vehicle
                    }
                }
            } else {
                System.out.println("No s'han trobat vehicles.");
            }
        } catch (Exception e) {
            // Capturar qualsevol error que es produeixi durant la consulta del servei REST
            System.err.println("Error consumint el servei REST: " + e.getMessage());
        }
    }
}
