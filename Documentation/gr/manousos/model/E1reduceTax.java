package gr.manousos.model;
// Generated Apr 16, 2013 12:57:37 AM by Hibernate Tools 3.4.0.CR1


import java.util.HashSet;
import java.util.Set;

/**
 * E1reduceTax generated by hbm2java
 */
public class E1reduceTax  implements java.io.Serializable {


     private Integer idE1reduceTax;
     private Integer 001;
     private Integer 002;
     private Integer 003;
     private Integer 004;
     private Integer 005;
     private Integer 006;
     private Set<E1> e1s = new HashSet<E1>(0);

    public E1reduceTax() {
    }

    public E1reduceTax(Integer 001, Integer 002, Integer 003, Integer 004, Integer 005, Integer 006, Set<E1> e1s) {
       this.001 = 001;
       this.002 = 002;
       this.003 = 003;
       this.004 = 004;
       this.005 = 005;
       this.006 = 006;
       this.e1s = e1s;
    }
   
    public Integer getIdE1reduceTax() {
        return this.idE1reduceTax;
    }
    
    public void setIdE1reduceTax(Integer idE1reduceTax) {
        this.idE1reduceTax = idE1reduceTax;
    }
    public Integer get001() {
        return this.001;
    }
    
    public void set001(Integer 001) {
        this.001 = 001;
    }
    public Integer get002() {
        return this.002;
    }
    
    public void set002(Integer 002) {
        this.002 = 002;
    }
    public Integer get003() {
        return this.003;
    }
    
    public void set003(Integer 003) {
        this.003 = 003;
    }
    public Integer get004() {
        return this.004;
    }
    
    public void set004(Integer 004) {
        this.004 = 004;
    }
    public Integer get005() {
        return this.005;
    }
    
    public void set005(Integer 005) {
        this.005 = 005;
    }
    public Integer get006() {
        return this.006;
    }
    
    public void set006(Integer 006) {
        this.006 = 006;
    }
    public Set<E1> getE1s() {
        return this.e1s;
    }
    
    public void setE1s(Set<E1> e1s) {
        this.e1s = e1s;
    }




}


