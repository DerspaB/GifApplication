package com.gifaplicationp.gifaplication.data;

import android.content.Context;
import android.util.Log;

import com.gifaplicationp.gifaplication.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    UserService userService;

    public Result<LoggedInUser> login(String username, String password, Context context) {

        try {
            // TODO: handle loggedInUser authentication
            userService = new UserService(username, password, context);
            userService.loginAuthenticate();

            if (userService.getAuthenticateError() == null) {
                LoggedInUser user =
                        new LoggedInUser(
                                userService.getUserId(),
                                userService.getUsername());
                return new Result.Success<>(user);
            } else {
                Log.i("ERROR", " - " + userService.getAuthenticateError());
                return new Result.Error(userService.getAuthenticateError());
            }
        } catch (Exception e) {
            Log.i("ERROR", " - " + e.getMessage());
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}