package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserLombok
{
    private String username;
    private String password;
}
