package projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
  
}
