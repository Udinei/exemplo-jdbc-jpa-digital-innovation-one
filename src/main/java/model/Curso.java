package model;


import java.sql.Time;
import java.time.LocalTime;

public class Curso {

    private Integer id;
    private String nome;
    private Time duracaoHoras;

    public Curso() {

    }

    public Curso(Integer id, String nome, Time duracaoHoras) {
        this.id = id;
        this.nome = nome;
        this.duracaoHoras = duracaoHoras;
    }

    public Curso(String nome, Time duracaoHoras) {
        this.nome = nome;
        this.duracaoHoras = duracaoHoras;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Time getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(Time duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }


    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", duracao_horas=" + duracaoHoras +
                '}';
    }
}
