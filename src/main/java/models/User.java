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
public class User
{
    @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @XmlElement
    @Column
    private String role;

    @XmlElement
    @Column
    private String email;


    @XmlElement
    @Column
    private String firstName;

    @XmlElement
    @Column
    private String name;

    @XmlElement
    @Column
    private Boolean isDeleted;

    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Blog> blogs = new ArrayList<>();

    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userSharing")
    private List<Comment> comments = new ArrayList<>();

    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userShared")
    private List<Sharing> shared = new ArrayList<>();



}