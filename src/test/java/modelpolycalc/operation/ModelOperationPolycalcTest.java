package modelpolycalc.operation;

import exception.PolynomialException;
import modelpolycalc.Monomial;
import modelpolycalc.Polynomial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Toate exemplele au fost testate folosind matlab in prealabil!
 */

class ModelOperationPolycalcTest {

    @ParameterizedTest
    @MethodSource("inputAdd")
    @DisplayName("verificare add polinoame")
    void add(Polynomial p1, Polynomial p2, Polynomial p3) {
        ModelOperationPolycalc m = new ModelOperationPolycalc(p1, p2);
        assertEquals(m.add().toString(), p3.toString());
    }

    @ParameterizedTest
    @MethodSource("inputSub")
    @DisplayName("verificare sub polinoame")
    void sub(Polynomial p1, Polynomial p2, Polynomial p3) {
        ModelOperationPolycalc m = new ModelOperationPolycalc(p1, p2);
        assertEquals(m.sub().toString(), p3.toString());
    }

    @ParameterizedTest
    @MethodSource("inputMul")
    @DisplayName("verificare mul polinoame")
    void mul(Polynomial p1, Polynomial p2, Polynomial p3) {
        ModelOperationPolycalc m = new ModelOperationPolycalc(p1, p2);
        assertEquals(m.mul().toString(), p3.toString());
    }

    @ParameterizedTest
    @MethodSource("inputDiv")
    @DisplayName("verificare div polinoame")
    void div(Polynomial p1, Polynomial p2, Polynomial aq, Polynomial ar) throws PolynomialException {
        ModelOperationPolycalc m = new ModelOperationPolycalc(p1, p2);
        List<Polynomial> rez = m.div();
        rez.get(0).simplifiesArranges();
        assertTrue(aq.toString().equals(rez.get(0).toString()) && ar.toString().equals(rez.get(1).toString()));
    }

    @ParameterizedTest
    @MethodSource("inputInteg")
    @DisplayName("verificare integ polinoame")
    void integ(Polynomial p1, Polynomial a) {
        ModelOperationPolycalc m = new ModelOperationPolycalc(p1);
        assertEquals(m.integ().toString(), a.toString());
    }

    @ParameterizedTest
    @MethodSource("inputDeriv")
    @DisplayName("verificare deriv polinoame")
    void deriv(Polynomial p1, Polynomial a) {
        ModelOperationPolycalc m = new ModelOperationPolycalc(p1);
        assertEquals(m.deriv().toString(), a.toString());
    }

    private static List<Arguments> inputAdd() throws PolynomialException {
        List<Arguments> arg = new ArrayList<>();
        arg.add(Arguments.of(new Polynomial("1+2x+x^2"), new Polynomial("x^10+1"), new Polynomial("2+2x+x^2+x^10")));
        arg.add(Arguments.of(new Polynomial("1-2x+3x+20x^20+23x^2"), new Polynomial("100-x+21x^2+21+x^20"), new Polynomial("+122+44x^2+21x^20")));
        arg.add(Arguments.of(new Polynomial("100+x^100-x^300+2220x^300"), new Polynomial("-100-x^100+x^300-2220x^300+1"), new Polynomial("1")));
        arg.add(Arguments.of(new Polynomial("x^600+700x^4-20+50x+x"), new Polynomial("-x^600-700x^4+20+50x+x^2"), new Polynomial("x^2+101x")));
        arg.add(Arguments.of(new Polynomial("1000x^10000+1020x^323-232x^232+1+x"), new Polynomial("1000x^10000-1020x^323+232x^232+1-x"), new Polynomial("2000x^10000+2")));
        return arg;
    }

    private static List<Arguments> inputSub() throws PolynomialException {
        List<Arguments> arg = new ArrayList<>();
        arg.add(Arguments.of(new Polynomial("1+2x+x^2"), new Polynomial("x^10+1"), new Polynomial("+2x+x^2-x^10")));
        arg.add(Arguments.of(new Polynomial("1-2x+3x+20x^20+23x^2"), new Polynomial("100-x+21x^2+21+x^20"), new Polynomial("-120+2x+2x^2+19x^20")));
        arg.add(Arguments.of(new Polynomial("100+x^100-x^300+2220x^300"), new Polynomial("-100-x^100+x^300-2220x^300+1"), new Polynomial("+199+2x^100+4438x^300")));
        arg.add(Arguments.of(new Polynomial("x^600+700x^4-20+50x+x"), new Polynomial("-x^600-700x^4+20+50x+x^2"), new Polynomial("-40+x-x^2+1400x^4+2x^600")));
        arg.add(Arguments.of(new Polynomial("1000x^10000+1020x^323-232x^232+1+x"), new Polynomial("1000x^10000-1020x^323+232x^232+1-x"), new Polynomial("+2x-464x^232+2040x^323")));
        return arg;
    }

