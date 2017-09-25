package marceme.com.blury.Util;

import android.text.format.DateUtils;

import java.util.Date;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public class Helper {
    public static String transformToSpanTime(long time) {
        long now = new Date().getTime();
        return DateUtils.getRelativeTimeSpanString(time, now, DateUtils.SECOND_IN_MILLIS).toString();
    }
}
