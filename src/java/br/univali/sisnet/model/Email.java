package br.univali.sisnet.model;


public class Email {

    private int id;
    private String endereco;

    public Email(int id, String endereco) {
        this.id = id;
        this.endereco = endereco;
    }

    public Email(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}
