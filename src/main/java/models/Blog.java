package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Blog
{
    @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @XmlElement
    @Column
    private String title;

    @XmlElement
    @ManyToOne
    @JoinColumn("id_user")
    private User user;

    @XmlElement
    @Column
    private String theme;

    @XmlElement
    @Column
    private Date creationDate;

    @XmlElement
    @Column
    private Boolean isDeleted;

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