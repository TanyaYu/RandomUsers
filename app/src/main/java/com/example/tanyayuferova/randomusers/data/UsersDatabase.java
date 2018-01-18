package com.example.tanyayuferova.randomusers.data;

import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AndroidRuntimeException;

import com.example.tanyayuferova.randomusers.entity.User;
import com.example.tanyayuferova.randomusers.utils.JsonUtils;
import com.example.tanyayuferova.randomusers.utils.NetworkUtils;

import java.net.URL;
import java.util.List;

/**
 * Created by Tanya Yuferova on 1/18/2018.
 */

public class UsersDatabase {

    /**
     * Gets users list from the Internet resource
     * Do not execute in the main thread!
     * @param count result list size
     * @return
     */
    @Nullable
    public static List<User> getUsers(int count) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new AndroidRuntimeException("UsersDatabase.getUsers should not be called on the main thread");
        }
        URL data = NetworkUtils.buildUsersUrl(count);
        String json = NetworkUtils.getJsonData(data);
        return JsonUtils.readUsersFromJson(json);
    }
}
