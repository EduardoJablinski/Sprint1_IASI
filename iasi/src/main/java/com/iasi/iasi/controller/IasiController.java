package com.iasi.iasi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iasi.iasi.model.Empresa;
import com.iasi.iasi.repository.EmpresaRepository;

import com.iasi.iasi.model.Equipamento;
import com.iasi.iasi.repository.EquipamentoRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class IasiController {
    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    EquipamentoRepository equipamentoRepository;

    @GetMapping("/empresas")
    public ResponseEntity<List<Empresa>> getAllEmpresas(@RequestParam(required = false) String nome) {
        try {
            List<Empresa> empresas = new ArrayList<Empresa>();

            if (nome == null)
                empresaRepository.findAll().forEach(empresas::add);
            else
                empresaRepository.findByNameContaining(nome).forEach(empresas::add);

            if (empresas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(empresas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") long id) {
        Empresa empresa = empresaRepository.findById(id);

        if (empresa != null) {
            return new ResponseEntity<>(empresa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/empresas")
    public ResponseEntity<String> createEmpresa(@RequestBody Empresa empresa) {
        try {
            empresaRepository.save(new Empresa(empresa.getNome(), empresa.getSetorIndustrial(), empresa.getLocalizacao(), empresa.getTipo(), empresa.getConformidadeRegular()));
            return new ResponseEntity<>("Empresa foi criado com sucesso.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/empresas/{id}")
    public ResponseEntity<String> updateEmpresa(@PathVariable("id") long id, @RequestBody Empresa empresa) {
        Empresa _empresa = empresaRepository.findById(id);

        if (_empresa != null) {
            _empresa.setId(id);
            _empresa.setNome(empresa.getNome());
            _empresa.setSetorIndustrial(empresa.getSetorIndustrial());
            _empresa.setLocalizacao(empresa.getLocalizacao());
            _empresa.setTipo(empresa.getTipo());
            _empresa.setConformidadeRegular(empresa.getConformidadeRegular());

            empresaRepository.update(_empresa);
            return new ResponseEntity<>("Empresa foi atualizado com sucesso.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Não foi encontrada Empresa com id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/empresas/{id}")
    public ResponseEntity<String> deleteEmpresa(@PathVariable("id") long id) {
        try {
            int result = empresaRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Não foi encontrada Empresa com id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Empresa foi deletada com sucesso.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Não é possível deletar Empresa.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/empresas")
    public ResponseEntity<String> deleteAllEmpresas() {
        try {
            int numRows = empresaRepository.deleteAll();
            return new ResponseEntity<>("Deletadas " + numRows + " Empresa(s) com sucesso.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Não é possível deletar Empresa.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/empresas/{id}/equipamentos")
    public ResponseEntity<List<Equipamento>> getEquipamentosByEmpresaId(@PathVariable("id") long id) {
        List<Equipamento> equipamentos = equipamentoRepository.findByEmpresaId(id);
        if (equipamentos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(equipamentos, HttpStatus.OK);
    }

    @DeleteMapping("/empresas/{idEmpresa}/equipamentos/{idEquipamento}")
    public ResponseEntity<String> removeEquipamentoFromEmpresa(@PathVariable("idEmpresa") long idEmpresa, @PathVariable("idEquipamento") long idEquipamento) {
        // Verifique se a empresa existe
        Empresa empresa = empresaRepository.findById(idEmpresa);
        if (empresa == null) {
            return new ResponseEntity<>("Empresa não encontrada com id=" + idEmpresa, HttpStatus.NOT_FOUND);
        }
        // Verifique se o equipamento está associado à empresa
        Equipamento equipamento = equipamentoRepository.findById(idEquipamento);
        if (equipamento == null || equipamento.getEmpresa().getId() != idEmpresa) {
            return new ResponseEntity<>("Equipamento não encontrado ou não associado à empresa com id=" + idEmpresa, HttpStatus.NOT_FOUND);
        }
        // Remova o equipamento
        equipamentoRepository.deleteById(idEquipamento);
        return new ResponseEntity<>("Equipamento removido com sucesso da empresa com id=" + idEmpresa, HttpStatus.OK);
    }

    @GetMapping("/equipamentos")
    public ResponseEntity<List<Equipamento>> getAllEquipamentos(@RequestParam(required = false) String nomeEquipamento) {
        try {
            List<Equipamento> equipamentos = new ArrayList<Equipamento>();

            if (nomeEquipamento == null)
                equipamentoRepository.findAll().forEach(equipamentos::add);
            else
                equipamentoRepository.findByNameContaining(nomeEquipamento).forEach(equipamentos::add);

            if (equipamentos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(equipamentos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
