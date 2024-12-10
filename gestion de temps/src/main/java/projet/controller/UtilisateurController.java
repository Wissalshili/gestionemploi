package projet.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.security.SecurityConstraints;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import projet.config.EmailAndPasswordRequest;
import projet.entities.Admin;
import projet.entities.Student;
import projet.entities.Teacher;
import projet.entities.Utilisateur;
import projet.service.AdminService;
import projet.service.StudentService;
import projet.service.TeacherService;
import projet.service.UtilisateurService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8080")
public class UtilisateurController {
	
	 	private final UtilisateurService utilisateurService;
	    private final AdminService adminService;
	    private final TeacherService teacherService;
	    private final StudentService studentService;

	
	public UtilisateurController(UtilisateurService utilisateurService, AdminService adminService,TeacherService teacherService, StudentService studentService ) {
	    this.utilisateurService = utilisateurService;
	    this.adminService = adminService;
	    this.teacherService =  teacherService;
	    this.studentService =  studentService;
	    
	}

	@GetMapping
	public  List<Utilisateur> getAllUtilisateurs() {
		return utilisateurService.getAllUtilisateurs();
	}

	
	@PostMapping(path="/RegisterA")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<Admin> registerAdmin(@RequestBody Map<String, Object> request){
	    try {
	    	Admin admin = new Admin();
	    	admin.setNom((String) request.get("nom"));
	    	admin.setPrenom((String) request.get("prenom"));
	    	admin.setNumero((String) request.get("numero"));

	    	Admin savedadmin = adminService.saveAdmin(admin);

	        if (savedadmin != null) {
	            Utilisateur utilisateur = new Utilisateur();
	            utilisateur.setAdmin(savedadmin);
	            utilisateur.setEmail((String) request.get("email"));
		        utilisateur.setPassword((String) request.get("password"));

	            utilisateurService.createUtilisateur(utilisateur);

	            System.out.print(utilisateur);
	        }

	        return new ResponseEntity<>(savedadmin, HttpStatus.CREATED);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PutMapping("updateA/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Long id, @RequestBody Map<String, Object> requestBody) {
	    Admin existingAdmin = adminService.getAdminById(id);
	    if (existingAdmin != null) {
	        Admin updatedAdmin = new Admin();
	        updatedAdmin.setId(id);
	        updatedAdmin.setNom((String) requestBody.get("nom"));
	        updatedAdmin.setPrenom((String) requestBody.get("prenom"));
	        updatedAdmin.setNumero((String) requestBody.get("numero"));


	        Admin savedAdmin = adminService.saveAdmin(updatedAdmin);

	        Utilisateur utilisateur = utilisateurService.findByAdminId(id);
	        if (utilisateur != null) {
	            utilisateur.setAdmin(savedAdmin);
	            utilisateur.setEmail((String) requestBody.get("email"));
	            utilisateur.setPassword((String) requestBody.get("password"));
	            utilisateurService.createUtilisateur(utilisateur);
	        }
	        return ResponseEntity.ok(savedAdmin);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	

	@PostMapping(path = "/RegisterT")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<Teacher> registerTeacher(@RequestBody Map<String, Object> request) {
	    try {
	    	Teacher teacher = new Teacher();
	        teacher.setFirstName((String) request.get("firstname"));
	    	teacher.setLastName((String) request.get("lastname"));
	    	teacher.setDepartment((String) request.get("department"));
	    	teacher.setPhone((String) request.get("phone"));
	    	
	    	Teacher savedTeacher = teacherService.saveTeacher(teacher);

	        if (savedTeacher != null) {
	            Utilisateur utilisateur = new Utilisateur();
	            utilisateur.setTeacher(savedTeacher);
	            utilisateur.setEmail((String) request.get("email"));
		        utilisateur.setPassword((String) request.get("password"));

	            utilisateurService.createUtilisateur(utilisateur);

		           System.out.print(utilisateur);
		       }
		       return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
		   } catch (Exception ex) {
		       ex.printStackTrace();
		       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		   }
		}
	

    
	@PutMapping("updateT/{id}")
	public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") Long id, @RequestBody Map<String, Object> requestBody) {
		Teacher existingTeacher = teacherService.getTeacherById(id);
	    if (existingTeacher != null) {
	    	Teacher updatedteacher = new Teacher();
	        updatedteacher.setTeacherId(id);
	        updatedteacher.setFirstName((String) requestBody.get("firstname"));
	        updatedteacher.setLastName((String) requestBody.get("lastname"));
	        updatedteacher.setDepartment((String) requestBody.get("department"));
	        updatedteacher.setPhone((String) requestBody.get("phone"));

	        Teacher savedTeacher = teacherService.updateTeacher(updatedteacher);

	        Utilisateur utilisateur = utilisateurService.findByTeacherId(id);
	        if (utilisateur != null) {
	            utilisateur.setTeacher(savedTeacher);
	            utilisateur.setEmail((String) requestBody.get("email"));
	            utilisateur.setPassword((String) requestBody.get("password"));
	            utilisateurService.createUtilisateur(utilisateur);
	        }
	        return ResponseEntity.ok(savedTeacher);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	
	@PostMapping(path = "/RegisterS")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<Student> registerStudent(@RequestBody Map<String, Object> request) {
	    try {
	    	Student student = new Student();
	    	student.setFirstName((String) request.get("firstname"));
	    	student.setLastName((String) request.get("lastname"));
	    	
	    	Student savedStudent = studentService.saveStudent(student);

	        if (savedStudent != null) {
	            Utilisateur utilisateur = new Utilisateur();
	            utilisateur.setStudent(savedStudent);
	            utilisateur.setEmail((String) request.get("email"));
		        utilisateur.setPassword((String) request.get("password"));

	            utilisateurService.createUtilisateur(utilisateur);

		           System.out.print(utilisateur);
		       }
		       return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
		   } catch (Exception ex) {
		       ex.printStackTrace();
		       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		   }
		}
	

    
	@PutMapping("updateS/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Map<String, Object> requestBody) {
		Teacher existingStudent = teacherService.getTeacherById(id);
	    if (existingStudent != null) {
	    	Student updatedstudent = new Student();
	    	updatedstudent.setStudentId(id);
	    	updatedstudent.setFirstName((String) requestBody.get("firstname"));
	    	updatedstudent.setLastName((String) requestBody.get("lastname"));

	        Student savedStudent = studentService.updateStudent(updatedstudent);

	        Utilisateur utilisateur = utilisateurService.findByTeacherId(id);
	        if (utilisateur != null) {
	            utilisateur.setStudent(savedStudent);
	            utilisateur.setEmail((String) requestBody.get("email"));
	            utilisateur.setPassword((String) requestBody.get("password"));
	            utilisateurService.createUtilisateur(utilisateur);
	        }
	        return ResponseEntity.ok(savedStudent);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	

    @GetMapping("/getbytech/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurByTeacherId(@PathVariable("id") Long teacherId) {
        Utilisateur utilisateur = utilisateurService.findByTeacherId(teacherId);
        if (utilisateur != null) {
            return ResponseEntity.ok(utilisateur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	@PostMapping(path = "/login")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<List<Utilisateur>> findByEmailAndPassword(@RequestBody EmailAndPasswordRequest emailpassreq) {

	    List<Utilisateur> utilisateurs = utilisateurService.findByEmailAndPassword(emailpassreq.getEmail(), emailpassreq.getPassword());
	    
	    if (utilisateurs.isEmpty()) {
	        return new ResponseEntity<List<Utilisateur>>(HttpStatus.NO_CONTENT);
	    } else {
	        Utilisateur utilisateur = utilisateurs.get(0);

	        if (utilisateur.getAdmin() != null) {
	            System.out.println("Login as Admin: " + utilisateur.getAdmin().getNom());
	        } else if (utilisateur.getTeacher() != null) {
	            System.out.println("Login as Teacher: " + utilisateur.getTeacher().getFirstName());
	        }else if (utilisateur.getStudent() != null) {
	            System.out.println("Login as Student: " + utilisateur.getStudent().getFirstName());
	        }
	        
	        String token = generateJwtToken(utilisateur.getEmail());

	        Map<String, Object> responseBody = new HashMap<>();
	        responseBody.put("token", token);
	        responseBody.put("user", utilisateurs);

	        return new ResponseEntity<List<Utilisateur>>(utilisateurs, HttpStatus.OK);
	    }
	}	
	
	private String generateJwtToken(String email) {
	    String token = Jwts.builder()
	            .setSubject(email)
	            .setExpiration(new Date(System.currentTimeMillis() + 9999999))  
	            .signWith(SignatureAlgorithm.HS512, SecurityConstraints.SECRET_JWT)
	            .compact();

	    System.out.println("Generated JWT Token: " + token);
	    return token;
	}
	
    
	@PutMapping("/updatepass/{email}/{newPassword}")
	public ResponseEntity<String> updatePasswordByEmail(
	        @PathVariable String email,
	        @PathVariable String newPassword) {

	    try {
	    	utilisateurService.updatePasswordByEmail(email, newPassword);
	        return ResponseEntity.ok("Password and Confirm Password updated successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error.");
	    }
	}
	
	
}
