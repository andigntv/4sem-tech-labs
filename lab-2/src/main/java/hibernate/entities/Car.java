package hibernate.entities;

//import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car implements Serializable {
    private static final Long serialId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "length")
    private Integer length;

    @Column(name = "width")
    private Integer width;

    @Enumerated(EnumType.STRING)
    @Column(name = "body")
    private Body body;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brandId")
    private Brand brand;
}
