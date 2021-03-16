package modelpolycalc.operation;

import modelpolycalc.Monomial;
import modelpolycalc.Polynomial;
import exception.PolynomialException;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ModelOperationPolycalc {
    private Polynomial input1, input2;

    public ModelOperationPolycalc(Polynomial input1, Polynomial input2) {
        this.input1 = input1;
        this.input2 = input2;
    }

    public ModelOperationPolycalc() {
    }

    public ModelOperationPolycalc(Polynomial input) {
        this.input1 = input;
    }

    public Polynomial add() {
        return operationAddSub(1.0);
    }

    public Polynomial sub() {
        return operationAddSub(-1.0);
    }

    //ma ajuta la adunare si scadere!
    private Polynomial operationAddSub(Double op) {
        Polynomial resultPoly = new Polynomial();
        TreeMap<Integer, Double> buffer = new TreeMap<>();

        for (Monomial monom : input1.getPoly())
            buffer.put(monom.getDegree(), monom.getCoef()); // input1 si input2 sunt deja procesate!!

        for (Monomial monom : input2.getPoly())
            buffer.merge(monom.getDegree(), op * monom.getCoef(), Double::sum);

        for (var it : buffer.entrySet())
            if (it.getValue() != 0)
                resultPoly.addMonomial(new Monomial(it.getValue(), it.getKey()));
        return resultPoly;
    }

    public Polynomial mul() {
        TreeMap<Integer, Double> buffer = new TreeMap();

        for (var monom1 : input1.getPoly())
            for (var monom2 : input2.getPoly())
                buffer.merge(monom1.getDegree() + monom2.getDegree(), monom1.getCoef() * monom2.getCoef(), Double::sum);

        Polynomial resultPoly = new Polynomial();

        for (var it : buffer.entrySet())
            resultPoly.addMonomial(new Monomial(it.getValue(), it.getKey()));

        return resultPoly;
    }

    public List<Polynomial> div() throws PolynomialException {
        Polynomial p = input1;
        Polynomial q = input2;
        Polynomial quo = new Polynomial();

        if (q.getDegree() > p.getDegree()) {
            Polynomial aux;
            aux = p;
            p = q;
            q = aux;
        }

        if (q.toString().equals("0"))
            throw new PolynomialException("Impartire la zero a polinoamelor!");

        while (!p.toString().equals("0") && p.getDegree() >= q.getDegree()) {
            Monomial pMon = p.getPoly().get(p.getPoly().size() - 1);//monomul mare lui p
            Monomial qMon = q.getPoly().get(q.getPoly().size() - 1);//monomul mare lui q
            Monomial quoPart = new Monomial(pMon.getCoef() / qMon.getCoef(),
                    pMon.getDegree() - qMon.getDegree()); // calculam catul prin impartire!
            quo.addMonomial(quoPart);//adaugare la cat!
            Polynomial polQuoPart = new Polynomial();
            polQuoPart.addMonomial(quoPart);//cu asta o sa inmultim!
            Polynomial intermediarMul = new ModelOperationPolycalc(q, polQuoPart).mul();//inmultirea intermediara
            p = new ModelOperationPolycalc(p, intermediarMul).sub(); //scaderea!
        }

        List<Polynomial> result = new ArrayList<>();
        result.add(quo);
        result.add(p);
        return result;
    }

    public Polynomial integ() {
        Polynomial resultPoly = new Polynomial();

        for (var it : input1.getPoly())
            resultPoly.addMonomial(new Monomial(it.getCoef() / (it.getDegree() + 1), it.getDegree() + 1));
        return resultPoly;
    }

    public Polynomial deriv() {
        Polynomial resultPoly = new Polynomial();

        for (Monomial monom : input1.getPoly())
            resultPoly.addMonomial(new Monomial(monom.getCoef() * monom.getDegree(), monom.getDegree() - 1));
        return resultPoly;
    }
}
