package uk.co.sangharsh.common.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="CUSTOMER")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer extends UuidEntity{
	
	@Column(name= "NAME")
    private String name;
    
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "ADDR_ID")
	private Address address;
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
     
}
