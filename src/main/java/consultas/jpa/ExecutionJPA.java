package consultas.jpa;


import model.AlunoJPA;
import model.EstadoJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ExecutionJPA {

    public static void main(String[] args) {
        // 1 - iniciar conexao com o BD e gerenciamento com entityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        EstadoJPA estadoParaAdicionar = new EstadoJPA("Minas Gerais", "MG");
        AlunoJPA alunoParaAdicionar1 = new AlunoJPA("Daniel", 30, estadoParaAdicionar);
        AlunoJPA alunoParaAdicionar2 = new AlunoJPA("Joao", 40, estadoParaAdicionar);
        AlunoJPA alunoParaAdicionar3 = new AlunoJPA("Maria", 10, estadoParaAdicionar);

        // inicia a transacao
        entityManager.getTransaction().begin();

        // inclui entidade no contexto para serem persistidas
        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(alunoParaAdicionar1);
        entityManager.persist(alunoParaAdicionar2);
        entityManager.persist(alunoParaAdicionar3);

        // persiste as entidade no BD
        entityManager.getTransaction().commit();

        // FIND - pesquisa é opcional usar transacao, nao é necessario pois nao faz nenhuma alteracao no bd
        EstadoJPA estadoAserBuscado = entityManager.find(EstadoJPA.class, 1);
        AlunoJPA alunoAserBuscado = entityManager.find(AlunoJPA.class, 3);

        System.out.println("Estado...." + estadoAserBuscado);
        System.out.println("Aluno...." + alunoAserBuscado);

        // UPDATE - altera o aluno buscado
        entityManager.getTransaction().begin();;

        alunoAserBuscado.setNome("Karen");
        alunoAserBuscado.setIdade(15);

        entityManager.getTransaction().commit();;
        System.out.println("Aluno alterado...." + alunoAserBuscado);


        // DELETE
        entityManager.getTransaction().begin();;

        entityManager.remove(alunoAserBuscado);

        entityManager.getTransaction().commit();;;

        entityManager.close();
        entityManagerFactory.close();

        //
    }
}
