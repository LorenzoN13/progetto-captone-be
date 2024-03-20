package capstone_project.project.repository;

import capstone_project.project.model.Recensione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecensioneRepository extends JpaRepository<Recensione, Integer>, PagingAndSortingRepository<Recensione, Integer> {

    Optional<Recensione> findById(int id);
}
