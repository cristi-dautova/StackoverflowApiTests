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

//    @Override
//    public String toString() {
//        return "Owner{" +
//                "reputation=" + reputation +
//                ", user_id=" + user_id +
//                ", user_type='" + user_type + '\'' +
//                ", accept_rate=" + accept_rate +
//                ", profile_image='" + profile_image + '\'' +
//                ", display_name='" + display_name + '\'' +
//                ", link='" + link +
//                '}';
//    }
}
