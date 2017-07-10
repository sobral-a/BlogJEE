package media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User
{
   @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @XmlElement
    @Column
    private String login;

    @XmlElement
    @Column
    private String email;

    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Media> medias = new ArrayList<>();

    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userSharing")
    private List<Sharing> sharings = new ArrayList<>();

    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userShared")
    private List<Sharing> shared = new ArrayList<>();



}