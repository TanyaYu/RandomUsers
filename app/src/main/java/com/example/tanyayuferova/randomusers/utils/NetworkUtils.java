package com.example.tanyayuferova.randomusers.utils;

import android.net.Uri;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Tanya Yuferova on 1/18/2018.
 */

public final class NetworkUtils {

    private static final String BASE_URL = "https://randomuser.me/api/";
    private static final String FORMAT_PARAM = "format";
    private static final String FORMAT_DEFAULT = "json";
    private static final String NATIONALITY_PARAM = "nat";
    private static final String[] NATIONALITY_DEFAULT = new String[] {"us","fr","gb"};
    private static final String RESULTS_PARAM = "results";
    private static final int RESULTS_DEFAULT = 100;

    /**
     * Builds url for Users API
     * @param countUsers
     * @return
     */
    public static URL buildUsersUrl(int countUsers) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(FORMAT_PARAM, FORMAT_DEFAULT)
                .appendQueryParameter(NATIONALITY_PARAM, StringUtils.join(",", NATIONALITY_DEFAULT))
                .appendQueryParameter(RESULTS_PARAM, String.valueOf(countUsers))
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * Gets response from url
     *
     * @param url
     * @return
     */
    public static String getJsonData(URL url) {
        String result = null;
        try {
            result = getResponseFromHttpUrl(url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Nullable
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
