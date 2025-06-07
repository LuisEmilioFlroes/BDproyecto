package gt.edu.umg.database.repository;

import gt.edu.umg.database.model.UsuarioSistema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepoUsuarios extends JpaRepository<UsuarioSistema, UUID> {


    UsuarioSistema findByCorreo(String correo);
}
