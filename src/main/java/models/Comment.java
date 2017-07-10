package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 10/07/2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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


    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("post: ");
        builder.append(this.post.getTitle()+ '\n');
        builder.append("Content: ");
        builder.append(this.content + '\n');
        builder.append("Date: ");
        builder.append(this.date.toString() + '\n');
        return builder.toString();
    }

}
