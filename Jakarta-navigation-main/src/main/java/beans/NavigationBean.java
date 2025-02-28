/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;

/**
 *
 * @author Jenovic Mwambay
 */
@Named(value = "navigationController")
@RequestScoped
public class NavigationBean {
    public void voirApropos(){
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(
                            "pages/a_propos.xhtml");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void lieu(){
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("pages/lieu.xhtml");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void visiter(){
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("pages/visite.xhtml");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
        public void retour(){
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("/home.xhtml");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
        
    public void guide(){
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("pages/guide.xhtml");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
