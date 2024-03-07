package capstone_project.project.repository;

import capstone_project.project.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    public Utente findByEmail(String email);
}
