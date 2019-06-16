/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.tads.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author marco
 */
public class Delivery {
    public static int updateDelivery(Coisa coisa){
        //criação do cliente que realiza a requisição
        Client client = ClientBuilder.newClient()
                .register(RestObjectMapperProvider.class)
                .register(JacksonFeature.class);
        
        WebTarget webTarget = client.target("http://localhost:8080/lol-lavanderia/webresources/delivery");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        //invocação do método http
        Response response = invocationBuilder.post(Entity.json(coisa));

        //tratamento da rola da resposta do marcurelio
        return response.getStatus();
    }
}

