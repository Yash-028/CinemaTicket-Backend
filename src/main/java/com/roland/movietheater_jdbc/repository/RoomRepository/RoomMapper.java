package com.roland.movietheater_jdbc.repository.RoomRepository;

import com.roland.movietheater_jdbc.model.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {

    @Override
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        Room room = new Room();
        room.setRoomId(resultSet.getInt("room_id"));
        room.setRoomCapacity(resultSet.getInt("room_capacity"));
        room.setRoomStatus(resultSet.getBoolean("room_status"));
        room.setRoomType(resultSet.getString("room_type"));
        room.setCinemaId(resultSet.getInt("cinema_branch"));

        return room;
    }
}
