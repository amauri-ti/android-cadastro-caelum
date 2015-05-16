package br.com.caelum.cadastro.modelo;

/**
 * Created by android5044 on 16/05/15.
 */
public class Aluno {
    private String nome;
    private String telefone;
    private String endereco;
    private String site;
    private Double nota;
    private Long id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {

        String nomeFormatado;

        if(nome.length() > 8) {
            nomeFormatado = nome.substring(0, 8) + "...";
        } else {
            nomeFormatado = nome;
        }

        return nomeFormatado + "("+site+")";
    }
}

