package projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.entities.Teacher;
import projet.entities.Utilisateur;
import projet.repository.TeacherRepository;
import projet.repository.UtilisateurRepository;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository TeacherRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    public Teacher saveTeacher(Teacher teacher) {
        return TeacherRepository.save(teacher);
    }

    public Teacher getTeacherById(Long id) {
        return TeacherRepository.findById(id).orElse(null);
    }
    
	
	public List<Teacher> getAllTeachers() {
		return TeacherRepository.findAll();
	}

    public Teacher updateTeacher(Teacher teacher) {
    	
        return TeacherRepository.save(teacher);
    }
    

    
    @Transactional
    public void deleteTeacherById(Long id) {
        Teacher teacher = TeacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            Utilisateur utilisateur = utilisateurRepository.findByTeacherTeacherId(id);
            if (utilisateur != null) {
                utilisateurRepository.delete(utilisateur);
            }
            TeacherRepository.delete(teacher);
        } else {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
    }


}
