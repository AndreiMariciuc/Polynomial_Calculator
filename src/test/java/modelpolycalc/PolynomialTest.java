package modelpolycalc;

import exception.PolynomialException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @Test
    @DisplayName("Verifica conversii de polinoame!")
    void testToString() {
        Polynomial p1 = null;
        Polynomial p2 = null;
        Polynomial p3 = null;
        Polynomial p4 = null;
        Polynomial p5 = null;
        Polynomial p6 = null;
        try {
            p1 = new Polynomial("1+2x+x^2");
            p2 = new Polynomial("x+2x+x^2+100x");
            p3 = new Polynomial("x^300+200x+x^200");
            p4 = new Polynomial("-1-x-x-x^2+x+x+1+2x^2");
            p5 = new Polynomial("+1-2x-x^2+100x+300x^20+800x+100x^2+900x^4");
            p6 = new Polynomial("+1-2x-x^2-1+2x+x^2");
        } catch (PolynomialException e) {
            System.out.println(e.toString());
            return;
        }

        Polynomial finalP1 = p1;
        Polynomial finalP2 = p2;
        Polynomial finalP3 = p3;
        Polynomial finalP4 = p4;
        Polynomial finalP5 = p5;
        Polynomial finalP6 = p6;

        assertAll(
                () -> assertEquals("+1+2x+x^2", finalP1.toString()),
                () -> assertEquals("+103x+x^2", finalP2.toString()),
                () -> assertEquals("+200x+x^200+x^300", finalP3.toString()),
                () -> assertEquals("+x^2", finalP4.toString()),
                () -> assertEquals("+1+898x+99x^2+900x^4+300x^20", finalP5.toString()),
                () -> assertEquals("0", finalP6.toString())
        );
    }

    @Test
    @DisplayName("Verfica  gradul!")
    void getDegree() {
        Polynomial p1 = null;
        Polynomial p2 = null;
        Polynomial p3 = null;
        Polynomial p4 = null;
        Polynomial p5 = null;
        Polynomial p6 = null;
        try {
            p1 = new Polynomial("1+2x+x^2");
            p2 = new Polynomial("x+2x+x^2+100x");
            p3 = new Polynomial("x^300+200x+x^200");
            p4 = new Polynomial("-1-x-x-x^2+x+x+1+2x^2");
            p5 = new Polynomial("+1-2x-x^2+100x+300x^20+800x+100x^2+900x^4");
            p6 = new Polynomial("+1-2x-x^2-1+2x+x^2");
        } catch (PolynomialException e) {
            System.out.println(e.toString());
            return;
        }

        Polynomial finalP1 = p1;
        Polynomial finalP2 = p2;
        Polynomial finalP3 = p3;
        Polynomial finalP4 = p4;
        Polynomial finalP5 = p5;
        Polynomial finalP6 = p6;

        assertAll(
                () -> assertEquals(2, finalP1.getDegree()),
                () -> assertEquals(2, finalP2.getDegree()),
                () -> assertEquals(300, finalP3.getDegree()),
                () -> assertEquals(2, finalP4.getDegree()),
                () -> assertEquals(20, finalP5.getDegree()),
                () -> assertEquals(0, finalP6.getDegree())
        );
    }
}