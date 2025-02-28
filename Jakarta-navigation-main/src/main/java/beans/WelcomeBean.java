/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import Business.UtilisateurEntrepriseBean;
import entities.Utilisateur;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Orman
 */
@Named(value = "test")
@RequestScoped
public class WelcomeBean {
    
    private String nom;
    private String message;
    private String email;
    private String password;
      private String description;
    private String ancienMotDePasse;
    private String nouveauMotDePasse;
    
    @Inject
    private UtilisateurEntrepriseBean utilisateurEntrepriseBean;
     @Inject
     private SessionManager sessionManager;



    private Utilisateur utilisateur;

    @PostConstruct
    public void chargerUtilisateur() {
        String emailSession = sessionManager.getValueFromSession("user");
        if (emailSession != null) {
            utilisateur = utilisateurEntrepriseBean.trouverUtilisateurParEmail(emailSession);
            if (utilisateur != null) {
                this.nom = utilisateur.getUsername();
                this.email = utilisateur.getEmail();
                this.description = utilisateur.getDescription();
            }
        }
    }

    public void modifierProfil() {
        if (utilisateur != null) {
            utilisateur.setDescription(description);
            utilisateurEntrepriseBean.mettreAJourUtilisateur(utilisateur);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Profil mis à jour avec succès", null));
        }
    }

    public void modifierMotDePasse() {
        if (utilisateur != null && utilisateurEntrepriseBean.verifierMotDePasse(ancienMotDePasse, utilisateur.getPassword())) {
            String hashedPassword = BCrypt.hashpw(nouveauMotDePasse, BCrypt.gensalt());
            utilisateur.setPassword(hashedPassword);
            utilisateurEntrepriseBean.mettreAJourUtilisateur(utilisateur);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Mot de passe mis à jour avec succès", null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ancien mot de passe incorrect", null));
        }
    }

    public String deconnexion() {
        sessionManager.invalidateSession();
        return "index?faces-redirect=true";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public void afficherMessage(){
        if (nom != null && !nom.trim().isEmpty()) {
            message = "Selamat datang, " + nom + "!";
        }else {
            message = "";
        }
    }
    
    public String sAuthentifier(){
        Utilisateur utilisateur = utilisateurEntrepriseBean.authentifier(email, password);
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        if(utilisateur != null){
            sessionManager.createSession("user", email);
            return "home?faces-redirect-true";
        }else {
            this.message="Email ou mot de passe incorrect.";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
        return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getNouveauMotDePasse() {
        return nouveauMotDePasse;
    }

    public String getAncienMotDePasse() {
        return ancienMotDePasse;
    }

    public void setNouveauMotDePasse(String nouveauMotDePasse) {
        this.nouveauMotDePasse = nouveauMotDePasse;
    }

    public void setAncienMotDePasse(String ancienMotDePasse) {
        this.ancienMotDePasse = ancienMotDePasse;
    }
    
}