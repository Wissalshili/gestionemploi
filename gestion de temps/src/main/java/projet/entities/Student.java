package projet.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    
    private String firstName;
    private String lastName;
    //private String email;
    
    @OneToMany(mappedBy = "student")
    private List<Utilisateur> utilisateurs;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classe;
    
    
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Class getClasse() {
		return classe;
	}
	public void setClasse(Class classe) {
		this.classe = classe;
	}

    
}

