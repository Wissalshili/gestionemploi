package projet.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import projet.entities.Teacher;
import projet.service.TeacherService;


@RestController
@RequestMapping("/teachers")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    
    

    public TeacherController(TeacherService teacherService) {
		super();
		this.teacherService = teacherService;
	}
    
    
	@GetMapping
	public  List<Teacher> getAllTeachers() {
		return teacherService.getAllTeachers();
	}


    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
    	Teacher teacher = teacherService.getTeacherById(id);
        if (teacher != null) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") Long id, @RequestBody Teacher updatedTeacher) {
    	Teacher existingTeacher = teacherService.getTeacherById(id);
        if (existingTeacher != null) {
            updatedTeacher.setTeacherId(id);
 
	        Teacher savedTeacher = teacherService.updateTeacher(updatedTeacher);
            return ResponseEntity.ok(savedTeacher);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") Long id) {
        try {
            teacherService.deleteTeacherById(id);
            return new ResponseEntity<>("Teacher with ID: " + id + " has been deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    
}