package fr.sleeptight.data.fitbit.auth;

/**
 * Created by UserUtils on 6/11/2016.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import fr.sleeptight.R;

/**
 * A BroadcastReceiver that handles the Action Intent from the Custom Tab and shows the Url
 * in a Toast.
 */
public class ActionBroadcastReceiver extends BroadcastReceiver {
    public static final String KEY_ACTION_SOURCE = "org.chromium.customtabsdemos.ACTION_SOURCE";
    public static final int ACTION_ACTION_BUTTON = 1;
    public static final int ACTION_MENU_ITEM = 2;
    public static final int ACTION_TOOLBAR = 3;

    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getDataString();
        if (url != null) {
            String toastText =
                    getToastText(context, intent.getIntExtra(KEY_ACTION_SOURCE, -1), url);
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
        }
    }

    private String getToastText(Context context, int actionId, String url) {
        switch (actionId) {
            case ACTION_ACTION_BUTTON:
                return context.getString(R.string.action_button_toast_text, url);
            case ACTION_MENU_ITEM:
                return context.getString(R.string.menu_item_toast_text, url);
            case ACTION_TOOLBAR:
                return context.getString(R.string.toolbar_toast_text, url);
            default:
                return context.getString(R.string.unknown_toast_text, url);
        }
    }
}
