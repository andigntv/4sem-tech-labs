package jdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private long id;
    private String name;
    private int length;
    private int width;
    private String body;
    private long brandId;
}
