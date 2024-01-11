package com.shohda.bazideraz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ServerData {
    public class verfiy {
        @SerializedName("error")
        String error;
        @SerializedName("message")
        String message;

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}