/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.fasterxml.classmate.AnnotationInclusion;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Ogretimuyesi;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Ahmet
 */
public class OgretimUyesiYonetici {
    
     private JTable uyelerTablo;
    private final static String SORGU_KALIP = "from Ogretimuyesi ogrUye";
    private Session session;
    private Vector<String> sutunlar = new Vector<>();
    private Vector<Object> satir;
    private DefaultTableModel model;
    
    public OgretimUyesiYonetici(JTable uyelerTablo) {
        this.uyelerTablo = uyelerTablo;
        sutunlar.add("Öğretim Uyesi ID");
        sutunlar.add("Ad Soyad");        
        sutunlar.add("Sicil No");
        sutunlar.add("Sehir");
        sutunlar.add("Tel No");
        model=(DefaultTableModel)uyelerTablo.getModel();
        model.setColumnIdentifiers(sutunlar);
    }


    public void uyeGetir(String aranan, String filtre) {
        String sorguMetin = "";
        if (filtre.equalsIgnoreCase("adSoyad")) {
            sorguMetin = SORGU_KALIP + " where ogrUye.adsoyad like '%" + aranan + "%'";
        } else if (filtre.equalsIgnoreCase("sicilNo")) {
            sorguMetin = SORGU_KALIP + " where ogrUye.sicilno like '%" + aranan + "%'";
        }
        session.beginTransaction();
        List uyelerList = session.createQuery(sorguMetin).list();
        session.getTransaction().commit();
        uyeGoster(uyelerList);

    }
    
//    private Integer musteriid;
//     private String musteriad;
//     private String musterisoyad;
//     private String adres;
//     private String telno;
    
    public void uyeEkle(Ogretimuyesi ogretimuUyesi){               
                               
         session.beginTransaction();
//         ogretimuUyesi.setOgretimuyesiiid(4);
         session.save(ogretimuUyesi);
//         List musterilerList = 
         session.getTransaction().commit();
//         session.close();
   uyeListele();
        
    }
    
    
    public void uyeSil(int id){               
                               
         session.beginTransaction();

         session.delete(( Ogretimuyesi) session.load(Ogretimuyesi.class, id));

         session.getTransaction().commit();

        uyeListele();
    }
    

    public void uyeGuncelle(int id,Ogretimuyesi ogretimuyesi){               
                               
        session.beginTransaction();


        
        Ogretimuyesi guncellenecekUye = ( Ogretimuyesi) session.load(Ogretimuyesi.class, id);
        session.evict(guncellenecekUye);
        
        
        
        guncellenecekUye.setAdsoyad(ogretimuyesi.getAdsoyad());
        guncellenecekUye.setSehir(ogretimuyesi.getSehir());
        guncellenecekUye.setSicilno(ogretimuyesi.getSicilno());
        guncellenecekUye.setTelno(ogretimuyesi.getTelno());
         
         session.merge(guncellenecekUye);

         session.getTransaction().commit();

        uyeListele();
    }
    
    

    
    public void uyeListele(){
        
         String sorguMetin = "from Ogretimuyesi";
         session.beginTransaction();
         List uyelerList = session.createQuery(sorguMetin).list();
         session.getTransaction().commit();
         uyeGoster(uyelerList);
    }

    public void ac() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void kapat() {
        session.close();
    }

    private void uyeGoster(List<Ogretimuyesi> uyeList) {
        model.getDataVector().removeAllElements();
        for (Ogretimuyesi gelenUye : uyeList) {
            satir=new Vector();
            satir.add(gelenUye.getOgretimuyesiiid());
            satir.add(gelenUye.getAdsoyad());
            satir.add(gelenUye.getSicilno());
            satir.add(gelenUye.getSehir());
            satir.add(gelenUye.getTelno());
            model.addRow(satir);
        }
    }
    
}
