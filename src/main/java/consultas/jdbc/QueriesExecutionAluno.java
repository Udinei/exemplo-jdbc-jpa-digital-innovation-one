package consultas.jdbc;

import dao.AlunoDAO;
import model.Aluno;

import java.util.List;

public class QueriesExecutionAluno {
    private static AlunoDAO alunoDAO = new AlunoDAO();
    private static Aluno alunoParaInsercao = new Aluno("Matheus",43,"SP");

    public static void main(String[] args) {

        consultaAlunoList();
        consultaAlunoPorId(alunoDAO);
        insereAluno(alunoParaInsercao);
        deleteAluno();
        updateAluno();
    }

    //  4 - Atualizar - O aluno ja deve ter sido cadastrado no BD
    private static void updateAluno() {
        // recupera o aluno por id
        Aluno alunoParaAtualizar = alunoDAO.getById(4);

        // atualiza os dados do aluno
        alunoParaAtualizar.setNome("Joaquim da Silva");
        alunoParaAtualizar.setIdade(30);
        alunoParaAtualizar.setEstado("SP");

        // realiza a alteracao no BD
        alunoDAO.update(alunoParaAtualizar);

        // lista todos os alunos
        consultaAlunoList();
    }

    /// 3 - Delete
    private static void deleteAluno() {
        consultaAlunoList();

        alunoDAO.delete(5);

        consultaAlunoList();

    }

    // 2 - Inserção
    private static void insereAluno(Aluno aluno) {
      alunoDAO.create(aluno);
    }

    // 1 - Consulta retorna todos os alunos da Tabela
    private static void consultaAlunoList() {
         List<Aluno> alunos = alunoDAO.list();
         alunos.stream().forEach(System.out::println);

    }

    // 1.1 - Consulta com filtro
    private static void consultaAlunoPorId(AlunoDAO alunoDAO) {
        Aluno alunoParaConsulta = alunoDAO.getById(1);
        System.out.println(alunoParaConsulta);

    }

}
