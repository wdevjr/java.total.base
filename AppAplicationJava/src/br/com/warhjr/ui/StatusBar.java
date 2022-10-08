package br.com.warhjr.ui;

import java.awt.Dimension;

import javax.swing.JLabel;

public class StatusBar extends JLabel {
    
    /** Cria uma nova instância de StatusBar */
    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 16));
        setMessage("Pronto");
    }
    
    public void setMessage(String messagem) {
        setText(" "+messagem);        
    }        
}