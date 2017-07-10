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
public class Post
{
    @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @XmlElement
    @ManyToOne
    @JoinColumn(name = "id_blog")
    private Blog blog;

    @XmlElement
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @XmlElement
    @Column
    private String title;

    @XmlElement
    @Column
    private String content;

    @XmlElement
    @Column
    private Date creationDate;

    @XmlElement
    @Column
    private Boolean isDeleted;

    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, mappedBy="post")
    private List<Comment> comments = new ArrayList<>();

}