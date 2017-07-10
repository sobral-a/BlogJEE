package models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 10/07/2017.
 */
public class Comment
{

    @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @XmlElement
    @ManyToOne
    @JoinColumn("id_user")
    private User user;

    @XmlElement
    @ManyToOne
    @JoinColumn("id_post")
    private Post post;

    @XmlElement
    @Column
    private String content;

    @XmlElement
    @Column
    private Date date;

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
