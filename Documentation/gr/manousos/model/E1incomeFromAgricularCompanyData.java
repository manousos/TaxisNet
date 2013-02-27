package gr.manousos.model;
// Generated 27 Φεβ 2013 12:29:40 πμ by Hibernate Tools 3.4.0.CR1



/**
 * E1incomeFromAgricularCompanyData generated by hbm2java
 */
public class E1incomeFromAgricularCompanyData  implements java.io.Serializable {


     private Integer idE1incomeFromAgricularCompanyId;
     private E1taxableIncomes e1taxableIncomes;
     private String location;
     private Integer prodKind;
     private Float population;
     private Integer locationType;
     private Integer hasWater;
     private Float netIncome;

    public E1incomeFromAgricularCompanyData() {
    }

    public E1incomeFromAgricularCompanyData(E1taxableIncomes e1taxableIncomes, String location, Integer prodKind, Float population, Integer locationType, Integer hasWater, Float netIncome) {
       this.e1taxableIncomes = e1taxableIncomes;
       this.location = location;
       this.prodKind = prodKind;
       this.population = population;
       this.locationType = locationType;
       this.hasWater = hasWater;
       this.netIncome = netIncome;
    }
   
    public Integer getIdE1incomeFromAgricularCompanyId() {
        return this.idE1incomeFromAgricularCompanyId;
    }
    
    public void setIdE1incomeFromAgricularCompanyId(Integer idE1incomeFromAgricularCompanyId) {
        this.idE1incomeFromAgricularCompanyId = idE1incomeFromAgricularCompanyId;
    }
    public E1taxableIncomes getE1taxableIncomes() {
        return this.e1taxableIncomes;
    }
    
    public void setE1taxableIncomes(E1taxableIncomes e1taxableIncomes) {
        this.e1taxableIncomes = e1taxableIncomes;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    public Integer getProdKind() {
        return this.prodKind;
    }
    
    public void setProdKind(Integer prodKind) {
        this.prodKind = prodKind;
    }
    public Float getPopulation() {
        return this.population;
    }
    
    public void setPopulation(Float population) {
        this.population = population;
    }
    public Integer getLocationType() {
        return this.locationType;
    }
    
    public void setLocationType(Integer locationType) {
        this.locationType = locationType;
    }
    public Integer getHasWater() {
        return this.hasWater;
    }
    
    public void setHasWater(Integer hasWater) {
        this.hasWater = hasWater;
    }
    public Float getNetIncome() {
        return this.netIncome;
    }
    
    public void setNetIncome(Float netIncome) {
        this.netIncome = netIncome;
    }




}

