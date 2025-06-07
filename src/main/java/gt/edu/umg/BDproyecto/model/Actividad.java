package gt.edu.umg.database.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Actividad {

    @Id
    private UUID id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "El estado es obligatorio (ej. PENDIENTE, EN_PROGRESO, COMPLETADO)")
    private String estado;

    private LocalDateTime createdAt;

    // Relación con tarea padre (si es una subtarea)
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference // ← rompe la recursión hacia arriba
    private Actividad parent;

    // Subtareas relacionadas con esta tarea
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // ← solo serializa una vez hacia abajo
    private List<Actividad> subtasks;

    // Constructor
    public Actividad() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.subtasks = new ArrayList<>();
    }

    // Getters y Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Actividad getParent() {
        return parent;
    }

    public void setParent(Actividad parent) {
        this.parent = parent;
    }

    public List<Actividad> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Actividad> subtasks) {
        this.subtasks = (subtasks != null) ? subtasks : new ArrayList<>();
    }
}
