package projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.entities.Room;
import projet.repository.RoomRepository;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long roomId, Room roomDetails) {
        Room room = roomRepository.findById(roomId)
                                  .orElseThrow();

        room.setRoomName(roomDetails.getRoomName());
        room.setCapacity(roomDetails.getCapacity());
        room.setBuilding(roomDetails.getBuilding());
        room.setFloor(roomDetails.getFloor());

        return roomRepository.save(room);
    }

    public void deleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                                  .orElseThrow();

        roomRepository.delete(room);
    }
}

