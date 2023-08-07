package origami.chaosengine.util;

import com.sun.jna.WString;

public final class StringUtils {
    private StringUtils() {}

    public static WString newWString(String source) {
        return new WString(source);
    }
}
