package com.josear33.spring.kafka.rest.producer.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josear33.spring.kafka.rest.producer.model.Persona;
import com.josear33.spring.kafka.rest.producer.service.Producer;

import me.xdrop.jrand.JRand;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaRestController {

    private final Producer producer;

    @Autowired
    KafkaRestController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
    }
    
    @PostMapping(value = "/followup")
    public void sendDiagFollowupToKafkaTopic(@RequestBody Persona persona) {
        this.producer.sendDiagFollowup(randomizeData(persona));
        
    }
    
    private Persona randomizeData(Persona persona) {
    	persona.setFechaControl(Calendar.getInstance().getTime());
    	persona.setNombre(JRand.firstname().gen());
    	persona.setApellido1(JRand.name().gen());
    	persona.setApellido2(JRand.name().gen());
    	persona.setEdad(JRand.age().gen().doubleValue());
    	persona.setCiudad(JRand.city().country("Spain").genString());
    	persona.setCovidPositivo(JRand.bool().gen());
    	persona.setFiebre(JRand.dbl().min(36D).max(43D).gen());
    	persona.setEsRiesgo(JRand.bool().gen());
    	persona.setMucosidad(JRand.bool().gen());
    	persona.setEmail(JRand.string().gen());
    	persona.setTos(JRand.bool().gen());
    	persona.setTelefono1(JRand.phone().mobile().gen());
    	return persona;
    }
}