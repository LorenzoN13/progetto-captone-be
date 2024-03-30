package capstone_project.project.repository;

import capstone_project.project.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Annotazione per indicare che questa interfaccia è un componente Repository gestito da Spring Data JPA.
@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>, PagingAndSortingRepository<Utente, Integer> {

    // Metodo per cercare un utente dato l'email.
    public Utente findByEmail(String email);

    // Metodo per cercare un utente dato lo username.
    Optional<Utente> findByUsername(String username);
}
