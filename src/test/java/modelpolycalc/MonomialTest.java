package modelpolycalc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonomialTest {

    @ParameterizedTest
    @MethodSource("inputCoef")
    @DisplayName("verificare coef")
    void getCoef(Monomial m, double expected) {
        assertEquals(expected, m.getCoef());
    }

    @ParameterizedTest
    @MethodSource("inputDegree")
    @DisplayName("verificare degree")
    void getDegree(Monomial m, int expected) {
        assertEquals(expected, m.getDegree());
    }

    private static List<Arguments>  inputCoef() {
        List<Arguments> arg = new ArrayList<>();
        arg.add(Arguments.of(new Monomial("2x^14"), 2));
        arg.add(Arguments.of(new Monomial("10x"), 10));
        arg.add(Arguments.of(new Monomial("-300x^30"), -300));
        arg.add(Arguments.of(new Monomial("1"), 1));
        arg.add(Arguments.of(new Monomial("-20x^2"), -20));
        arg.add(Arguments.of(new Monomial("-70x^100"), -70));
        return arg;
    }

    private static List<Arguments>  inputDegree() {
        List<Arguments> arg = new ArrayList<>();
        arg.add(Arguments.of(new Monomial("2x^14"), 14));
        arg.add(Arguments.of(new Monomial("10x"), 1));
        arg.add(Arguments.of(new Monomial("-300x^30"), 30));
        arg.add(Arguments.of(new Monomial("1"), 0));
        arg.add(Arguments.of(new Monomial("-20x^2"), 2));
        arg.add(Arguments.of(new Monomial("-70x^100"), 100));
        return arg;
    }
}