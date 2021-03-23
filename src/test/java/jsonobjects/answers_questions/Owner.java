package jsonobjects.answers_questions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
public class Owner {

    private int reputation;
    private String user_id;
    private String user_type;
    private String profile_image;
    private String display_name;
    private String link;
    private int accept_rate;
}
