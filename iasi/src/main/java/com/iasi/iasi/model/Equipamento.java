package com.iasi.iasi.model;

import com.iasi.iasi.model.Empresa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TB_IASI_EQUIPAMENTO")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EQUIPAMENTO")
    private long idEquipamento;

    @Column(name = "NOME_EQUIPAMENTO")
    private String nomeEquipamento;

    @Column(name = "TIPO_EQUIPAMENTO")
    private String tipoEquipamento;

    @Column(name = "LOCALIZACAO_EQUIPAMENTO")
    private String localizacaoEquipamento;

    @Column(name = "DATA_INSTALACAO_EQUIPAMENTO")
    private Date dataInstalacaoEquipamento;

    @Column(name = "ESTADO_EQUIPAMENTO")
    private String estadoEquipamento;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    public Equipamento() {}

    public long getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(long idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getLocalizacaoEquipamento() {
        return localizacaoEquipamento;
    }

    public void setLocalizacaoEquipamento(String localizacaoEquipamento) {
        this.localizacaoEquipamento = localizacaoEquipamento;
    }

    public Date getDataInstalacaoEquipamento() {
        return dataInstalacaoEquipamento;
    }

    public void setDataInstalacaoEquipamento(Date dataInstalacaoEquipamento) {
        this.dataInstalacaoEquipamento = dataInstalacaoEquipamento;
    }

    public String getEstadoEquipamento() {
        return estadoEquipamento;
    }

    public void setEstadoEquipamento(String estadoEquipamento) {
        this.estadoEquipamento = estadoEquipamento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
