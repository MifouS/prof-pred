package rs.ac.singidunum.prof_pred.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.prof_pred.Entity.Professor;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    List<Professor> findAllByDeletedAtIsNull();

    Optional<Professor> findByIdAndDeletedAtIsNull(Integer id);
}
