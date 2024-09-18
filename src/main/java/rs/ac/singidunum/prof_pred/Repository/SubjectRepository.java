package rs.ac.singidunum.prof_pred.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.prof_pred.Entity.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    List<Subject> findAllByDeletedAtIsNull();

    Optional<Subject> findByIdAndDeletedAtIsNull(Integer id);

}
