/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

/**
 *
 * @author Maciek
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Samochod implements Serializable {
    private static final long serialVersionUID = -300035L;
    
    @Column(name = "id_samochodu", unique = true)
    @Id
    @GeneratedValue
    private int id_samochodu;
    @Column(name = "marka")
    private String marka;
    @Column(name = "model")
    private String model;
    @Column(name = "nr_rej")
    private String nr_rejestracyjny;
    @Column(name = "VIN")
    private String VIN;

    public int getId_samochodu() {
        return id_samochodu;
    }

    public void setId_samochodu(int id_samochodu) {
        this.id_samochodu = id_samochodu;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNr_rejestracyjny() {
        return nr_rejestracyjny;
    }

    public void setNr_rejestracyjny(String nr_rejestracyjny) {
        this.nr_rejestracyjny = nr_rejestracyjny;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Samochod(String marka, String model, String nr_rejestracyjny, String VIN) {
        this.id_samochodu = id_samochodu;
        this.marka = marka;
        this.model = model;
        this.nr_rejestracyjny = nr_rejestracyjny;
        this.VIN = VIN;
    }

    public Samochod() {
    }

    @Override
    public String toString() {
        return  marka + " " + model + " " + nr_rejestracyjny + " " + VIN;
    }
    
    
    
}
