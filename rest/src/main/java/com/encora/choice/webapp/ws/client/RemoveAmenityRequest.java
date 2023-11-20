
package com.encora.choice.webapp.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="hotelId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="amenityId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "hotelId",
    "amenityId"
})
@XmlRootElement(name = "RemoveAmenityRequest")
public class RemoveAmenityRequest {

    protected long hotelId;
    protected long amenityId;

    /**
     * Gets the value of the hotelId property.
     * 
     */
    public long getHotelId() {
        return hotelId;
    }

    /**
     * Sets the value of the hotelId property.
     * 
     */
    public void setHotelId(long value) {
        this.hotelId = value;
    }

    /**
     * Gets the value of the amenityId property.
     * 
     */
    public long getAmenityId() {
        return amenityId;
    }

    /**
     * Sets the value of the amenityId property.
     * 
     */
    public void setAmenityId(long value) {
        this.amenityId = value;
    }

}
