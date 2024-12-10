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

import projet.entities.Class;
import projet.service.ClassService; 

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping
    public List<Class> getClasses() {
        return classService.getAllClasses();
    }

    @PostMapping("/addclass")
    public Class addClass(@RequestBody Class classEntity) {
        return classService.addClass(classEntity);
    }

    @PutMapping("/updateclass/{classId}")
    public Class updateClass(@PathVariable Long classId, @RequestBody Class classEntity) {
        return classService.updateClass(classId, classEntity);
    }

    @DeleteMapping("/delclass/{classId}")
    public void deleteClass(@PathVariable Long classId) {
        classService.deleteClass(classId);
    }
}
