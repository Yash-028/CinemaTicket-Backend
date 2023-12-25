package com.roland.movietheater_jdbc.service.CinemaService;

import com.roland.movietheater_jdbc.model.CinemaBranch;
import com.roland.movietheater_jdbc.repository.CinemaRepository.ICinemaRepositoryDAO;
import com.roland.movietheater_jdbc.service.RoomService.FailedToInsertRoomInCinemaBranchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService implements ICinemaService {


    @Autowired
    private ICinemaRepositoryDAO cinemaRepository;

    @Override
    public List<CinemaBranch> findAllCinemaBranch() {
        return cinemaRepository.findAllCinemasBranch();
    }

    @Override
    public CinemaBranch getCinemaBranchForUserById(int cinemaId) throws FailedToFindCinemaBranchException {
        return cinemaRepository.getCinemaBranchForUserById(cinemaId);
    }

    @Override
    public CinemaBranch createCinemaBranch(CinemaBranch cinemaBranch) throws FailedToInsertCinemaException {
        return cinemaRepository.createCinemaBranch(cinemaBranch);
    }

    @Override
    public int deleteCinemaBranch(int cinemaId) throws FailedToDeleteCinemaException {
       return cinemaRepository.deleteCinemaBranch(cinemaId);

    }

    @Override
    public CinemaBranch updateCinemaBranch(int cinemaId, CinemaBranch cinemaBranch) throws FailedToUpdateCinemaException {
        return cinemaRepository.updateCinemaBranch(cinemaId,cinemaBranch);
    }

    @Override
    public void updateCinemaCapacity(int roomCapacity, int cinemaId) throws FailedToInsertRoomInCinemaBranchException {
        cinemaRepository.updateCinemaCapacity(roomCapacity,cinemaId);
    }




}
