package capstone_project.project.repository;


import capstone_project.project.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>, PagingAndSortingRepository<Categoria, Integer> {
    public Categoria findByName(String name);
}
