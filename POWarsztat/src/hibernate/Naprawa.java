/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "naprawa")
public class Naprawa implements Serializable {

    private static final long serialVersionUID = -300025L;

    @Column(name = "id_naprawy", unique = true)
    @Id
    @GeneratedValue
    private int id_naprawy;

    @Column(name = "koszt")
    private float koszt;

    @Column(name = "usterka")
    private String usterka;
    
    @ManyToOne
    @JoinColumn(name="id_samochodu")
    private Samochod samochod;

    @ManyToOne
    @JoinColumn(name="id_klienta")
    private Klient klient;

    public int getId_naprawy() {
        return id_naprawy;
    }

    public void setNaprawaId(int naprawaId) {
        this.id_naprawy = naprawaId;
    }

    public float getKoszt() {
        return koszt;
    }

    public void setKoszt(int koszt) {
        this.koszt = koszt;
    }

    public String getUsterka() {
        return usterka;
    }

    public void setUsterka(String usterka) {
        this.usterka = usterka;
    }

    public Samochod getSamochod() {
        return samochod;
    }

    public void setSamochod(Samochod samochod) {
        this.samochod = samochod;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Naprawa( float koszt, String usterka, Samochod samochod, Klient klient) {
        this.id_naprawy = id_naprawy;
        this.koszt = koszt;
        this.usterka = usterka;
        this.samochod = samochod;
        this.klient = klient;
    }

    @Override
    public String toString() {
        return koszt + " " + usterka + " " + samochod + " " + klient;
    }
    
    

    

public Naprawa(){}    

    
}



    
        
    
