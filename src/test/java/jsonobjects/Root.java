package jsonobjects;

import jsonobjects.answers.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString()
public class Root {
    private List<Item> items;
    private boolean has_more;
    private int backoff;
    private int quota_max;
    private int quota_remaining;

//    @Override
//    public String toString() {
//        return "Root{" +
//                "items=" + items +
//                ", has_more=" + has_more +
//                ", backoff=" + backoff +
//                ", quota_max=" + quota_max +
//                ", quota_remaining=" + quota_remaining +
//                '}';
//    }
}
