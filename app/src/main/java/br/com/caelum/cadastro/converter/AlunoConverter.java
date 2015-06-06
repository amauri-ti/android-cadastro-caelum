package br.com.caelum.cadastro.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5044 on 30/05/15.
 */
public class AlunoConverter {

    public String toJson(List<Aluno> alunos) {
        JSONStringer jsonStringer=new JSONStringer();
        try {
            jsonStringer.object().key("list").array().object().key("aluno").array();
            for (Aluno aluno: alunos) {
                jsonStringer.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("telefone").value(aluno.getTelefone())
                        .key("site").value(aluno.getSite())
                        .key("nota").value(aluno.getNota())
                        .endObject();
            }
            jsonStringer.endArray().endObject().endArray().endObject().toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStringer.toString();
    }
}
