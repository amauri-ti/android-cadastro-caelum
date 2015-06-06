package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5044 on 16/05/15.
 */
public class AlunoDao extends SQLiteOpenHelper {

    private static final int VERSAO = 2;
    private static final String TABELA = "Alunos";
    private static final String DATABASE = "CadastroCaelum";

    public AlunoDao(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE " + TABELA +
                " (id INTEGER PRIMARY KEY, " +
                " nome TEXT NOT NULL, " +
                " telefone TEXT, " +
                " endereco TEXT, " +
                " site TEXT, " +
                " nota REAL);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == 1) {
            String sql = "DROP TABLE IF EXISTS " + TABELA;
            db.execSQL(sql);
            onCreate(db);
        } else if(newVersion == 2) {
            String sql = "ALTER TABLE " + TABELA + " ADD COLUMN caminhoFoto TEXT;";
            db.execSQL(sql);
        }
    }

    public void inserir(Aluno aluno) {
        ContentValues values=new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("caminhoFoto", aluno.getCaminhoFoto());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void alterar(Aluno aluno) {
        ContentValues values=new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("caminhoFoto", aluno.getCaminhoFoto());

        getWritableDatabase().update(TABELA, values, "id=?", new String[] {aluno.getId().toString()});
    }

    public void apagar(Aluno aluno) {
        SQLiteDatabase db=getWritableDatabase();
        String[] args={aluno.getId().toString()};
        db.delete(TABELA, "id=?", args);
    }

    public List<Aluno> getLista() {
        List<Aluno> alunos=new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + " ORDER BY NOME;", null);

        while(c.moveToNext()) {
            Aluno aluno=new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            alunos.add(aluno);
        }
        c.close();
        return alunos;
    }

    public boolean isAluno(String telefone) {
        String[] parametros = {telefone};
        Cursor rawQuery = getReadableDatabase()
                .rawQuery("SELECT telefone FROM " + TABELA
                + " WHERE telefone = ?", parametros);
        int total = rawQuery.getCount();
        rawQuery.close();
        return total > 0;
    }
}
