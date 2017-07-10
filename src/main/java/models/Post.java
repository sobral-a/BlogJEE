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
    @Column(name = "creation_date")
    private Date creationDate;

    @XmlElement
    @Column
    private Boolean isDeleted;

    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, mappedBy="post")
    private List<Comment> comments = new ArrayList<>();


    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Blog: ");
        builder.append(this.blog.getTitle()+ '\n');
        builder.append("Title: ");
        builder.append(this.title + '\n');
        builder.append("Content: ");
        builder.append(this.content + '\n');
        builder.append("User: ");
        builder.append(this.user.getName() + '\n');
        builder.append("Date: ");
        builder.append(this.creationDate.toString() + '\n');
        return builder.toString();
    }


}