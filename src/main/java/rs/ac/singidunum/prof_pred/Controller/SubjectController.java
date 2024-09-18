package rs.ac.singidunum.prof_pred.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.prof_pred.Entity.Subject;
import rs.ac.singidunum.prof_pred.Model.SubjectModel;
import rs.ac.singidunum.prof_pred.Service.SubjectService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/subject")
@RequiredArgsConstructor
@CrossOrigin
public class SubjectController {

    private final SubjectService service;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return service.getAllSubjects();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getSubjectById(id));
    }


    @PostMapping
    public Subject createSubject(@RequestBody SubjectModel subject) {
        return service.createSubject(subject);
    }

    @PutMapping(path = "/{id}")
    public Subject updateSubject(@PathVariable Integer id, @RequestBody SubjectModel subject) {
        return service.updateSubject(id, subject);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable Integer id) {
        service.deleteSubject(id);
    }
}
