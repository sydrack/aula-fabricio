

package br.univali.sisnet.model;


public class Telefone {

    private int id;
    private String numero;

    public Telefone(int id, String numero) {
        this.id = id;
        this.numero = numero;
    }
    
    public Telefone(String numero){
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}
