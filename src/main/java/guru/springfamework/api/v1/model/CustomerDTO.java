package guru.springfamework.api.v1.model;

import lombok.Data;

@Data // @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
public class CustomerDTO {
    private Long id;
    private String firstname;
    private String lastname;
}
