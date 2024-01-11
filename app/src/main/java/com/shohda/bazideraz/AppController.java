package com.shohda.bazideraz;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AppController extends Application
{
    public static synchronized AppController getInstance() {
        return mInstance;
    }
    private static Context mContext;
    public  ImageLoader imageLoader;
    public static MediaPlayer player;
    public static Activity currentActivity;

    private RequestQueue mRequestQueue;

    private static AppController mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        setContext(getApplicationContext());
        player = new MediaPlayer();
        mediaPlayerInitializer();
        imageLoader = AppController.getInstance().getImageLoader();

    }
    private static void setContext(Context context) {
        mContext = context;
    }
    private com.android.volley.toolbox.ImageLoader mImageLoader;
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new com.shohda.bazideraz.helper.LruBitmapCache());
        }
        return this.mImageLoader;
    }
    public static void mediaPlayerInitializer() {
        try {
          //  int resourceId = R.raw.snd_bg;
           // player = MediaPlayer.create(mContext, resourceId);
            player.setOnCompletionListener(mp -> {

                player.reset();
                player.release();
            });

           // player = MediaPlayer.create(getAppContext(), R.raw.snd_bg);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setLooping(true);
            player.setVolume(1f, 1f);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
    public static String createJWT(String issuer, String subject) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            byte[] apiKeySecretBytes = Constant.JWT_KEY.getBytes();
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            JwtBuilder builder = Jwts.builder()
                    .setIssuedAt(now)
                    .setSubject(subject)
                    .setIssuer(issuer)
                    .signWith(signatureAlgorithm, signingKey);
            return builder.compact();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
}
