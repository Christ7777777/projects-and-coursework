/**
 * @Author Alexander Volkov
 */


package com.example.Experiment_2.atom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtomService {

    @Autowired
    private AtomRepository atomRepository;

    public Atom saveAtom(Atom atom) {
        return atomRepository.save(atom);
    }

    public List<Atom> saveAtoms(List<Atom> atoms) {
        return atomRepository.saveAll(atoms);
    }

    public List<Atom> getAtoms() {
        return atomRepository.findAll();
    }

    public Atom getAtomBySymbol(String symbol) {
        return atomRepository.findById(symbol).orElse(null);
    }

    public Atom getAtomByNumber(int number) {
        return atomRepository.findByAtomicNumber(number);
    }

    public String deleteAtom(String symbol) {
        atomRepository.deleteById(symbol);
        return "Atom " + symbol + " was deleted";
    }

    public Atom updateAtom(Atom atom) {
        Atom existingAtom = atomRepository.findById(atom.getSymbol()).orElse(null);
        if (existingAtom != null)
        {
            existingAtom.setSymbol(atom.getSymbol());
            existingAtom.setAtomicNumber(atom.getAtomicNumber());
            existingAtom.setAtomicMass(atom.getAtomicMass());
            return atomRepository.save(existingAtom);
        }
        return null;
    }
}
