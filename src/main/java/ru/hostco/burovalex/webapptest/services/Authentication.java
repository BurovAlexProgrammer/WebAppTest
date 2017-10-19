package ru.hostco.burovalex.webapptest.services;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import java.io.Serializable;

public class Authentication implements AuthenticationService,Serializable {
    private static final long serialVersionUID = 1L;

    public UserCredential getUserCredential(){
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential)sess.getAttribute("userCredential");
        if(cre==null){
            cre = new UserCredential();//new a anonymous user and set to session
            sess.setAttribute("userCredential",cre);
        }
        return cre;
    }


    public boolean login(String nm, String pd) {
        // will be implemented in chapter 8
        return false;
    }


    public void logout() {
        // will be implemented in chapter 8
    }
}
