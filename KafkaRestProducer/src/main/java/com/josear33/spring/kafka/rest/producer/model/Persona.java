package com.josear33.spring.kafka.rest.producer.model;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

public class Persona {

	private String nombre="";
	private String apellido1="";
	private String apellido2="";
	private String email="";
	private String telefono1="";
	private String telefono2="";
	private Integer edad;
	private String uid="";
	private Double fiebre;
	private Boolean covidPositivo=false;
	private Boolean esRiesgo=false;
	private Boolean tos=false;
	private Boolean sequedad=false;
	private Boolean mucosidad=false;
	private Date fechaControl;
	private String ciudad;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = DigestUtils.sha256Hex(this.getNombre()+this.getApellido1()+this.getApellido2()+this.getEmail()+this.getTelefono1());
	}
	public Double getFiebre() {
		return fiebre;
	}
	public void setFiebre(Double fiebre) {
		this.fiebre = fiebre;
	}
	public Boolean getCovidPositivo() {
		return covidPositivo;
	}
	public void setCovidPositivo(Boolean covidPositivo) {
		this.covidPositivo = covidPositivo;
	}
	public Boolean getEsRiesgo() {
		return esRiesgo;
	}
	public void setEsRiesgo(Boolean esRiesgo) {
		this.esRiesgo = esRiesgo;
	}
	public Boolean getTos() {
		return tos;
	}
	public void setTos(Boolean tos) {
		this.tos = tos;
	}
	public Boolean getSequedad() {
		return sequedad;
	}
	public void setSequedad(Boolean sequedad) {
		this.sequedad = sequedad;
	}
	public Boolean getMucosidad() {
		return mucosidad;
	}
	public void setMucosidad(Boolean mucosidad) {
		this.mucosidad = mucosidad;
	}
	public Date getFechaControl() {
		return fechaControl;
	}
	public void setFechaControl(Date fechaControl) {
		this.fechaControl = fechaControl;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}	
}
