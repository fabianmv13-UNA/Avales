package cgi.avales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "avales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @Column(nullable = false)
    private String nombreSoftware; // Lista/Catalogo de Software

    private String version;

    private String idioma; // Lista/Catalogo de Idiomas

    private Boolean requiereRenovacion; // true / false

    private String instanciaSolicitante; // Lista/Catalogo de Instancias

    private String oficio;

    private String tiquete;

    private Integer cantidad;

    private String moneda; // Lista/Catalogo de Moneda (USD, CRC, EUR, etc.)

    private Double precioUnitario;

    private Double costoTotal;

    @Column(columnDefinition = "TEXT")
    private String observacionesAdicionales; // Campo multilinea

    // Método para calcular el costo total automáticamente antes de guardar en la base de datos
    @PrePersist
    @PreUpdate
    public void calcularCostoTotal() {
        if (this.cantidad != null && this.precioUnitario != null) {
            this.costoTotal = this.cantidad * this.precioUnitario;
        }
    }
}