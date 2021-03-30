package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EstadoJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sigla;

    // relacionamento Bidirecional Aluno <-> Estado
    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<model.AlunoJPA> AlunoJPA  = new ArrayList<>();

     public EstadoJPA(){

     }

    public EstadoJPA(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public EstadoJPA(int id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}
