package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class VisiteBean implements Serializable {

    private Long idUtilisateur;
    private Long idLieu;
    private Date dateVisite;
    private int tempsPasse;
    private String observations;
    private double depenses;
    private boolean afficherFormulaireVisite;

    public Date getDateVisite() {
        return dateVisite;
    }

    public double getDepenses() {
        return depenses;
    }

    public Long getIdLieu() {
        return idLieu;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getObservations() {
        return observations;
    }

    public int getTempsPasse() {
        return tempsPasse;
    }

    public void setTempsPasse(int tempsPasse) {
        this.tempsPasse = tempsPasse;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setAfficherFormulaireVisite(boolean afficherFormulaireVisite) {
        this.afficherFormulaireVisite = afficherFormulaireVisite;
    }

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public void setDepenses(double depenses) {
        this.depenses = depenses;
    }

    public void setIdLieu(Long idLieu) {
        this.idLieu = idLieu;
    }

    public void afficherFormulaireVisite() {
        afficherFormulaireVisite = true;
    }

    public void sauvegarderVisite() {
        if (idLieu != null && dateVisite != null && tempsPasse > 0) {
            // Logique pour sauvegarder la visite (ex. appel à un service ou DAO)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Visite enregistrée avec succès", null));
            afficherFormulaireVisite = false;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Veuillez remplir tous les champs requis", null));
        }
    }

    public boolean isAfficherFormulaireVisite() {
        return afficherFormulaireVisite;
    }
}
