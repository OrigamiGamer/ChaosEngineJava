package origami.chaosengine.lib;

import com.sun.jna.Library;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;

public final class Libs {
    public static final Kernel32 kernel32 = Kernel32.INSTANCE;
    public static final User32 user32 = User32.INSTANCE;
}
