package utils;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ContactDto {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String description;
}
