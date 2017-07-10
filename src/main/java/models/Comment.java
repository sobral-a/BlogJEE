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
    @JoinColumn(name = "id_user")
    private User user;

    @XmlElement
    @ManyToOne
    @JoinColumn(name = "id_post")
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

}
