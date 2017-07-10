package media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 10/06/2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Media
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile profile;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="media")
    private List<Value> values = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
    private List<File> files = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
    private List<Sharing> sharings = new ArrayList<>();

}