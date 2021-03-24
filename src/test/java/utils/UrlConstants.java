package utils;

public class UrlConstants {

    public static final String BASE_URL = "https://api.stackexchange.com/2.2";

    public static String ANSWERS_END_POINT_WITH_BASE_PARAMETERS = "/answers?site=stackoverflow&order=desc&sort=activity&filter=default&page=%s&pagesize=%s";
    public static String QUESTIONS_IDS_ANSWERS_END_POINT_WITH_BASE_PARAMETERS = "/questions/{ids}/answers?order=desc&sort=activity&site=stackoverflow";
}
