package entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Visite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateVisite;
    private int tempsPasse;
    private String observations;
    private double depenses;

    public Visite() {}

    public Visite(Date dateVisite, int tempsPasse, String observations, double depenses) {
        this.dateVisite = dateVisite;
        this.tempsPasse = tempsPasse;
        this.observations = observations;
        this.depenses = depenses;
    }

    // Getters et Setters...
}
