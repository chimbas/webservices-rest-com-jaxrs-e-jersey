package br.com.alura.loja.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projeto {

	private Long id;
	private String nome;
	private Integer anoDeInicio;
	
	public Projeto(Long id, String nome, Integer anoDeInicio) {
		super();
		this.id = id;
		this.nome = nome;
		this.anoDeInicio = anoDeInicio;
	}
	
	public Projeto() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Integer getAnoDeInicio() {
		return anoDeInicio;
	}

	public String toXML() {
	    return new XStream().toXML(this);
	}

	public String toJson() {
        return new Gson().toJson(this);
    }

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAnoDeInicio(Integer anoDeInicio) {
		this.anoDeInicio = anoDeInicio;
	}
}
