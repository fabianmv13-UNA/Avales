package cgi.avales.repository;

import cgi.avales.model.Aval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvalRepository extends JpaRepository<Aval, Long> {

    // Buscar por fecha específica
    List<Aval> findByFecha(LocalDate fecha);

    // Buscar entre un rango de fechas
    List<Aval> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    // Buscar por nombre de software (filtrando sin importar mayúsculas/minúsculas)
    List<Aval> findByNombreSoftwareContainingIgnoreCase(String nombreSoftware);

    // Buscar por tiquete o número de oficio
    List<Aval> findByTiqueteContainingIgnoreCase(String tiquete);
    List<Aval> findByOficioContainingIgnoreCase(String oficio);

    // Buscar por instancia solicitante
    List<Aval> findByInstanciaSolicitante(String instanciaSolicitante);
}