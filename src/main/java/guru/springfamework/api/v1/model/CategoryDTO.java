package guru.springfamework.api.v1.model;

import lombok.Data;

@Data // get set e costruttore (pojo)
public class CategoryDTO {
    private Long id;
    private String name;
}
