package projet.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.entities.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
	
    List<Session> findBySubjectSubjectId(Long subjectId);
    List<Session> findByRoomRoomId(Long roomId);
    List<Session> findByTeacherTeacherId(Long teacherId);
    
    @Query("SELECT s FROM Session s WHERE MONTH(s.sessionDate) = :month AND YEAR(s.sessionDate) = :year")
    List<Session> findSessionsByMonth(int month, int year);

    List<Session> findSessionsBySessionDate(LocalDate sessionDate);
    
    @Query("SELECT s FROM Session s WHERE s.sessionDate BETWEEN :startDate AND :endDate")
    List<Session> findBySessionDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


	
}
