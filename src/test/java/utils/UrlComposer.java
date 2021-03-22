package utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UrlComposer {

    public static String composeURL (String[] parameterKeys, String[] parameterValues, String ...endPoints) {
        List<String> paramKeys = Arrays.asList(parameterKeys);
        List<String> paramValues = Arrays.asList(parameterValues);

        Map<String, String> queryParameters = IntStream.range(0, paramKeys.size())
                .boxed()
                .collect(Collectors.toMap(i -> paramKeys.get(i), i -> paramValues.get(i)));

        queryParameters.put("site", "stackoverflow");
        queryParameters.put("order", "desc");
        queryParameters.put("sort", "activity");
        queryParameters.put("filter", "default");

        StringBuilder stringBuilder = new StringBuilder();
        for (String endPoint : endPoints) {
            stringBuilder.append(endPoint);
        }
        return stringBuilder.toString() + buildQueryParameters(queryParameters);
    }

    public static String composeURL (String ...endPoints) {
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("site", "stackoverflow");
        queryParameters.put("order", "desc");
        queryParameters.put("sort", "activity");

        StringBuilder stringBuilder = new StringBuilder();
        for (String endPoint : endPoints) {
            stringBuilder.append(endPoint);
        }
        return stringBuilder.toString() + buildQueryParameters(queryParameters);
    }

    private static URIBuilder buildQueryParameters(Map<String, String> queryParameters) {
        List<NameValuePair> queryTokens = new ArrayList<>();
        for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
            queryTokens.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.addParameters(queryTokens);
        return uriBuilder;
    }
}
