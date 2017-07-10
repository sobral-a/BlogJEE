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
    @JoinColumn(name = "id_user")
    private User user;

    @XmlElement
    @Column
    private String theme;

    @XmlElement
    @Column(name = "creation_date")
    private Date creationDate;

    @XmlElement
    @Column
    private Boolean isDeleted;

    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, mappedBy="blog")
    private List<Post> posts = new ArrayList<>();

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Theme: ");
        builder.append(this.theme + '\n');
        builder.append("Creation Date: ");
        builder.append(this.creationDate.toString() + '\n');
        return builder.toString();
    }
}