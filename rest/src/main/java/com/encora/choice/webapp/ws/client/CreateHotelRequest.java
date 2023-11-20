
package com.encora.choice.webapp.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Hotel" type="{http://www.encora.com/choice/core/ws/hotelCatalog}HotelDTO"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hotel"
})
@XmlRootElement(name = "CreateHotelRequest")
public class CreateHotelRequest {

    @XmlElement(name = "Hotel", required = true)
    protected HotelDTO hotel;

    /**
     * Gets the value of the hotel property.
     * 
     * @return
     *     possible object is
     *     {@link HotelDTO }
     *     
     */
    public HotelDTO getHotel() {
        return hotel;
    }

    /**
     * Sets the value of the hotel property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelDTO }
     *     
     */
    public void setHotel(HotelDTO value) {
        this.hotel = value;
    }

}
