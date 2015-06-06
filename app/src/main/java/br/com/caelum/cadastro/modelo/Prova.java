package br.com.caelum.cadastro.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by android5044 on 06/06/15.
 */
public class Prova implements Serializable {

    private String data;
    private String materia;
    private String descricao;
    private List<String> topicos=new ArrayList<>();

    public Prova(String data, String materia) {
        this.data = data;
        this.materia = materia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    @Override
    public String toString() {
        return materia;
    }
}
