package com.roland.movietheater_jdbc.repository.CinemaRepository;

import com.roland.movietheater_jdbc.model.CinemaBranch;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CinemaMapper implements RowMapper<CinemaBranch> {

    @Override
    public CinemaBranch mapRow(ResultSet resultSet, int i) throws SQLException {
        CinemaBranch cinemaBranch = new CinemaBranch();
        cinemaBranch.setCinemaId(resultSet.getInt("cinema_id"));
        cinemaBranch.setCinemaName(resultSet.getString("cinema_name"));
        cinemaBranch.setCinemaAddress(resultSet.getString("cinema_address"));
        cinemaBranch.setCinemaPhone(resultSet.getString("cinema_phone"));
        cinemaBranch.setCinemaManager(resultSet.getString("cinema_manager"));
        cinemaBranch.setCinemaSeatCapacity(resultSet.getInt("cinema_seat_capacity"));


        return  cinemaBranch;
    }


}
