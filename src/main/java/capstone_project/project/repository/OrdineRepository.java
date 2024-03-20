package capstone_project.project.repository;


import capstone_project.project.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdineRepository  extends JpaRepository<Ordine, Integer>, PagingAndSortingRepository<Ordine, Integer> {

    Optional<Ordine> findById(int id);
}
