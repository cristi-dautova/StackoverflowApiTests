package jsonobjects.answers_questions;

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
}
