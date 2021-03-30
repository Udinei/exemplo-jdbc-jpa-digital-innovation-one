package model;

import javax.persistence.*;

@Entity
public class AlunoJPA {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column
        private String nome;

        @Column
        private int idade;

        @ManyToOne(fetch = FetchType.LAZY) // somente quanto um getEstadoJPA for executado que sera carregado o estado
        private EstadoJPA estado;

        public AlunoJPA(int id, String nome, int idade, EstadoJPA estado) {
            this.id = id;
            this.nome = nome;
            this.idade = idade;
            this.estado = estado;
        }

        public AlunoJPA(String nome, int idade, EstadoJPA estado) {
            this.nome = nome;
            this.idade = idade;
            this.estado = estado;
        }

        public AlunoJPA() { }

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public EstadoJPA getEstado() {
        return estado;
    }

    public void setEstado(EstadoJPA estado) {
        this.estado = estado;
    }

    @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("AlunoJPA{");
            sb.append("id=").append(id);
            sb.append(", nome='").append(nome).append('\'');
            sb.append(", idade=").append(idade);
            sb.append(", estado='").append(estado).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }


