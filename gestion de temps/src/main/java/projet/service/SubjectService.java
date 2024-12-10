package projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.entities.Subject;
import projet.repository.SubjectRepository;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId);
    }

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long subjectId, Subject subject) {
        if (subjectRepository.existsById(subjectId)) {
            subject.setSubjectId(subjectId);
            return subjectRepository.save(subject);
        }
        return null;
    }

    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}
