/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import jakarta.faces.context.FacesContext;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.IOException;

/**
 *
 * @author dell
 */
@Named("NavigationBean")
@RequestScoped
public class NavigationBean {
    
     public void redirection(String url){
            try{ 
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(url);
            }catch (IOException e){
                
                e.printStackTrace();
            }
        }
    
    public void voirApropos(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("pages/a_propos.xhtml");
        
        }catch(IOException e){
            e.printStackTrace();
        
        }
    
    
    }
    public void voirVisiter(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
        
        }catch(IOException e){
            e.printStackTrace();
        
        }
        
    }
    public void voirAjouter(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("pages/lieu.xhtml");
        
        }catch(IOException e){
            e.printStackTrace();
        
        }

    }
    
    public void visiterLieu(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("guide.xhtml");
        
        }catch(IOException e){
            e.printStackTrace();
        
        }

    }
    
}
