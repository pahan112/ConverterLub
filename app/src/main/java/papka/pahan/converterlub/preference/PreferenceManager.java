package papka.pahan.converterlub.preference;

import android.content.Context;
import android.content.SharedPreferences;

import papka.pahan.converterlub.BuildConfig;

/**
 * Created by admin on 09.05.2017.
 */

public abstract class PreferenceManager {
    public static final String PARAM_LAST_UPDATE = "PARAM_LAST_UPDATE";

    public static void storeStringParam(Context context, String key, String value) {
        if (context != null) {
            SharedPreferences.Editor editor = context.getSharedPreferences(PreferenceManager.class.getSimpleName(), 0).edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public static String loadStringParam(Context context, String key) {
        if (context == null) {
            return BuildConfig.FLAVOR;
        }
        return context.getSharedPreferences(PreferenceManager.class.getSimpleName(), 0).getString(key, BuildConfig.FLAVOR);
    }
}
