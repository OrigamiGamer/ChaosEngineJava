package origami.chaosengine;

public class IEngine {
    IEngine() {
    }

    public boolean Init(HWND hWnd) {
        ChaosEngine.Windows.Init();
        return true;
    }
}
