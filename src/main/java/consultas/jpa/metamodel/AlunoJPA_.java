package consultas.jpa.metamodel;

import model.AlunoJPA;
import model.EstadoJPA;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AlunoJPA.class)
public abstract class AlunoJPA_ {
    public static volatile SingularAttribute<AlunoJPA, Integer> idade;
    public static volatile SingularAttribute<AlunoJPA, EstadoJPA> estado;
    public static volatile SingularAttribute<AlunoJPA, String> nome;
    public static volatile SingularAttribute<AlunoJPA, Integer> id;
    public static final String IDADE = "idade";
    public static final String ESTADO = "estado";
    public static final String NOME = "nome";
    public static final String ID = "id";

    public AlunoJPA_(){

    }

}
