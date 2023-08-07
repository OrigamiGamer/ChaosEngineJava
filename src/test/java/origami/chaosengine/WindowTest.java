package origami.chaosengine;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinUser.*;
import com.sun.jna.platform.win32.WinUser.WindowProc;
import org.junit.jupiter.api.Test;
import origami.chaosengine.lib.Constants;

import static org.junit.jupiter.api.Assertions.*;
import static origami.chaosengine.lib.Libs.kernel32;
import static origami.chaosengine.lib.Libs.user32;

class WindowTest {
    @Test
    void testCreateWindow() {
        HWND hWnd = createWindow("TestWindowClass", "TestClass");
        assertNotNull(hWnd);
        showWindow(hWnd);
        messageLoop();
        destroyWindow(hWnd);
    }

    private static HWND createWindow(String className, String name) {
        HINSTANCE hInstance = kernel32.GetModuleHandle(null);

        WNDCLASSEX windowClass = new WNDCLASSEX();
        windowClass.hInstance = hInstance;
        windowClass.lpfnWndProc = new TestWindowProc();
        windowClass.lpszClassName = className;
        windowClass.cbSize = windowClass.size();
        user32.RegisterClassEx(windowClass).intValue();

        return user32.CreateWindowEx(
                0,
                className,
                name,
                User32.WS_OVERLAPPEDWINDOW,
                0,
                0,
                800, 600,
                null,
                null,
                hInstance,
                null
        );
    }

    private static void showWindow(HWND hWnd) {
        user32.ShowWindow(hWnd, User32.SW_SHOW);
        user32.UpdateWindow(hWnd);
    }

    private static void messageLoop() {
        MSG msg = new MSG();
        while(user32.GetMessage(msg, null, 0, 0) != 0) {
            user32.TranslateMessage(msg);
            user32.DispatchMessage(msg);
        }
    }

    private static void destroyWindow(HWND hWnd) {
        user32.PostQuitMessage(0);
        user32.DestroyWindow(hWnd);
    }

    private static class TestWindowProc implements WindowProc {
        @Override
        public LRESULT callback(HWND hwnd, int uMsg, WPARAM wParam, LPARAM lParam) {
            switch(uMsg) {
                case User32.WM_DESTROY -> user32.PostQuitMessage(0);
                default -> {
                    return user32.DefWindowProc(hwnd, uMsg, wParam, lParam);
                }
            }
            return null;
        }
    }
}
