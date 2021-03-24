package jsonobjects;

import jsonobjects.answers_questions.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString()
public class Root {
    private List<Item> items;
    private boolean has_more;
    private int backoff;
    private int quota_max;
    private int quota_remaining;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Root root = (Root) o;
        return Objects.equals(this.backoff, root.backoff) &&
                Objects.equals(this.has_more, root.has_more) &&
                Objects.equals(this.quota_max, root.quota_max) &&
                Objects.equals(this.quota_remaining, root.quota_remaining) &&
                Objects.equals(this.items, root.items);
    }
}
