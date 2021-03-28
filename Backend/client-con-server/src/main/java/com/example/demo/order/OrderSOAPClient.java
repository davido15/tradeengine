package com.example.demo.order;

import com.example.consumingorderservice.wsdl.ValidateOrder;
import com.example.consumingorderservice.wsdl.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.math.BigDecimal;


public class OrderSOAPClient extends WebServiceGatewaySupport {

    public ValidationResponse setOrder(String product, int quantity,  double price, String side) {

        ValidateOrder request = new ValidateOrder();
        request.setProduct(product);
        request.setQuantity(quantity);
        request.setPrice(BigDecimal.valueOf(price));
        request.setSide(side);

        ValidationResponse response = (ValidationResponse) getWebServiceTemplate().marshalSendAndReceive("http://ordervalidation.herokuapp.com/ws/Order", request,
                        new SoapActionCallback(
                                "http://turntabl/trading/ordervalidservice/validateOrder"));
        return response;
    }
}
