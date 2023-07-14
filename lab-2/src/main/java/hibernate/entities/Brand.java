package hibernate.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "brands")
public class Brand implements Serializable {
    private static final Long serialId = 12L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;
}
