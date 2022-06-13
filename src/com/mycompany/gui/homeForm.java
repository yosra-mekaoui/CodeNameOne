/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class homeForm extends Form{
Form current;
    public homeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAjouter = new Button("Add Match");
        Button btnAjouterb = new Button("Add Billet");
        //Button btnListTasks = new Button("List Tasks");
        
        btnAjouter.addActionListener(e-> new AjoutMatchForm(current).show());
        //btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
        //addAll(btnAddTask,btnListTasks);
        addAll(btnAjouter,btnAjouterb);
        
        
    }
    
    
}
