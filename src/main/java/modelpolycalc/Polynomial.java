package modelpolycalc;

import exception.PolynomialException;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private ArrayList<Monomial> poly;

    public Polynomial() {
        poly = new ArrayList<>();
    }

    public Polynomial(String input) throws PolynomialException {
        poly = new ArrayList<>();
        if (input.charAt(0) != '+' && input.charAt(0) != '-')
            input = "+" + input;
        computeMonomial(input);
        simplifiesArranges();
    }

    public void simplifiesArranges() {
        TreeMap<Integer, Double> buffer = new TreeMap<>();

        for (Monomial monom : poly)
            buffer.merge(monom.getDegree(), monom.getCoef(), Double::sum);

        poly = new ArrayList<>();
        for (var it : buffer.entrySet())
            if(it.getValue() != 0)
                poly.add(new Monomial(it.getValue(), it.getKey()));
    }

    public void addMonomial(Monomial monom) {
        poly.add(monom);
    }

    private void computeMonomial(String input) throws PolynomialException {
        String regexPattern = "[+-](\\d+x\\^\\d+|x\\^\\d+|\\d*x|\\d+)";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(input);

        String result = "";

        while (matcher.find()) {
            result += matcher.group();
            poly.add(new Monomial(matcher.group()));
        }

        if (!result.equals(input)) {
            poly = new ArrayList<>();
            throw new PolynomialException("Nu ai introdus un polinom corect!");
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (Monomial m : poly)
            output += m;
        if (output.equals(""))
            return 0 + "";
        return output;
    }

    public ArrayList<Monomial> getPoly() {
        return poly;
    }

    public int getDegree() {
        if(poly.size() == 0)
            return 0;
        else
            return poly.get(poly.size() - 1).getDegree();
    }

//    public String toStringLatex() {
//        String output = "";
//        for (Monomial m : poly)
//            output += m.toStringLatex();
//        if (output.equals(""))
//            return 0 + "";
//        return output;
//    }
}
