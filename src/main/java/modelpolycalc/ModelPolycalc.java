package modelpolycalc;

import controllerpolycalc.ControllerPolycalc;
import viewpolycalc.ViewPolycalc;

public class ModelPolycalc {
    ModelPolycalc modelPolycalc = new ModelPolycalc();
    ViewPolycalc viewPolycalc = new ViewPolycalc();
    ControllerPolycalc controllerPolycalc = new ControllerPolycalc(viewPolycalc, modelPolycalc);
}
