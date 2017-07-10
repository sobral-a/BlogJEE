package media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by alexa on 10/06/2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class File
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String path;

    @Column
    private String format;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;



}
