package com.iasi.iasi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_IASI_EMPRESA")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NOME_EMPRESA")
    private String nome;

    @Column(name = "SETOR_INDUSTRIAL_EMPRESA")
    private String setorIndustrial;

    @Column(name = "LOCALIZACAO_EMPRESA")
    private String localizacao;

    @Column(name = "TIPO_EMPRESA")
    private String tipo;

    @Column(name = "CONFORMIDADE_REGULAR")
    private String conformidadeRegular;

    public Empresa(){

    }
    public Empresa(long id, String nome, String setorIndustrial, String localizacao, String tipo, String conformidadeRegular){
        this.id = id;
        this.nome = nome;
        this.setorIndustrial = setorIndustrial;
        this.localizacao = localizacao;
        this.tipo = tipo;
        this.conformidadeRegular = conformidadeRegular;
    }

    public Empresa(String nome, String setorIndustrial, String localizacao, String tipo, String conformidadeRegular){
        this.nome = nome;
        this.setorIndustrial = setorIndustrial;
        this.localizacao = localizacao;
        this.tipo = tipo;
        this.conformidadeRegular = conformidadeRegular;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetorIndustrial() {
        return setorIndustrial;
    }

    public void setSetorIndustrial(String setorIndustrial) {
        this.setorIndustrial = setorIndustrial;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConformidadeRegular() {
        return conformidadeRegular;
    }

    public void setConformidadeRegular(String conformidadeRegular) {
        this.conformidadeRegular = conformidadeRegular;
    }

    @Override
    public String toString() {
        return "Empresa [id=" + id + ", nome=" + nome + ", setor industrial=" + setorIndustrial + ", localizacao=" + localizacao + ", tipo=" + tipo + ", conformidade regular=" + conformidadeRegular + "]";
    }
}
