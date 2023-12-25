package com.roland.movietheater_jdbc.controller.room;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomApiResponseForAdmin {
    private int roomId;
    private int roomCapacity;
    private String roomType;
    private boolean roomStatus;
    private int cinemaId;
}
