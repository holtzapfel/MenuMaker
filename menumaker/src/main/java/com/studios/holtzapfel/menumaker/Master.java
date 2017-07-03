package com.studios.holtzapfel.menumaker;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by holtzapfel on 6/26/17.
 */

public class Master {
    public static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    public static boolean verifyStringIsEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean verifyStringIsPhoneNumber(String phoneNumber){
        return phoneNumber.matches("^[+]?[0-9]{10,13}$");
    }
}
