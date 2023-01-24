/**
 * @Author Alexander Volkov
 */


package com.example.Experiment_2.atom;

import javax.persistence.*;

@Entity
@Table(name = "atoms_table")
public class Atom {

    @Id
    private String symbol;

    @Column
    private Integer atomicNumber;

    @Column
    private Double atomicMass;

    public Atom() {
    }

    public Atom(String symbol, Integer atomicNumber, Double atomicMass) {
        this.symbol = symbol;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getAtomicNumber() {
        return atomicNumber;
    }

    public void setAtomicNumber(Integer atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    public Double getAtomicMass() {
        return atomicMass;
    }

    public void setAtomicMass(Double atomicMass) {
        this.atomicMass = atomicMass;
    }

    @Override
    public String toString() {
        return "Atom{" +
                "symbol='" + symbol + '\'' +
                ", atomicNumber=" + atomicNumber +
                ", atomicMass=" + atomicMass +
                '}';
    }
}
