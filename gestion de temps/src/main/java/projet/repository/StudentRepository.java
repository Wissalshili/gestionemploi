package projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
