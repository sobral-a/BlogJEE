package media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alexa on 10/06/2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Parameter
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String type;

    @Column(name = "default_value")
    private String defaultValue;

    @Column
    private String regxp;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameter")
    private List<Value> values;

}
