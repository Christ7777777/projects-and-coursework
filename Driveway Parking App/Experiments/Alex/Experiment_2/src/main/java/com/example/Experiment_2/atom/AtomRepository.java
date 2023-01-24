/**
 * @Author Alexander Volkov
 */


package com.example.Experiment_2.atom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtomRepository extends JpaRepository<Atom, String> {
    Atom findByAtomicNumber(int number);
}
