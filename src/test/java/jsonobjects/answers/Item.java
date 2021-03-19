package jsonobjects.answers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
public class Item {

    private Owner owner;
    private boolean is_accepted;
    private int score;
    private int last_activity_date;
    private int creation_date;
    private int answer_id;
    private int question_id;
    private String content_license;
    private int last_edit_date;

//    @Override
//    public String toString() {
//        return "Item{" +
//                "owner=" + owner +
//                ", is_accepted=" + is_accepted +
//                ", score=" + score +
//                ", last_activity_date='" + last_activity_date + '\'' +
//                ", creation_date='" + creation_date + '\'' +
//                ", answer_id=" + answer_id +
//                ", question_id='" + question_id + '\'' +
//                ", content_license='" + content_license + '\'' +
//                ", last_edit_date='" + last_edit_date +
//                '}';
//    }
}
