package com.roland.movietheater_jdbc.controller.room;

import com.roland.movietheater_jdbc.model.Room;
import com.roland.movietheater_jdbc.service.RoomService.*;
import com.roland.movietheater_jdbc.service.SeatService.FailedToCreateSeatInCinemaBranchRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class RoomController {


    IRoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }


    @GetMapping("/admin/cinemas/{cinemaId}/rooms")
    public ResponseEntity<List<RoomApiResponseForAdmin>> getAllRoomsForMovieInCinemaBranch(@PathVariable("cinemaId") int cinemaId){
        List<Room> roomList = roomService.findAllRooms(cinemaId);
        List<RoomApiResponseForAdmin> responseForAdminList = buildResponseForAdmin(roomList);
        return  ResponseEntity.status(HttpStatus.OK).body(responseForAdminList);


    }

    @GetMapping("/admin/cinemas/{cinemaId}/rooms/{roomId}")
    public ResponseEntity getRoomInCinemaBranchById(@PathVariable("cinemaId") int cinemaId, @PathVariable("roomId") int roomId){
        try {
            Room room = roomService.getRoomInCinemaBranchById(cinemaId,roomId);
            RoomApiResponseForAdmin response = getRoomApiResponse(room);
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToFindRoomInCinemaBranchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }


    }

    @PostMapping("/admin/cinemas/{cinemaId}/rooms")
    public ResponseEntity createRoomInBranch(@PathVariable("cinemaId") int cinemaId ,@RequestBody RoomApiRequestForAdmin request){
        try {
            Room room = roomService.createRoomInBranch(getRoom(request,cinemaId));
            RoomApiResponseForAdmin response = getRoomApiResponse(room);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToInsertRoomInCinemaBranchException e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (FailedToCreateSeatInCinemaBranchRoom e) {

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }

    @DeleteMapping("/admin/cinemas/{cinemaId}/rooms/{roomId}")
    public ResponseEntity deleteRoomInBranch(@PathVariable("cinemaId") int cinemaId, @PathVariable("roomId") int roomId){
        try {
            int roomIdDeleted = roomService.deleteRoomInBranch(cinemaId,roomId);
            return ResponseEntity.status(HttpStatus.OK).body(roomIdDeleted);
        } catch (FailedToDeleteRoomInCinemaBranchException | FailedToFindRoomInCinemaBranchException | FailedToInsertRoomInCinemaBranchException e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    private Room getRoom(RoomApiRequestForAdmin request, int cinemaId) {
        return Room.builder()
                .roomCapacity(request.getRoomCapacity())
                .roomType(request.getRoomType())
                .roomStatus(request.isRoomStatus())
                .cinemaId(cinemaId)
                .build();
    }

    private List<RoomApiResponseForAdmin> buildResponseForAdmin(List<Room> roomList) {
        List<RoomApiResponseForAdmin> responseForAdminList = new ArrayList<>();
        for(Room room: roomList){
            responseForAdminList.add(getRoomApiResponse(room));
        }
        return  responseForAdminList;

    }

    private RoomApiResponseForAdmin getRoomApiResponse(Room room) {
        return new RoomApiResponseForAdmin().builder()
                .cinemaId(room.getCinemaId())
                .roomId(room.getRoomId())
                .roomCapacity(room.getRoomCapacity())
                .roomStatus(room.isRoomStatus())
                .roomType(room.getRoomType())
                .build();
    }


}














