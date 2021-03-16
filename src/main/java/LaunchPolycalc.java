import controllerpolycalc.ControllerPolycalc;
import modelpolycalc.operation.ModelOperationPolycalc;
import viewpolycalc.ViewPolycalc;

public class LaunchPolycalc {
    public static void main(String[] args) {
        //launch the app
        ModelOperationPolycalc modelOp = new ModelOperationPolycalc();
        ViewPolycalc viewPolycalc = new ViewPolycalc("PolyCalc");
        ControllerPolycalc controllerPolycalc = new ControllerPolycalc(viewPolycalc, modelOp);
    }
}
