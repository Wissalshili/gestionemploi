package projet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projet.entities.Subject;
import projet.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<Subject> getSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/addsubject")
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @PutMapping("/updatesubject/{subjectId}")
    public Subject updateSubject(@PathVariable Long subjectId, @RequestBody Subject subject) {
        return subjectService.updateSubject(subjectId, subject);
    }

    @DeleteMapping("/delsubject/{subjectId}")
    public void deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
    }
}
