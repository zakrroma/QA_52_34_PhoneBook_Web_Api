package dto;

import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ContactsDto {
    private List<ContactDto> contacts;
}
