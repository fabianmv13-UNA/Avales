package cgi.avales.service;

import cgi.avales.model.Aval;
import cgi.avales.repository.AvalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AvalService {

    @Autowired
    private AvalRepository avalRepository;

    // Obtener todos los avales
    public List<Aval> obtenerTodos() {
        return avalRepository.findAll();
    }

    // Obtener un aval por su ID
    public Optional<Aval> obtenerPorId(Long id) {
        return avalRepository.findById(id);
    }

    // Guardar o actualizar un aval
    public Aval guardar(Aval aval) {
        return avalRepository.save(aval);
    }

    // Eliminar un aval por ID
    public void eliminar(Long id) {
        avalRepository.deleteById(id);
    }

    // Buscar por software
    public List<Aval> buscarPorSoftware(String software) {
        return avalRepository.findByNombreSoftwareContainingIgnoreCase(software);
    }

    // Buscar por fecha
    public List<Aval> buscarPorFecha(LocalDate fecha) {
        return avalRepository.findByFecha(fecha);
    }

    // Buscar por tiquete
    public List<Aval> buscarPorTiquete(String tiquete) {
        return avalRepository.findByTiqueteContainingIgnoreCase(tiquete);
    }
}
