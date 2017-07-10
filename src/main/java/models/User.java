package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.ws.rs.POST;
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
    private String password;


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
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Post> posts = new ArrayList<>();

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Role: ");
        builder.append(this.role + '\n');
        builder.append("Email: ");
        builder.append(this.email + '\n');
        builder.append("Password: ");
        builder.append(this.password + '\n');
        builder.append("Name: ");
        builder.append(this.name + '\n');
        builder.append("FirstName: ");
        builder.append(this.firstName + '\n');
        return builder.toString();
    }

}