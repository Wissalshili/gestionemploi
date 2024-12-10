package projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import projet.entities.Session;
import projet.repository.SessionRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }

    public Optional<Session> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
    
    public List<Session> getSessionsBySubject(Long subjectId) {
        return sessionRepository.findBySubjectSubjectId(subjectId);
    }

    public List<Session> getSessionsByRoom(Long roomId) {
        return sessionRepository.findByRoomRoomId(roomId);
    }

    public List<Session> getSessionsByTeacher(Long teacherId) {
        return sessionRepository.findByTeacherTeacherId(teacherId);
    }
    
    public List<Session> findSessionsByMonth(int month, int year) {
        return sessionRepository.findSessionsByMonth(month, year);
    }

    public List<Session> findSessionsByDate(LocalDate date) {
        return sessionRepository.findSessionsBySessionDate(date);
    }

    
    @Scheduled(cron = "0 0 * * * *")
    public void sendScheduledReminders() {
        createRemindersForUpcomingSessions();
    }

    public List<Session> getUpcomingSessions() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24Hours = now.plusDays(1);

        
        return sessionRepository.findBySessionDateBetween(now, next24Hours);
    }

    public void createRemindersForUpcomingSessions() {
        List<Session> upcomingSessions = getUpcomingSessions();

        for (Session session : upcomingSessions) {
            sendReminder(session);
        }
    }

    private void sendReminder(Session session) {
        System.out.println("Reminder for session: " + session.getSubject().getSubjectName() + " on " + session.getSessionDate());
    }

}
