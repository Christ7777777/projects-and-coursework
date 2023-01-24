/**
 * @Author Alexander Volkov
 */


package com.example.Experiment_2.atom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "atom")
public class AtomController {

    private final AtomService atomService;

    @Autowired
    public AtomController(AtomService atomService) {
        this.atomService = atomService;
    }

    @PostMapping(path = "/addAtom")
    public Atom addAtom(@RequestBody Atom atom) {
        return atomService.saveAtom(atom);
    }

    @PostMapping(path = "/addAtoms")
    public List<Atom> addAtoms(@RequestBody List<Atom> atoms) {
        return atomService.saveAtoms(atoms);
    }

    @GetMapping(path = "/getAtom/{symbol}")
    public Atom getAtomBySymbol(@PathVariable String symbol) {
        return atomService.getAtomBySymbol(symbol);
    }

    @GetMapping(path = "/getAtom/{number}")
    public Atom getAtomByNumber(@PathVariable int number) {
        return atomService.getAtomByNumber(number);
    }

    @GetMapping(path = "/getAtoms")
    public List<Atom> getAtoms() {
        return atomService.getAtoms();
    }

    @PutMapping(path = "/update")
    public Atom updateAtoms(@RequestBody Atom atom) {
        return atomService.updateAtom(atom);
    }

    @DeleteMapping(path = "/delete/{symbol}")
    public String deleteAtom(@PathVariable String symbol) {
        return atomService.deleteAtom(symbol);
    }
}
