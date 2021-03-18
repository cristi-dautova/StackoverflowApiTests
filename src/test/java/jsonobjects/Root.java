package jsonobjects;

import java.util.List;

public class Root {
    public List<Item> items;
    public boolean has_more;
    public int backoff;
    public int quota_max;
    public int quota_remaining;

    @Override
    public String toString() {
        return "Root{" +
                "items=" + items +
                ", has_more=" + has_more +
                ", backoff=" + backoff +
                ", quota_max=" + quota_max +
                ", quota_remaining=" + quota_remaining +
                '}';
    }
}
