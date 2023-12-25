package com.roland.movietheater_jdbc.controller.Admin;

        import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AdminApiResponse {
    private  int adminId;
    private  String adminUserName;
}
