package projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.entities.Class;
import projet.repository.ClassRepository;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Optional<Class> getClassById(Long classId) {
        return classRepository.findById(classId);
    }

    public Class addClass(Class classObj) {
        return classRepository.save(classObj);
    }

    public Class updateClass(Long classId, Class classObj) {
        if (classRepository.existsById(classId)) {
            classObj.setClassId(classId);
            return classRepository.save(classObj);
        }
        return null;
    }

    public void deleteClass(Long classId) {
        classRepository.deleteById(classId);
    }
}

