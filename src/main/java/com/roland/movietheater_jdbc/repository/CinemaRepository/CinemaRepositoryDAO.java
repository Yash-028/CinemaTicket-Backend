package com.roland.movietheater_jdbc.repository.CinemaRepository;

import com.roland.movietheater_jdbc.model.CinemaBranch;
import com.roland.movietheater_jdbc.service.CinemaService.FailedToDeleteCinemaException;
import com.roland.movietheater_jdbc.service.CinemaService.FailedToFindCinemaBranchException;
import com.roland.movietheater_jdbc.service.CinemaService.FailedToInsertCinemaException;
import com.roland.movietheater_jdbc.service.CinemaService.FailedToUpdateCinemaException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToInsertRoomInCinemaBranchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

@Repository
public class CinemaRepositoryDAO implements ICinemaRepositoryDAO {

    private static final String SQL_STATEMENT_TO_FIND_CINEMABRANCHES = "select * from cinemabranch";
    private static final String SQL_STATEMENT_TO_INSERT_CINEMABRANCH = "insert into cinemabranch (cinema_name,cinema_address,cinema_phone,cinema_manager,cinema_seat_capacity) values (?,?,?,?,?)";
    private static final String SQL_STATEMENT_TO_DELETE_CINEMABRANCH = "delete from cinemabranch where cinema_id =?";
    private static final String SQL_STATEMENT_TO_UPDATE_CINEMBRANCH = "update cinemabranch set cinema_name = ? , cinema_address = ? , cinema_phone =? , cinema_manager = ?, cinema_seat_capacity =? where cinema_id = ?";
    private static final String SQL_STATEMENT_TO_UPDATE_CINEMA_BRANCH_SEAT_CAPACITY ="UPDATE cinemabranch SET cinema_seat_capacity = ? WHERE cinema_id = ?";
    private static final String SQL_STATEMENT_TO_FIND_THE_SUM_OF_SEATS_IN_A_CINEMA ="SELECT SUM(room_capacity) from room where cinema_branch = ?";
    private static final String SQL_STATMENT_TO_FIND_CINEMA_BRANCH_BY_ID ="SELECT * FROM cinemabranch WHERE cinema_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CinemaBranch createCinemaBranch(CinemaBranch cinemaBranch) throws FailedToInsertCinemaException {


        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_INSERT_CINEMABRANCH, cinemaBranch.getCinemaName(),
                    cinemaBranch.getCinemaAddress(),
                    cinemaBranch.getCinemaPhone(), cinemaBranch.getCinemaManager(), cinemaBranch.getCinemaSeatCapacity());

            return cinemaBranch;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new FailedToInsertCinemaException(e, cinemaBranch);
        }
    }

    @Override
    public CinemaBranch getCinemaBranchForUserById(int cinemaId) throws FailedToFindCinemaBranchException {
        try {
            return jdbcTemplate.queryForObject(SQL_STATMENT_TO_FIND_CINEMA_BRANCH_BY_ID, new CinemaMapper(),cinemaId);
        } catch (Exception e) {
            throw new FailedToFindCinemaBranchException("Cinema Branch Not Found !");
        }
    }


    @Override
    public int deleteCinemaBranch(int cinemaId) throws FailedToDeleteCinemaException {

        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_DELETE_CINEMABRANCH, cinemaId);
            return cinemaId;
        } catch (DataAccessException e) {
            throw new FailedToDeleteCinemaException(e, cinemaId);
        }

    }

    @Override
    public List<CinemaBranch> findAllCinemasBranch() {
        List<CinemaBranch> cinemaBranchList = jdbcTemplate.query(SQL_STATEMENT_TO_FIND_CINEMABRANCHES, new CinemaMapper());

        return cinemaBranchList;

    }

    @Override
    public CinemaBranch updateCinemaBranch(int cinemaId, CinemaBranch cinemaBranch) throws FailedToUpdateCinemaException {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_UPDATE_CINEMBRANCH, cinemaBranch.getCinemaName(), cinemaBranch.getCinemaAddress(),
                    cinemaBranch.getCinemaPhone(), cinemaBranch.getCinemaManager(), cinemaBranch.getCinemaSeatCapacity(), cinemaId);

            return cinemaBranch;
        } catch (DataAccessException e) {
            throw new FailedToUpdateCinemaException(e, cinemaId);
        }
    }

    @Override
    public void updateCinemaCapacity(int roomCapacity, int cinemaId) throws FailedToInsertRoomInCinemaBranchException {
        try {

            Integer sumOfSeatsInCinemaBranch = jdbcTemplate.queryForObject(SQL_STATEMENT_TO_FIND_THE_SUM_OF_SEATS_IN_A_CINEMA,Integer.class,cinemaId);
            if(sumOfSeatsInCinemaBranch ==null) {
                sumOfSeatsInCinemaBranch =roomCapacity;
            }else{
                System.out.println("Seats already existed: " + sumOfSeatsInCinemaBranch + " + roomCapacity: " + roomCapacity);
                sumOfSeatsInCinemaBranch = sumOfSeatsInCinemaBranch + roomCapacity;
            }



            jdbcTemplate.update(SQL_STATEMENT_TO_UPDATE_CINEMA_BRANCH_SEAT_CAPACITY,sumOfSeatsInCinemaBranch,cinemaId);
        } catch (Exception e) {
            throw new FailedToInsertRoomInCinemaBranchException("Failed To Update seat Capacity Of Cinema!");
        }


    }




}
