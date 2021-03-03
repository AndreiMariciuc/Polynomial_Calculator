package controllerpolycalc;

import modelpolycalc.ModelPolycalc;
import viewpolycalc.ViewPolycalc;

public class ControllerPolycalc {
    private ViewPolycalc viewPolycalc;
    private ModelPolycalc modelPolycalc;
    public ControllerPolycalc(ViewPolycalc viewPolycalc, ModelPolycalc modelPolycalc) {
        this.viewPolycalc = viewPolycalc;
        this.modelPolycalc = modelPolycalc;
    }
}
