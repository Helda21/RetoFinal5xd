/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.domingo.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tutoria.domingo.modelo.Reservacion;
import tutoria.domingo.repositorio.ContadorClientes;
import tutoria.domingo.servicios.ServiciosReservacion;
import tutoria.domingo.servicios.StatusReservas;

/**
 *
 * @author USUARIO
 */

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class WebReservacion {
     @Autowired
    private ServiciosReservacion servicio;
    @GetMapping("/all")
    public List<Reservacion> getReservations(){
        return servicio.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservacion> getReservation(@PathVariable("id") int reservationId) {
        return servicio.getReservation(reservationId);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservacion save(@RequestBody Reservacion reservation) {
        return servicio.save(reservation);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservacion update(@RequestBody Reservacion reservacion) {
        return servicio.update(reservacion);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int reservationId) {
        return servicio.deleteReservation(reservationId);
    }
    
    
    @GetMapping("/report-status")
    public StatusReservas getReservationsStatusReport(){
        return servicio.getReservationsStatusReport();
    }
    
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservacion> getReservationsReportDates(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo){
    return servicio.getReservationPeriod(dateOne, dateTwo);
    }
    
    @GetMapping("/report-clients")
    public List<ContadorClientes> getReservationsReportsClient(){
        return servicio.getTopClients();
    }
}