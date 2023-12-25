package com.roland.movietheater_jdbc.controller.room;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomApiRequestForAdmin {

    private int roomCapacity;
    private String roomType;
    private boolean roomStatus;

}
