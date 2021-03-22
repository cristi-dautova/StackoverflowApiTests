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

//    public static class Builder {
//        private Owner owner;
//        private boolean is_accepted;
//        private int score;
//        private int last_activity_date;
//        private int creation_date;
//        private int answer_id;
//        private int question_id;
//        private String content_license;
//        private int last_edit_date;
//
//        public Builder() {
//        }
//
//        public Builder withOwner(Owner owner) {
//            this.owner = owner;
//            return this;
//        }
//
//        public Builder withIsAccepted(boolean is_accepted) {
//            this.is_accepted = is_accepted;
//            return this;
//        }
//
//        public Builder withScore(int score) {
//            this.score = score;
//            return this;
//        }
//
//        public Builder withCreationDate(int creation_date) {
//            this.creation_date = creation_date;
//            return this;
//        }
//
//        public Builder withLastActivityDate(int last_activity_date) {
//            this.last_activity_date = last_activity_date;
//            return this;
//        }
//
//        public Builder withLastAnswerId(int answer_id) {
//            this.answer_id = answer_id;
//            return this;
//        }
//
//        public Builder withQuestionId(int question_id) {
//            this.question_id = question_id;
//            return this;
//        }
//
//        public Builder withLastEditDate(int last_edit_date) {
//            this.last_edit_date = last_edit_date;
//            return this;
//        }
//
//        public Builder withContentLicense(String content_license) {
//            this.content_license = content_license;
//            return this;
//        }
//
//        public Item build() {
//
//            Item item = new Item();
//            item.owner = this.owner;
//            item.is_accepted = this.is_accepted;
//            item.score = this.score;
//            item.creation_date = this.creation_date;
//            item.last_activity_date = this.last_activity_date;
//            item.answer_id = this.answer_id;
//            item.question_id = this.question_id;
//            item.last_edit_date = this.last_edit_date;
//            item.content_license = this.content_license;
//
//            return item;
//        }
//    }
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
