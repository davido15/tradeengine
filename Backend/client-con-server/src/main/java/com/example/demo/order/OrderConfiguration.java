package com.example.demo.order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class OrderConfiguration {

        @Bean
    public Jaxb2Marshaller marshaller(){
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
            marshaller.setContextPath("com.example.consumingorderservice.wsdl");
            return marshaller;
        }

        @Bean
    public OrderSOAPClient orderClient(Jaxb2Marshaller marshaller){
            OrderSOAPClient client = new OrderSOAPClient();
            client.setDefaultUri("http://ordervalidation.herokuapp.com/ws");
            client.setMarshaller(marshaller);
            client.setUnmarshaller(marshaller);
            return client;
        }
}
