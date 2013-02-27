package gr.manousos.model;
// Generated 27 Φεβ 2013 12:29:40 πμ by Hibernate Tools 3.4.0.CR1


import java.util.HashSet;
import java.util.Set;

/**
 * E1taxPayerBankAccount generated by hbm2java
 */
public class E1taxPayerBankAccount  implements java.io.Serializable {


     private Integer idE1taxPayerBankAccount;
     private String bic;
     private String iban;
     private Set<E1> e1s = new HashSet<E1>(0);

    public E1taxPayerBankAccount() {
    }

    public E1taxPayerBankAccount(String bic, String iban, Set<E1> e1s) {
       this.bic = bic;
       this.iban = iban;
       this.e1s = e1s;
    }
   
    public Integer getIdE1taxPayerBankAccount() {
        return this.idE1taxPayerBankAccount;
    }
    
    public void setIdE1taxPayerBankAccount(Integer idE1taxPayerBankAccount) {
        this.idE1taxPayerBankAccount = idE1taxPayerBankAccount;
    }
    public String getBic() {
        return this.bic;
    }
    
    public void setBic(String bic) {
        this.bic = bic;
    }
    public String getIban() {
        return this.iban;
    }
    
    public void setIban(String iban) {
        this.iban = iban;
    }
    public Set<E1> getE1s() {
        return this.e1s;
    }
    
    public void setE1s(Set<E1> e1s) {
        this.e1s = e1s;
    }




}

