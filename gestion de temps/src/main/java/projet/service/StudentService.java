package projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.entities.Student;
import projet.entities.Utilisateur;
import projet.repository.StudentRepository;
import projet.repository.UtilisateurRepository;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository StudentRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    public Student saveStudent(Student student) {
        return StudentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return StudentRepository.findById(id).orElse(null);
    }
    
	
	public List<Student> getAllStudents() {
		return StudentRepository.findAll();
	}

    public Student updateStudent(Student student) {
    	
        return StudentRepository.save(student);
    }
    

    
    @Transactional
    public void deleteStudentById(Long id) {
        Student student = StudentRepository.findById(id).orElse(null);
        if (student != null) {
            Utilisateur utilisateur = utilisateurRepository.findByStudentStudentId(id);
            if (utilisateur != null) {
                utilisateurRepository.delete(utilisateur);
            }
            StudentRepository.delete(student);
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }


}
