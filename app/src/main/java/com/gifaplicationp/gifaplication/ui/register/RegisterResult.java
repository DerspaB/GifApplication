package com.gifaplicationp.gifaplication.ui.register;

import androidx.annotation.Nullable;

public class RegisterResult {
    @Nullable
    private boolean success;
    @Nullable
    private Integer exeptionError;
    private String displayName;

    public RegisterResult(@Nullable Integer exeptionError) {
        this.exeptionError = exeptionError;

    }

    public RegisterResult(@Nullable boolean success, String displayName) {
        this.success = success;
        this.displayName = displayName;
    }

    @Nullable
    boolean getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return exeptionError;
    }

    String getDisplayName() {
        return displayName;
    }
}
