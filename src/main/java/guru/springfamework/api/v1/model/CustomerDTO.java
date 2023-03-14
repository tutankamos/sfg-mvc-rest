package guru.springfamework.api.v1.model;

import lombok.Data;

// quello che ho nel DTO e' quello che mi interessa poter ritornoare a chi fa le chiamate http, non necessariamente tutto l'oggetto del domain

@Data // @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
public class CustomerDTO {
    // private Long id; // questo non serve perche' non devo rimandarlo a chi mi fa la richiesta http
    private String firstname;
    private String lastname;

    private String url;
}
