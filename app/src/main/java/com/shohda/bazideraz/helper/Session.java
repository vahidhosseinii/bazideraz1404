package com.shohda.bazideraz.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Session {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    public static final String SETTING_Quiz_PREF = "setting_quiz_pref";



    public static final String LOGIN = "login";
    public static final String USER_ID = "userId";

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String MOBILE = "mobile";
    public static final String PROFILE = "profile";
    public static final String IS_FIRST_TIME = "isfirsttime";



    public Session(Context context) {
        this._context = context;
        pref = PreferenceManager.getDefaultSharedPreferences(_context);
        editor = pref.edit();
    }

    public String getData(String id) {
        return pref.getString(id, "");
    }

    public void setData(String id, String val) {
        editor.putString(id, val);
        editor.commit();
    }



    public static void setReset(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("reset", true);
        editor.apply();
    }

    public static boolean isResetUsed(Context context) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean("reset", false);
    }


    public static void setSkip(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("skip", true);
        editor.apply();
    }

    public static boolean isSkipUsed(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean("skip", false);
    }




    public static void removeSharedPreferencesData(Context mContext) {
        if (mContext != null) {
            SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            if (mSharedPreferences != null) {
                mSharedPreferences.edit().remove("reset").apply();
                mSharedPreferences.edit().remove("skip").apply();
            }
        }
    }





    public static String getUserData(String key, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, null);
    }
    public static String getUserAdsData(String key, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "123");
    }

    public static boolean isLogin(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public static void saveUserDetail(Context context, String userId, String name, String mobile, String profile) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, userId);
        editor.putString(NAME, name);
        editor.putString(MOBILE, mobile);
        editor.putString(PROFILE, profile);
        editor.putBoolean(LOGIN, true);
        editor.apply();
    }


    public static void clearUserSession(Context mContext) {
        if (mContext != null) {
            SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            if (mSharedPreferences != null) {
                mSharedPreferences.edit().remove(USER_ID).apply();
                mSharedPreferences.edit().remove(NAME).apply();
                mSharedPreferences.edit().remove(MOBILE).apply();
                mSharedPreferences.edit().remove(LOGIN).apply();
                mSharedPreferences.edit().remove(PROFILE).apply();
                mSharedPreferences.edit().remove(IS_FIRST_TIME).apply();

            }
        }
    }


    public static void setUserData(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
}