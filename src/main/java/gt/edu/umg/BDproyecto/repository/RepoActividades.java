package gt.edu.umg.database.repository;

import gt.edu.umg.database.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepoActividades extends JpaRepository<Actividad, UUID> {

    // Buscar subtareas de una tarea padre
    List<Actividad> findByParentId(UUID parentId);

    // Buscar tareas sin padre (tareas raíz)
    List<Actividad> findByParentIsNull();

    // Buscar tareas por estado (ej: "pendiente", "completado")
    List<Actividad> findByEstado(String estado);

}
