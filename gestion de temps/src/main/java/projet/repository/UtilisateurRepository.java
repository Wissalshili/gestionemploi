package projet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import projet.entities.Utilisateur;

public interface UtilisateurRepository  extends JpaRepository <Utilisateur , Long>{
	//login
	public List<Utilisateur> findByEmailAndPassword(String email,String password);	
	public Utilisateur findById(long id);
	Utilisateur findByTeacherTeacherId(Long teacherId);
	Utilisateur findByStudentStudentId(Long studentId);
	Utilisateur findByAdminId(Long adminId);
	public Utilisateur findByEmail(String email);
}
