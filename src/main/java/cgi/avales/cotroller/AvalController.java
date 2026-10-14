package cgi.avales.controller;

import cgi.avales.model.Aval;
import cgi.avales.service.AvalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/avales")
@CrossOrigin(origins = "*") // Permite conexiones desde el frontend
public class AvalController {

    @Autowired
    private AvalService avalService;

    // GET: Obtener todos los avales
    @GetMapping
    public List<Aval> listarTodos() {
        return avalService.obtenerTodos();
    }

    // GET: Obtener aval por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aval> obtenerPorId(@PathVariable Long id) {
        return avalService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Crear un nuevo aval
    @PostMapping
    public Aval crear(@RequestBody Aval aval) {
        return avalService.guardar(aval);
    }

    // PUT: Actualizar un aval existente
    @PutMapping("/{id}")
    public ResponseEntity<Aval> actualizar(@PathVariable Long id, @RequestBody Aval avalDetalles) {
        return avalService.obtenerPorId(id).map(avalExistente -> {
            avalExistente.setFecha(avalDetalles.getFecha());
            avalExistente.setNombreSoftware(avalDetalles.getNombreSoftware());
            avalExistente.setVersion(avalDetalles.getVersion());
            avalExistente.setIdioma(avalDetalles.getIdioma());
            avalExistente.setRequiereRenovacion(avalDetalles.getRequiereRenovacion());
            avalExistente.setInstanciaSolicitante(avalDetalles.getInstanciaSolicitante());
            avalExistente.setOficio(avalDetalles.getOficio());
            avalExistente.setTiquete(avalDetalles.getTiquete());
            avalExistente.setCantidad(avalDetalles.getCantidad());
            avalExistente.setMoneda(avalDetalles.getMoneda());
            avalExistente.setPrecioUnitario(avalDetalles.getPrecioUnitario());
            avalExistente.setObservacionesAdicionales(avalDetalles.getObservacionesAdicionales());

            Aval avalActualizado = avalService.guardar(avalExistente);
            return ResponseEntity.ok(avalActualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Eliminar un aval
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (avalService.obtenerPorId(id).isPresent()) {
            avalService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // GET: Buscar por fecha (?fecha=YYYY-MM-DD)
    @GetMapping("/buscar/fecha")
    public List<Aval> buscarPorFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return avalService.buscarPorFecha(fecha);
    }

    // GET: Buscar por software (?software=Nombre)
    @GetMapping("/buscar/software")
    public List<Aval> buscarPorSoftware(@RequestParam String software) {
        return avalService.buscarPorSoftware(software);
    }
}