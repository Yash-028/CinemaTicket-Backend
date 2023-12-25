package com.roland.movietheater_jdbc.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private int roomId;
    private int roomCapacity;
    private String roomType;
    private boolean roomStatus;
    private int cinemaId;
}