    private static List<Arguments> inputMul() throws PolynomialException {
        List<Arguments> arg = new ArrayList<>();
        arg.add(Arguments.of(new Polynomial("x^3+x^2+1"), new Polynomial("20x^20+1"), new Polynomial("20x^23+20x^22+20x^20+x^3+x^2+1")));
        arg.add(Arguments.of(new Polynomial("1-2x+3x+20x^20+23x^2"), new Polynomial("100-x+21x^2+21+x^20"), new Polynomial("+121+120x+2803x^2-2x^3+483x^4+2421x^20-19x^21+443x^22+20x^40")));
        arg.add(Arguments.of(new Polynomial("100+x^100-x^300+2220x^300"), new Polynomial("-100-x^100+x^300-2220x^300+1"), new Polynomial("-9900-199x^100-x^200-441581x^300-4438x^400-4923961x^600")));
        arg.add(Arguments.of(new Polynomial("x^600+700x^4-20+50x+x"), new Polynomial("-x^600-700x^4+20+50x+x^2"), new Polynomial("-400+20x+2530x^2+51x^3+28000x^4-700x^5+700x^6-490000x^8+40x^600-x^601+x^602-1400x^604-x^1200")));
        arg.add(Arguments.of(new Polynomial("1000x^10000+1020x^323-232x^232+1+x"), new Polynomial("1000x^10000-1020x^323+232x^232+1-x"), new Polynomial("+1-x^2+464x^233-2040x^324-53824x^464+473280x^555-1040400x^646+2000x^10000+1000000x^20000")));
        return arg;
    }

    private static List<Arguments> inputDiv() throws PolynomialException {
        List<Arguments> arg = new ArrayList<>();
        Polynomial q1 = new Polynomial("20x^17-20x^16+20x^15-40x^14+60x^13-80x^12+120x^11-180x^10+260x^9-380x^8+560x^7-820x^6+1200x^5-1760x^4+2580x^3-3780x^2+5540x-8120");
        Polynomial r1 = new Polynomial("11900x^2-5540x+8121");

        arg.add(Arguments.of(new Polynomial("x^3+x^2+1"), new Polynomial("20x^20+1"), q1, r1));

        Polynomial q2 = new Polynomial("20");
        Polynomial r2 = new Polynomial("-397x^2+21x-2419");

        arg.add(Arguments.of(new Polynomial("1-2x+3x+20x^20+23x^2"), new Polynomial("100-x+21x^2+21+x^20"), q2, r2));

        Polynomial q3 = new Polynomial("-1");
        Polynomial r3 = new Polynomial("1");

        arg.add(Arguments.of(new Polynomial("100+x^100-x^300+2220x^300"), new Polynomial("-100-x^100+x^300-2220x^300+1"), q3, r3));

        Polynomial q4 = new Polynomial("-x^300");
        Polynomial r4 = new Polynomial("-700x^304+x^302+50x^301+20x^300+700x^4+51x-20");

        arg.add(Arguments.of(new Polynomial("x^900+700x^4-20+50x+x"), new Polynomial("-x^600-700x^4+20+50x+x^2"), q4, r4));

        Polynomial q5 = new Polynomial("1");
        Polynomial r5 = new Polynomial("2040x^323-464x^232+2x");

        arg.add(Arguments.of(new Polynomial("1000x^10000+1020x^323-232x^232+1+x"), new Polynomial("1000x^10000-1020x^323+232x^232+1-x"), q5, r5));
        return arg;
    }
    private static List<Arguments> inputDeriv() throws PolynomialException {
        List<Arguments> arg = new ArrayList<>();
        arg.add(Arguments.of(new Polynomial("x^3+x^2+1"), new Polynomial("+2x+3x^2")));
        arg.add(Arguments.of(new Polynomial("1-2x+3x+20x^20+23x^2"), new Polynomial("+1+46x+400x^19")));
        arg.add(Arguments.of(new Polynomial("100+x^100-x^300+2220x^300"), new Polynomial("+100x^99+665700x^299")));
        arg.add(Arguments.of(new Polynomial("x^600+700x^4-20+50x+x"), new Polynomial("+51+2800x^3+600x^599")));
        arg.add(Arguments.of(new Polynomial("1000x^10000+1020x^323-232x^232+1+x"), new Polynomial("+1-53824x^231+329460x^322+10000000x^9999")));
        return arg;
    }
    private static List<Arguments> inputInteg() throws PolynomialException {
        List<Arguments> arg = new ArrayList<>();

        Polynomial p1 = new Polynomial();
        p1.addMonomial(new Monomial(1, 1));
        p1.addMonomial(new Monomial(0.333, 3));
        p1.addMonomial(new Monomial(0.25, 4));


        arg.add(Arguments.of(new Polynomial("x^3+x^2+1"), p1));

        Polynomial p2 = new Polynomial();
        p2.addMonomial(new Monomial(1, 1));
        p2.addMonomial(new Monomial(0.5, 2));
        p2.addMonomial(new Monomial(7.667, 3));
        p2.addMonomial(new Monomial(0.952, 21));

        arg.add(Arguments.of(new Polynomial("1-2x+3x+20x^20+23x^2"), p2));

        Polynomial p3 = new Polynomial();
        p3.addMonomial(new Monomial(100, 1));
        p3.addMonomial(new Monomial(0.01, 101));
        p3.addMonomial(new Monomial(7.372, 301));

        arg.add(Arguments.of(new Polynomial("100+x^100-x^300+2220x^300"), p3));

        Polynomial p4 = new Polynomial();
        p4.addMonomial(new Monomial(-20, 1));
        p4.addMonomial(new Monomial(25.5, 2));
        p4.addMonomial(new Monomial(140, 5));
        p4.addMonomial(new Monomial(0.002, 601));

        arg.add(Arguments.of(new Polynomial("x^600+700x^4-20+50x+x"), p4));

        Polynomial p5 = new Polynomial();
        p5.addMonomial(new Monomial(1, 1));
        p5.addMonomial(new Monomial(0.5, 2));
        p5.addMonomial(new Monomial(-0.996, 233));
        p5.addMonomial(new Monomial(3.148, 324));
        p5.addMonomial(new Monomial(0.1, 10001));

        arg.add(Arguments.of(new Polynomial("1000x^10000+1020x^323-232x^232+1+x"), p5));
        return arg;
    }
}