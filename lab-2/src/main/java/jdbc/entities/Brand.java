package jdbc.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    private long id;
    private String name;
    private Date date;
}
