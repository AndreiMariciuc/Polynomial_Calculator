import controllerpolycalc.ControllerPolycalc;
import modelpolycalc.ModelPolycalc;
import viewpolycalc.ViewPolycalc;

public class LaunchPolycalc {
    public static void main(String[] args) {
        ModelPolycalc modelPolycalc = new ModelPolycalc();
        ViewPolycalc viewPolycalc = new ViewPolycalc();
        ControllerPolycalc controllerPolycalc = new ControllerPolycalc(viewPolycalc, modelPolycalc);
    }
}
