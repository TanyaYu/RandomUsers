package com.example.tanyayuferova.randomusers.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;

import com.example.tanyayuferova.randomusers.entity.User;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya Yuferova on 1/18/2018.
 */

public final class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getName();

    /**
     * Creates User objects from JSON string
     * @param jsonString
     * @return
     */
    @Nullable
    public static List<User> readUsersFromJson(String jsonString) {
        if (TextUtils.isEmpty(jsonString))
            return null;

        List<User> users = new ArrayList<>();
        JsonReader reader = new JsonReader(new StringReader(jsonString));

        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {
                    case "results":
                        reader.beginArray();
                        while (reader.hasNext()) {
                            users.add(readUser(reader));
                        }
                        reader.endArray();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Creates User object from JsonReader
     * @param reader
     * @return
     */
    private static User readUser(JsonReader reader) {
        User user = new User();
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                switch (reader.nextName()) {
                    case "gender": user.setGender(reader.nextString()); break;
                    case "email": user.setEmail(reader.nextString()); break;
                    case "phone": user.setPhone(reader.nextString()); break;
                    case "nat": user.setNationality(reader.nextString()); break;
                    case "name": {
                        reader.beginObject();
                        while (reader.hasNext()) {
                            switch (reader.nextName()) {
                                case "first": user.setFirstName(reader.nextString()); break;
                                case "last": user.setLastName(reader.nextString()); break;
                                default: reader.skipValue(); break;
                            }
                        }
                        reader.endObject();
                        break;
                    }
                    case "location": {
                        reader.beginObject();
                        while (reader.hasNext()) {
                            switch (reader.nextName()) {
                                case "street": user.getLocation().setStreet(reader.nextString()); break;
                                case "city": user.getLocation().setCity(reader.nextString()); break;
                                case "postcode": user.getLocation().setPostCode(reader.nextString()); break;
                                case "state": user.getLocation().setState(reader.nextString()); break;
                                default: reader.skipValue(); break;
                            }
                        }
                        reader.endObject();
                        break;
                    }
                    case "picture": {
                        reader.beginObject();
                        while (reader.hasNext()) {
                            switch (reader.nextName()) {
                                case "large": user.setPhotoLarge(reader.nextString()); break;
                                case "medium": user.setPhotoMedium(reader.nextString()); break;
                                case "thumbnail": user.setPhotoThumbnail(reader.nextString()); break;
                                default: reader.skipValue(); break;
                            }
                        }
                        reader.endObject();
                        break;
                    }
                    default: reader.skipValue(); break;
                }
            }
            reader.endObject();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
        return user;
    }
}
