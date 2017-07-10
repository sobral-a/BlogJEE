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
public class Profile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="profile")
    private List<Media> medias;
}
