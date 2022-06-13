/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;



import com.codename1.io.CharArrayReader;
import com.mycompany.entites.Matche;

import com.mycompany.utils.Statics;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import com.codename1.io.JSONParser;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 *
 * @author DeLL
 */
public class ServiceMatch {
    //houni bch n3aytou lel crud eli zdnhom fi symfony w yraj3ou jsonREsult 
    //w nzido alihom code o5r ,nmchou tawa nchoufou test mt3 methodes json f symfony
    
    public static ServiceMatch instance = null ;
    public ArrayList<Matche> matche;
    public static boolean resultOK;
    
    //inisialisation connection request
    private ConnectionRequest req;
    
    
    public static ServiceMatch getInstance(){
        if(instance == null )
            instance = new ServiceMatch();
        return instance;
        
    }

        
    
    public ServiceMatch(){
        req = new ConnectionRequest();
       
        
}
    
    //ajout
    public boolean ajoutMatch(Matche match){
        
        String url =Statics.BASE_URL+"/addMatch?NomMatche="+match.getNomMatche()+"&NomArbitre="+match.getNomArbitre()+"&Stade="+match.getStade()+"&tour="+match.getTour()+"&Photo="+match.getPhoto();
  
            req.setUrl(url);
            req.addResponseListener((e) -> {
        
      String str = new String(req.getResponseData());
      System.out.println("data == "+str);
      
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    //affichage
    public ArrayList<Matche>affichageMatche() {
        ArrayList<Matche> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displayMatch";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            public void actionPerformed(NetworkEvent evt){
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                
                try {
                    Map<String,Object>mapMatche = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>>  listOfMaps  =   (List<Map<String,Object>>) mapMatche.get("root");
                    for(Map<String, Object> obj : listOfMaps){
                        Matche m = new Matche();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String NomMatche = obj.get("NomMatche").toString();
                        String NomArbitre = obj.get("NomArbitre").toString();
                        String Stade = obj.get("Stade").toString();
                        String tour = obj.get("tour").toString();
                        String Photo = obj.get("Photo").toString();
                        
                        m.setId((int)id);
                        m.setNomMatche(NomMatche);
                        m.setNomArbitre(NomArbitre);
                        m.setNomArbitre(Stade);
                       // m.setStade(obj.get("Stade").toString());
                        m.setTour(tour);
                        m.setPhoto(Photo);
                        
                        //insert data into ArrayList result
                        result.add(m);
                        
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return result;
        
    }
    //Delete
    public boolean deleteMatch(int id){
        String url = Statics.BASE_URL +"/deleteMatch?id="+id;
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    //update
    
    public boolean modifierMatch(Matche match){
        String url = Statics.BASE_URL +"/updateMatch?id"+match.getId()+"&NomMatche="+match.getNomMatche()+"&NomArbitre="+match.getNomArbitre()+"&Stade="+match.getStade()+"&tour="+match.getTour();
    req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOK = req.getResponseCode() == 200 ; // code response http 200 
               req.removeResponseCodeListener(this);
              
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
