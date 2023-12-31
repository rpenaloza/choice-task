package com.encora.choice.core.ws.exception;

import lombok.experimental.StandardException;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;
@StandardException
@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://www.encora.com/choice/core/ws/hotelCatalog}NO_SUCH_AMENITY", faultStringOrReason = "NO_SUCH_AMENITY")
public class NoSuchAmenityException extends RuntimeException {
}
