package origami.chaosengine;

public class IEngine {
    IEngine() {
    }

    public boolean Init() {
        ChaosEngine.Windows.Init();
        return true;
    }
}
