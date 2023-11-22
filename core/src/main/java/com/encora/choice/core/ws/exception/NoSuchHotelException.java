package com.encora.choice.core.ws.exception;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode=FaultCode.CUSTOM,customFaultCode = "{http://www.encora.com/choice/core/ws/hotelCatalog}NO_SUCH_HOTEL", faultStringOrReason = "NO_SUCH_HOTEL")
public class NoSuchHotelException extends RuntimeException{
}
