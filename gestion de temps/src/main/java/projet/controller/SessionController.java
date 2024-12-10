package projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.entities.Session;
import projet.service.SessionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    
    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }
    
    
    @PostMapping("/addsess")
    public ResponseEntity<Session> createOrUpdateSession(@RequestBody Session session) {
        Session savedSession = sessionService.saveSession(session);
        return ResponseEntity.ok(savedSession);
    }


    @DeleteMapping("/delsess/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
    	sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping("/filter/subject/{subjectId}")
    public ResponseEntity<List<Session>> getSessionsBySubject(@PathVariable Long subjectId) {
        List<Session> sessions = sessionService.getSessionsBySubject(subjectId);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/filter/room/{roomId}")
    public ResponseEntity<List<Session>> getSessionsByRoom(@PathVariable Long roomId) {
        List<Session> sessions = sessionService.getSessionsByRoom(roomId);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/filter/teacher/{teacherId}")
    public ResponseEntity<List<Session>> getSessionsByTeacher(@PathVariable Long teacherId) {
        List<Session> sessions = sessionService.getSessionsByTeacher(teacherId);
        return ResponseEntity.ok(sessions);
    }
    
    @GetMapping("/month/{month}/{year}")
    public ResponseEntity<List<Session>> getSessionsByMonth(@PathVariable int month, @PathVariable int year) {
        return ResponseEntity.ok(sessionService.findSessionsByMonth(month, year));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Session>> getSessionsByDate(@PathVariable String date) {
        LocalDate sessionDate = LocalDate.parse(date);
        return ResponseEntity.ok(sessionService.findSessionsByDate(sessionDate));
    }
    
    @GetMapping("/reminders")
    public ResponseEntity<String> createSessionReminders() {
        sessionService.createRemindersForUpcomingSessions();
        return ResponseEntity.ok("Reminders for upcoming sessions are being sent.");
    }
}
