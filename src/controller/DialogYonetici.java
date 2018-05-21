/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Dimension;
import javax.swing.JDialog;
import model.Ogretimuyesi;

/**
 *
 * @author Ahmet
 */
public class DialogYonetici {
    
    private Ogretimuyesi ogretimUyesi;
    private JDialog dialog;

    public DialogYonetici(Ogretimuyesi ogretimUyesi, JDialog dialog) {
        this.ogretimUyesi = ogretimUyesi;
        this.dialog = dialog;
    }
     public void dialogGoster(){
        
//        [434, 248]
        dialog.setMinimumSize(new Dimension(450, 300));
        dialog.setVisible(true);
    }
    
    public void dialogKapat(){
    dialog.dispose();
    }
    
    
//         private Integer ogretimuyesiiid;
//     private String sicilno;
//     private String adsoyad;
//     private String sehir;
//     private String telno;
    
     public void uyeBilgileriniAyarla(String adSoyad,String sicilno,
             String sehir,String telno){
        
         
        ogretimUyesi.setAdsoyad(adSoyad);
        ogretimUyesi.setSicilno(sicilno);
        ogretimUyesi.setSehir(sehir);
        ogretimUyesi.setTelno(telno);
    }
    
//    public int priKey(){
//        
//    }
    
}
