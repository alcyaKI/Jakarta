package beans;

import business.SessionManager;
import business.UtilisateurEntrepriseBean;
import entities.Utilisateur;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

@Named("welcomeBean")
@SessionScoped // Utiliser SessionScoped pour gérer l'état de l'utilisateur connecté
public class welcomeBean implements Serializable {
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    private String message;

    @Inject
    private UtilisateurEntrepriseBean utilisateurEntrepriseBean;

    @Inject
    private SessionManager sessionManager;

    @Inject
    private ProfilBean profilBean;

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    // Méthode pour s'authentifier
    public String sAuthentifier() {
        FacesContext context = FacesContext.getCurrentInstance();

        // Authentifier l'utilisateur
        Utilisateur utilisateur = utilisateurEntrepriseBean.authentifier(email, password);

        if (utilisateur != null) {
            // Créer la session et stocker l'email de l'utilisateur
            sessionManager.createSession("user", email);

            // Charger les informations du profil dans profilBean
            profilBean.setEmail(utilisateur.getEmail());
            profilBean.setUsername(utilisateur.getUsername());

            // Rediriger vers la page d'accueil
            return "/home?faces-redirect=true";
        } else {
            // Afficher un message d'erreur
            message = "Email ou mot de passe incorrect.";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return null; // Rester sur la même page
        }
    }

    // Méthode pour se déconnecter
    public String deconnecter() {
        // Invalider la session
        sessionManager.invalidateSession();
        // Rediriger vers la page d'accueil
        return "/index?faces-redirect=true";
    }
}