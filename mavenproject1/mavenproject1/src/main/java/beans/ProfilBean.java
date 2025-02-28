package beans;

import business.SessionManager;
import business.UtilisateurEntrepriseBean;
import entities.Utilisateur;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import org.mindrot.jbcrypt.BCrypt;

@Named("profilBean")
@SessionScoped
public class ProfilBean implements Serializable {

    private String email;
    private String username;
    private String description;
    private String newPassword;

    @Inject
    private UtilisateurEntrepriseBean utilisateurEntrepriseBean;

    @Inject
    private SessionManager sessionManager;

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @PostConstruct
    public void init() {

        chargerProfil();
    }

    // Méthode pour charger les informations du profil
    public void chargerProfil() {
        String userEmail = sessionManager.getValueFromSession("user");
        if (userEmail != null) {
            Utilisateur utilisateur = utilisateurEntrepriseBean.trouverUtilisateurParEmail(userEmail);
            if (utilisateur != null) {
                this.email = utilisateur.getEmail();
                this.username = utilisateur.getUsername();
                //this.description = utilisateur.getDescription();
            }
        }
    }

    public String mettreAJourProfil() {
    String userEmail = sessionManager.getValueFromSession("user");
    if (userEmail != null) {
        Utilisateur utilisateur = utilisateurEntrepriseBean.trouverUtilisateurParEmail(userEmail);
        if (utilisateur != null) {
            boolean modificationEffectuee = false;

            // Vérifier si la description a changé
            if (description != null && !description.trim().isEmpty() 
                && !description.equals(utilisateur.getDescription())) {
                utilisateur.setDescription(description);
                modificationEffectuee = true;
            }

            // Vérifier si un nouveau mot de passe est fourni
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                utilisateur.setPassword(hashedPassword);
                modificationEffectuee = true;
            }

            // Si une modification a été faite, on met à jour
            if (modificationEffectuee) {
                utilisateurEntrepriseBean.mettreAJourUtilisateur(utilisateur);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modifications enregistrées avec succès.", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Aucune modification n'a été effectuée.", null));
            }

            return null; // Rester sur la même page
        }
    }
    
    // En cas d'erreur
    FacesContext.getCurrentInstance().addMessage(null, 
        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la mise à jour du profil.", null));
    return null; // Reste sur la même page
}


}
