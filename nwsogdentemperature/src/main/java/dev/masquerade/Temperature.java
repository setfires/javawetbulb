package dev.masquerade;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Temperature {

    private String f;
    private String fetchDate;

    public Temperature(String httpBody) {
        this.f = MatchTemp(httpBody);
        this.fetchDate = MatchDate(httpBody);
    }

    private static String MatchTemp(String httpBody) {
        Pattern pattern = Pattern.compile(
                "\\<p\\ class\\=\\\"myforecast\\-current\\-lrg\\\"\\>([\\d\\.]+)\\&deg\\;F\\<\\/p\\>",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(httpBody);

        if (matcher.find()) {
            return matcher.group(1);

        } else {
            return "---";
        }
    }

    public String getF(){ return this.f; }

    public String getFetchDate() { return this.fetchDate; }

    private static String MatchDate(String httpBody) {
        Pattern pattern = Pattern.compile(
                "(\\d{2}\\s\\w{3}\\s\\d{2}\\:\\d{2}\\s(?:AM|PM))",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(httpBody);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "-- --- --:-- --";
        }
    }
}
