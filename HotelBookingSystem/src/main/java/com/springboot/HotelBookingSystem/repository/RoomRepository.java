package com.springboot.HotelBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.HotelBookingSystem.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	List<Room> findByHotelId(int hid);

	@Query("select r From Room r where r.hotel.id=:hotelId AND r.room_type=:roomType")
	Room findByHotelIdAndRoomType(int hotelId, String roomType);

}
