package modelpolycalc;

import java.text.NumberFormat;

public class Monomial {
    private double coef;
    private int degree;
    //ma ajuta la fisare in virgula mobila!
    private NumberFormat formatter = NumberFormat.getInstance();

    public Monomial(String input) {
        formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);
        if (input.indexOf('x') == -1) { // nu are x
            degree = 0;
            coef = Integer.parseInt(input);
        } else {//avem cazuri: x, +-x, +-x^an, +-anx, +-anx^an //aducerea tuturor formelor in cea generala anx^n
            if (input.charAt(0) == 'x')
                input = "1" + input;
            else if (input.charAt(1) == 'x' && (input.charAt(0) == '+' || input.charAt(0) == '-'))
                input = input.charAt(0) + "1" + input.substring(1);
            if (input.charAt(input.length() - 1) == 'x')
                input = input + "^1";
            String[] output = input.split("x\\^");
            coef = Integer.parseInt(output[0]);
            degree = Integer.parseInt(output[1]);
        }
    }

    public Monomial(double coef, int degree) {
        this.coef = coef;
        this.degree = degree;
    }

    public double getCoef() {
        return coef;
    }

    public int getDegree() {
        return degree;
    }

    @Override
    public String toString() {
        if (coef != 0) {
            if (degree == 0)
                return ((coef > 0) ? "+" : "") + formatter.format(coef) + "";
            else if (degree == 1 && Math.abs(coef) == 1)
                return ((coef > 0) ? "+" : "-") + "x";
            else if (degree == 1)
                return ((coef > 0) ? "+" : "") + formatter.format(coef) + "x";
            else if (Math.abs(coef) == 1)
                return ((coef > 0) ? "+" : "-") + "x^" + degree;
            else
                return ((coef > 0) ? "+" : "") + formatter.format(coef) + "x^" + degree;
        } else
            return "";
    }

//    public String toStringLatex() {
//        if (coef != 0) {
//            if (degree == 0)
//                return ((coef > 0) ? "+" : "") + formatter.format(coef) + "";
//            else if (degree == 1 && Math.abs(coef) == 1)
//                return ((coef > 0) ? "+" : "-") + "x";
//            else if (degree == 1)
//                return ((coef > 0) ? "+" : "") + formatter.format(coef) + "x";
//            else if (Math.abs(coef) == 1)
//                return ((coef > 0) ? "+" : "-") + "x^{" + degree + "}";
//            else
//                return ((coef > 0) ? "+" : "") + formatter.format(coef) + "x^{" + degree + "}";
//        } else
//            return "";
//    }
}

