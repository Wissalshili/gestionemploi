package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
	Optional<Room> findById(Long roomId);
}
