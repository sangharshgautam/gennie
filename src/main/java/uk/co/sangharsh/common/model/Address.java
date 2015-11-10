package uk.co.sangharsh.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS")
public class Address extends UuidEntity{
	
	@Column(name="CITY")
	private String city;
    
	@Column(name="COUNTRY")
	private String country;
     
    
    public String getAddress() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
     
}
