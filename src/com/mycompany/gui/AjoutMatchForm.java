/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.entites.Matche;
import com.mycompany.services.ServiceMatch;

/**
 *
 * @author DeLL
 */
public class AjoutMatchForm extends BaseForm { 
     Form current;
    
       public AjoutMatchForm(Resources res) {
          super("Newsfeed",BoxLayout.y());
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolbar(tb);
        getTitleArea().setUIID("container") ;
        setTitle("Ajout Match");
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand(e -> {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("logoo.png"),"","",res);
        
        
        
        //**************************Debut Design************************
         
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

            ButtonGroup barGroup = new ButtonGroup();
        RadioButton produits = RadioButton.createToggle("Match", barGroup);
        produits.setUIID("SelectBar");
        RadioButton detailProduit = RadioButton.createToggle("Ajouter Match", barGroup);
        detailProduit.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        

        produits.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();

            final Dialog ipDlg = ip.showInifiniteBlocking();
        
            new ListMatchForm(res).show();
                   
            refreshTheme();
        });

        detailProduit.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();

            final Dialog ipDlg = ip.showInifiniteBlocking();
        
            new ListMatchForm(res).show();
                   
            refreshTheme();
        });


        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, produits, detailProduit),
                FlowLayout.encloseBottom(arrow)
        ));

        detailProduit.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(detailProduit, arrow);
        });
        bindButtonSelection(produits, arrow);
        bindButtonSelection(detailProduit, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

       
        
        //*****************************FinDesign*********************************
                
                
        TextField NomMatche = new TextField("","NomMatche");
        NomMatche.setUIID("textFieldBack");
        addStringValue("NomMatche",NomMatche);
        
        
        TextField NomArbitre = new TextField("","NomArbitre");
         NomArbitre.setUIID("textFieldBack");
        addStringValue("NomArbitre",NomArbitre);
                
        TextField Stade = new TextField("","Stade");
         Stade.setUIID("textFieldBack");
        addStringValue("Stade",Stade);
                
        TextField Tour = new TextField("","tour");
         Tour.setUIID("textFieldBack");
        addStringValue("tour",Tour);
        
        TextField Photo = new TextField("","Photo");
         Tour.setUIID("textFieldBack");
        addStringValue("Photo",Photo);
        
        TextField Description = new TextField("","Description");
         Tour.setUIID("textFieldBack");
        addStringValue("Description",Description);
                
        Button btnAjouter = new Button("Ajouter");
        addStringValue("",btnAjouter);
       
        //onClick button event
        
        btnAjouter.addActionListener((e) ->  {
                try {
                    if (NomMatche.getText().equals("") || NomArbitre.getText().equals("") || Stade.getText().equals("") || Tour.getText().equals("")|| Photo.getText().equals("")|| Description.getText().equals("")){
            Dialog.show("Veuillez vérifier les données","","Annuler","OK");
                    }
                    else {
                        InfiniteProgress ip = new InfiniteProgress();;
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        
                        Matche m = new Matche(String.valueOf(NomMatche.getText()).toString(),
                                              String.valueOf(NomArbitre.getText()).toString(),
                                              String.valueOf(Stade.getText()).toString(),
                                              String.valueOf(Tour.getText()).toString(),
                                              String.valueOf(Photo.getText()).toString(),
                                              String.valueOf(Description.getText()).toString()
                                );
                        
                        System.out.println("data Matche == "+m);
                        
                        //app methode ajouterMatch mt3 service Match bch nzidou données te3na fi base
                        ServiceMatch.getInstance().ajoutMatch(m);
                        
                        
                        iDialog.dispose(); //nahio loadig ma 3mlt l'ajout   
                        
                        //ListMatchForm
                        new ListMatchForm(res).show();
                        
                        refreshTheme();
                    }
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
           
        });
        
       }

    AjoutMatchForm(Form current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addStringValue(String s, Component v) {
        
       add(BorderLayout.west(new Label(s,"PaddedLabel"))
               .add(BorderLayout.CENTER,v));
       add(createLineSeparator(0xeeeeee));
    }

    
    //*************************************************************************************

    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() <size) {
            image = image.scaledHeight(size);
        }
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2){
        
        image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
    }
    
 ScaleImageLabel imageScale = new ScaleImageLabel(image);
 imageScale.setUIID("Container");
 imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
 
 Label overLay = new Label("","ImageOverlay");
 
 Container page1 =
         LayeredLayout.encloseIn(
         imageScale,
                 overLay,
                 BorderLayout.south(
                 BoxLayout.encloseY( 
                 new SpanLabel(text, "LargeWhiteText"),
                         FlowLayout.encloseIn(),
                         spacer
                    )
                 )
         );
 
 swipe.addTab("",res.getImage("logoo.png"), page1);
 
 
    }     
    
    public void bindButtonSelection(Button btn , Label l ){
        
        btn.addActionListener(e -> {
        if(btn.isSelected()){
            updateArrowPosition(btn,l);
        }
        
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
       l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2 -l.getWidth() / 2);
       l.getParent().repaint();
    }
                
}