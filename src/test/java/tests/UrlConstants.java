package tests;

public class UrlConstants {

    public static String STACKEXCHANGE_EMPTY_PARAMETERS_URL = "https://api.stackexchange.com/2.2/answers?site=%s&page=%s&pagesize=%s&order=%s&sort=%s&filter=%s";
    public static String STACKOVERFLOW_PARAMETERS_URL = String.format(STACKEXCHANGE_EMPTY_PARAMETERS_URL, "stackoverflow", "1", "10", "desc", "activity", "default");
}
