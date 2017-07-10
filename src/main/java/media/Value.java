package media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by alexa on 10/06/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Value
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String value;

    @ManyToOne
    private Media media;

    @ManyToOne
    @JoinColumn(name = "parameter_id")
    private Parameter parameter;
}
