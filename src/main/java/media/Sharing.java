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
public class Sharing
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @ManyToOne
    @JoinColumn(name = "user_sharing_id")
    private User userSharing;

    @ManyToOne
    @JoinColumn(name = "user_share_to_id")
    private User userShared;
}
