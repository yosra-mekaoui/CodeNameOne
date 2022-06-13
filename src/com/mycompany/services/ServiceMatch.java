/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Match;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP USER
 */
public class ServiceMatch {
    public static ServiceMatch instance = null ;
    public ArrayList<Match> matche;
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
    public ArrayList<Match>affichageMatche() {
        ArrayList<Match> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/AfficherMatchsMobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            public void actionPerformed(NetworkEvent evt){
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                
                try {
                    Map<String,Object>mapMatche = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>>  listOfMaps  =   (List<Map<String,Object>>) mapMatche.get("root");
                    for(Map<String, Object> obj : listOfMaps){
                        Match m = new Match();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String NomMatche = obj.get("nom_match").toString();
                        String NomArbitre = obj.get("nom_arbitre").toString();
                        String Stade = obj.get("Stade").toString();
                        String tour = obj.get("tour").toString();
                        String Photo = obj.get("image1").toString();
                        
                        m.setId((int)id);
                        m.setNom_match(NomMatche);
                        m.setNom_arbitre(NomArbitre);
                  //      m.setStade(Stade);
                       // m.setStade(obj.get("Stade").toString());
                        m.setTour(tour);
                        m.setImage1(Photo);
                        
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
}
