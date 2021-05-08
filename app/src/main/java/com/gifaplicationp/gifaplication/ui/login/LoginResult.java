package com.gifaplicationp.gifaplication.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer exeptionError;
    @Nullable
    private String error;

    LoginResult(@Nullable Integer exeptionError) {
        this.exeptionError = exeptionError;
        this.error = null;
    }

    LoginResult(@Nullable String error) {
        this.error = error;
        this.exeptionError = null;
    }

    LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return exeptionError;
    }
}