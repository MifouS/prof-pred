package rs.ac.singidunum.prof_pred.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.prof_pred.Entity.Professor;
import rs.ac.singidunum.prof_pred.Model.ProfessorModel;
import rs.ac.singidunum.prof_pred.Service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/professor")
@RequiredArgsConstructor
@CrossOrigin
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping
    public List<Professor> getAll() {
        return service.getAllProfessors();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getProfessorById(id));
    }
    @PostMapping
    public Professor create(@RequestBody ProfessorModel model) {
        return service.saveProfessor(model);
    }

    @PutMapping(path = "/{id}")
    public Professor update(@PathVariable Integer id, @RequestBody ProfessorModel model) {
        return service.updateProfessor(id, model);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.deleteProfessor(id);
    }

}
