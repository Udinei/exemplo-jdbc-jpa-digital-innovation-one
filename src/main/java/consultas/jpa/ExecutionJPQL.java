package consultas.jpa;

import model.AlunoJPA;
import model.EstadoJPA;
import consultas.jpa.metamodel.AlunoJPA_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExecutionJPQL {

    public static void main(String[] args) {
        // interface -  fabrica de conexao e acesso a BD
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        // interfaace - cria gerenciador do ciclo de vida das entidadas apartir da fabrica
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        populaBD(entityManager);

        // 2 - Vamos utilizar o metodo do EntityManager find(), SQO nativo, JPQL e JPA Criteria API para realizar
        //  uma consulta que retorna o mesmo valor equivalente aos seguintes SQL abaixo:
        //  SELECT * FROM alunoJPA where id= 1 - equivalente ao metodo find do entityManager
        //  SELECT * FROM alunoJPA where nome= "Daniel" - equivalente ao metodo find do entityManager

        // 2.1 O parametro de busca que sera utilzado nas proximas buscas
        String nome = "Daniel" ;

        //(entityManager);
        //consultaAlunoComSQLNativo(entityManager, nome);
        //consultaAlunoComJPQL(entityManager, nome);
        consultaAlunoComJPACriteriaAPI(entityManager, nome);


    }

    private static void consultaAlunoComJPACriteriaAPI(EntityManager entityManager, String nome) {
        // 2.5 - JPA Criteria API - JPA Metamodel
        // trazendo somente 1 resultado
        // Tabela a ser consultada
        CriteriaQuery<AlunoJPA> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(AlunoJPA.class);
        Root<AlunoJPA> alunoJPARoot = criteriaQuery.from(AlunoJPA.class); //

        // acessando o metamodel AlunoJPA_
        // AlunoJPA.nome é o campo a ser pesquisado
        CriteriaBuilder.In<String> inClause = entityManager.getCriteriaBuilder().in(alunoJPARoot.get(AlunoJPA_.NOME));
        System.out.println("......" + nome);
        inClause.value(nome); // nome - valor as ser pesquisado (Daniel no caso)

        criteriaQuery.select(alunoJPARoot).where(inClause); // seleciona os registro que que tenha o nome =
        AlunoJPA alunoAPTICriteria = entityManager.createQuery(criteriaQuery).getSingleResult();

        // ---------
        // trazendo uma lista no resultado
        // entidade que sera consultada no BD
        CriteriaQuery<AlunoJPA> criteriaQueryList = entityManager.getCriteriaBuilder().createQuery(AlunoJPA.class);

        Root<AlunoJPA> alunoRootList = criteriaQueryList.from(AlunoJPA.class);
        // retorna todos os registros da tabela
        List<AlunoJPA> alunoAPICriteriaList = entityManager.createQuery(criteriaQueryList).getResultList();

        // resultado das consultas acima
        System.out.println("Consulta alunoAPICriteria... " + alunoAPTICriteria);
        alunoAPICriteriaList.forEach(Aluno -> System.out.println("Consulta alunoAPICriteriaList.... " + Aluno));
    }

    private static void consultaAlunoComJPQL(EntityManager entityManager, String nome) {
        // 2.4 - CONSULTA COM JPQ
        // trazendo somente 1 resultado
        String jpql = "select a from AlunoJPA a where a.nome = :nome";
        AlunoJPA alunoJPQL = entityManager
                .createQuery(jpql, AlunoJPA.class)
                .setParameter("nome", nome)
                .getSingleResult();

        // trazendo uma lista como resultado
        String jpqlList = "select a from AlunoJPA a";
        List<AlunoJPA> alunoJPQLlist = entityManager
                .createQuery(jpqlList, AlunoJPA.class)
                .getResultList();

        // Trazendo uma lista de alunos por estado - JPQL - vantagem dos campos ja estar mapeado na entidades
        // consulta por objetos, por atributos da entidade
        String jpqlList1 = "select a from AlunoJPA a where a.estado = :estado";
        //String jpqlList1 = "select a from AlunoJPA a where a.estado.nome = :nomeEstado";
        //String jpqlList1 = "select a from AlunoJPA a where a.estado.id = :estadoId";
        List<AlunoJPA> alunoJPQLlist1 = entityManager
                .createQuery(jpqlList1, AlunoJPA.class)
                .setParameter("estado", entityManager.find(EstadoJPA.class, 1))
                //.setParameter("nomeEstado", "Rio de Janeiro")
                //.setParameter("estadoId", 1)
                .getResultList();

        // resultado das consultas acima
        System.out.println("Consulta alunoJPQL... " + alunoJPQL);
        alunoJPQLlist.forEach(AlunoJPQL -> System.out.println("Consulta alunoJPQLlist... " + AlunoJPQL));
        alunoJPQLlist1.forEach(AlunoJPQL -> System.out.println("Consulta aluno por estado - alunoJPQLlist1... " + AlunoJPQL));
    }


    private static void consultaAlunoComSQLNativo(EntityManager entityManager, String nome) {
        // USANDO SQL NATIVO -----------------------------
        // 2.3 usando SQL nativo para fazer a mesma consulta por nome do Aluno Daniel
        // Trazendo um unico elemento
        String sql = "SELECT * FROM AlunoJPA WHERE nome= :nome";
        AlunoJPA alunoSQL = (AlunoJPA) entityManager // usando SQL NATIVO no retorno tem que fazer um cast para o objeto aluno
                .createNativeQuery(sql, AlunoJPA.class)
                .setParameter("nome", nome)
                .getSingleResult();

        // Trazendo uma lista como resultado do aluno
        String sqlList = "SELECT * FROM AlunoJPA";
        List<AlunoJPA> alunoSQLlist = entityManager
                .createNativeQuery(sqlList, AlunoJPA.class)
                .getResultList();


        // Trazendo uma lista de aluno pelo estado de id = 1 (desvantagem de ter que conhecer os campo da tabela, se mudar a tabela tem que mudar o mapeamento)
        String sqlList1 = "SELECT * FROM AlunoJPA where estado_id = :idEstado";
        List<AlunoJPA> alunoSQLlist1 = entityManager
                .createNativeQuery(sqlList1, AlunoJPA.class)
                .setParameter("idEstado", 1)
                .getResultList();

        // Resultado das consultas acima
        System.out.println("Consulta alunoSQL... " + alunoSQL);
        alunoSQLlist.forEach(AlunoJPA -> System.out.println("Consulta alunoSQLList... " + AlunoJPA.getNome()));
        alunoSQLlist1.forEach(AlunoJPA -> System.out.println("Consulta de aluno por estado - alunoSQLList1... " + AlunoJPA));
    }

    private static void consultaAlunoComFindEntityManager(EntityManager entityManager) {
        // 2.2 - Utilizando o método find do entityManager
        // trazendo somente 1 elemento do BD
        AlunoJPA alunoEntityManager = entityManager.find(AlunoJPA.class, 1);

        // Trazendo uma lista como resultado
        // Não eh possivel com os metodos EntityManager!!! somente com SQL nativo, JPQL ou JPA Criteria API

        // resultado da consulta com find do entityManager
        System.out.println("Consulta com entityManager metodo find:.... " + alunoEntityManager);
    }

    // 1 - Dados instanciados que sera utilizados como exemplo das consultas
    private static void populaBD(EntityManager entityManager) {
        // inicia a transacao
        entityManager.getTransaction().begin();

        // cria e adiciona estado ao BD
        EstadoJPA estadoParaAdicionar = new EstadoJPA("Rio de Janeiro", "RJ");

        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(new EstadoJPA("São Paulo", "SP"));

        // cria e adiciona aluno no BD
        entityManager.persist(new AlunoJPA("Daniel", 29, estadoParaAdicionar));
        entityManager.persist(new AlunoJPA("Joao", 20, estadoParaAdicionar));
        entityManager.persist(new AlunoJPA("Pedro", 30, estadoParaAdicionar));

        entityManager.getTransaction().commit();
    }
}
