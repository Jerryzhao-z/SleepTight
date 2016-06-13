package fr.sleeptight.ui.listener;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.support.customtabs.CustomTabsIntent;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import fr.sleeptight.R;
import fr.sleeptight.data.fitbit.chrome.ActionBroadcastReceiver;
import fr.sleeptight.data.fitbit.chrome.CustomTabActivityHelper;
import fr.sleeptight.data.fitbit.chrome.WebviewFallback;
import fr.sleeptight.data.traitement.User;
import fr.sleeptight.ui.ContextHolder;

/**
 * Created by Zhengrui on 6/12/2016.
 */
public class ChromeTabListener implements View.OnClickListener {
    private static final String TAG = "ChromeTabListener";
    private int targetView;
    private final Activity activity;
    private final String url;
    private final boolean SleepTightAuth;

    public ChromeTabListener(int targetView, Activity activity, String url, Boolean SleepTightAuth)
    {
        this.targetView = targetView;
        this.activity = activity;
        this.url = url;
        this.SleepTightAuth = SleepTightAuth;
    }

    public ChromeTabListener( Activity activity, String url, Boolean SleepTightAuth)
    {
        this.activity = activity;
        this.url = url;
        this.SleepTightAuth = SleepTightAuth;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == this.targetView)
        {
                openCustomTab();
        }
    }

    private int getColor(String color) {
        try {
            return Color.parseColor(color);
        } catch (NumberFormatException ex) {
            Log.i(TAG, "Unable to parse Color: " + color);
            return Color.LTGRAY;
        }
    }

    public void openCustomTab() {
        //String url = "http://sleeptight2016.herokuapp.com";
        //String url = "http://sleeptight2016.herokuapp.com/api/v1.0/users/fitbit/auth";

        int color = getColor("#0099cc");
        int secondaryColor = getColor("#0099cc");

        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(color);
        intentBuilder.setSecondaryToolbarColor(secondaryColor);


        String actionLabel = activity.getString(R.string.label_action);
        Bitmap icon = BitmapFactory.decodeResource(activity.getResources(),
                android.R.drawable.ic_menu_share);
        PendingIntent pendingIntent =
                createPendingIntent(ActionBroadcastReceiver.ACTION_ACTION_BUTTON);
        intentBuilder.setActionButton(icon, actionLabel, pendingIntent);

        intentBuilder.setStartAnimations(activity, R.anim.slide_in_right, R.anim.slide_out_left);
        intentBuilder.setExitAnimations(activity, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        CustomTabsIntent customTabsIntent = intentBuilder.build();
        //++
        if(this.SleepTightAuth) {
            Bundle headers = new Bundle();
            User user = User.getInstance();
            String auth = user.getUsername() + ":" + user.getPassword();
            Log.d("AUTH", auth + "->" + String.valueOf(Base64.encodeToString(auth.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP)));
            headers.putString("Authorization", "Basic " + String.valueOf(Base64.encodeToString(auth.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP)));
            //headers.putString("second header key", "Pikachu");
            customTabsIntent.intent.putExtra(Browser.EXTRA_HEADERS, headers);
        }
        //
        //CustomTabActivityHelper.openCustomTab(
        //        this, intentBuilder.build(), Uri.parse(url), new WebviewFallback());
        CustomTabActivityHelper.openCustomTab(
                activity, customTabsIntent, Uri.parse(url), new WebviewFallback());
    }
    private PendingIntent createPendingIntent(int actionSourceId) {
        Intent actionIntent = new Intent(
                ContextHolder.getContext(), ActionBroadcastReceiver.class);
        actionIntent.putExtra(ActionBroadcastReceiver.KEY_ACTION_SOURCE, actionSourceId);
        return PendingIntent.getBroadcast(
                ContextHolder.getContext(), actionSourceId, actionIntent, 0);
    }
}
