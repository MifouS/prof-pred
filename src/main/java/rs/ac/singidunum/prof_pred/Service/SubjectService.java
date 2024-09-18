package rs.ac.singidunum.prof_pred.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.prof_pred.Entity.Professor;
import rs.ac.singidunum.prof_pred.Entity.Subject;
import rs.ac.singidunum.prof_pred.Model.SubjectModel;
import rs.ac.singidunum.prof_pred.Repository.SubjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository repository;
    private final ProfessorService professorService;

    public List<Subject> getAllSubjects() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Subject> getSubjectById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }


    public Subject createSubject(SubjectModel model) {
        Subject subject = new Subject();
        Professor prf = professorService.getProfessorById(model.getProfessorId()).orElseThrow();
        subject.setSubject_name(model.getSubject_name());
        subject.setProfessor(prf);
        return repository.save(subject);
    }

    public Subject updateSubject(Integer id, SubjectModel model) {
        Subject subject = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        Professor prf = professorService.getProfessorById(model.getProfessorId()).orElseThrow();
        subject.setSubject_name(model.getSubject_name());
        subject.setProfessor(prf);
        subject.setUpdatedAt(LocalDateTime.now());
        return repository.save(subject);
    }

    public void deleteSubject(Integer id) {
        Subject subject = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        subject.setDeletedAt(LocalDateTime.now());
        repository.save(subject);
    }
}
