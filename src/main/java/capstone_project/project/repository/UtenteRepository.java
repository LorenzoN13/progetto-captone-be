package capstone_project.project.repository;

import capstone_project.project.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>, PagingAndSortingRepository<Utente, Integer> {
    public Utente findByEmail(String email);
    Optional<Utente> findByUsername(String username);
}
