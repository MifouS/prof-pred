package rs.ac.singidunum.prof_pred.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.prof_pred.Entity.Professor;
import rs.ac.singidunum.prof_pred.Model.ProfessorModel;
import rs.ac.singidunum.prof_pred.Repository.ProfessorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;

    public List<Professor> getAllProfessors() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Professor> getProfessorById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public Professor saveProfessor(ProfessorModel model) {
        Professor professor = new Professor();
        professor.setName(model.getName());
        return repository.save(professor);
    }

    public Professor updateProfessor(Integer id, ProfessorModel model) {
        Professor professor = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        professor.setName(model.getName());
        professor.setUpdatedAt(LocalDateTime.now());
        return repository.save(professor);
    }

    public void deleteProfessor(Integer id) {
        Professor professor = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        professor.setDeletedAt(LocalDateTime.now());
        repository.save(professor);
    }

}
