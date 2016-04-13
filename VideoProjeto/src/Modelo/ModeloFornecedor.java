/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Eduardo
 */
public class ModeloFornecedor {
    private int id;
    private String nome;
    private String endereco;
    private String bairro;
    private String CNPJ;
    private String sigla_estado;
    private String nomeCidade;
    private String nomeBairro;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the CNPJ
     */
    public String getCNPJ() {
        return CNPJ;
    }

    /**
     * @param CNPJ the CNPJ to set
     */
    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
public String getSigla_estado() {
        return sigla_estado;
    }

    /**
     * @param sigla_estado
     */
    public void setSigla_estado(String sigla_estado) {
        this.sigla_estado =sigla_estado;
    }
    public String getNomeCidade() {
        return CNPJ;
    }

    /**
     * @param NomeCidade
     */
    public void setNomeCidade(String NomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    public String getNomeBairro() {
        return bairro;
    }

    /**
     * @param NomeCidade
     */
    public void setNomeBairro(String NomeBairro) {
        this.nomeBairro = nomeBairro;
    }
}