/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Matche;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.mycompany.services.ServiceMatch;

/**
 *
 * @author DeLL
 */
public class ModifierMatchForm extends BaseForm {
    Form current;
    public ModifierMatchForm(Resources res , Matche m) {
    
     super("Newsfeed",BoxLayout.y());
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolbar(tb);
        getTitleArea().setUIID("container") ;
        setTitle("Ajout Match");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        TextField nomMatche = new TextField(m.getNomMatche() , "nomMatche" , 20 , TextField.ANY);
        TextField nomArbitre = new TextField(m.getNomArbitre() , "nomArbitre" , 20 , TextField.ANY);
        TextField Stade = new TextField(m.getStade() , "stade" , 20 , TextField.ANY);
        TextField tour = new TextField(m.getTour() , "tour" , 20 , TextField.ANY);
        
         nomMatche.setUIID("NewsTopLine");
        
         nomArbitre.setUIID("NewsTopLine");
      
         Stade.setUIID("NewsTopLine");
        
         tour.setUIID("NewsTopLine");
         
         nomMatche.setSingleLineTextArea(true);
        
         nomArbitre.setSingleLineTextArea(true);
      
         Stade.setSingleLineTextArea(true);
        
         tour.setSingleLineTextArea(true);
         
         Button btnModifier = new Button("Modifier");
         
         btnModifier.setUIID("Button");
         
         //Event OnClick btnModifier
         btnModifier.addPointerPressedListener(l -> { 
             m.setNomMatche(nomMatche.getText());
             m.setNomArbitre(nomArbitre.getText());
             m.setStade(Stade.getText());
             m.setTour(tour.getText());
             
         
         
         //app fonction modifier match
         if(ServiceMatch.getInstance().modifierMatch(m)){
             new ListMatchForm(res).show();
         }
         });
         Button btnAnnuler = new Button ("Annuler");
         btnAnnuler.addActionListener(e -> {
             
             new ListMatchForm(res).show();
             
         });
         
         Label l2 = new Label ("");
         Label l3 = new Label ("");
         Label l4 = new Label ("");
         Label l5 = new Label ("");
         Label l1 = new Label ();
         
         Container content = BoxLayout.encloseY(
                 createLineSeparator(),
                 new FloatingHint(nomMatche),
                 createLineSeparator(),
                 new FloatingHint(nomArbitre),
                 createLineSeparator(),
                 new FloatingHint(Stade),
                 createLineSeparator(),
                 new FloatingHint(tour),
                 createLineSeparator(),
                 btnModifier,
                 btnAnnuler
         );
         
         add(content);
         show();
         
    }
    
}
