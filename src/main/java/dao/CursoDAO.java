package dao;

import conexao.ConnectionFactory;
import model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

       // create/insert - classe/BD/SQL-CRUD
    public void create(Curso curso){

        // 1 - Conectar ao BD
        try(Connection conn = ConnectionFactory.getConnection()) {

            // 2 - escrever a sentença SQL
            String sql = "INSERT INTO curso (nome, duracao_horas) VALUES ( ?, ?)";

            // 3 - passar a consulta sql para um prepareStatement usando a conexao
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 4 - passar os parametros do objeto a ser criado para o prepareStatement
            stmt.setString(1, curso.getNome());
            stmt.setTime(2, curso.getDuracaoHoras());

            // 5 - retorna a qtd de linhas afetadas no BD
            int rowAffected = stmt.executeUpdate();
            System.out.println("Insercao executada com sucesso: " + rowAffected + " linha foram inseridas!");

        } catch (SQLException throwables) {
            System.out.println("Falha ao inserir novo curso...");
            throwables.printStackTrace();
        }
    }

    public void delete(int id){

        // 1 - conexao
        try(Connection conn = ConnectionFactory.getConnection()){

            // 2 - sentenca SQL
            String sql = "DELETE FROM curso WHERE id = ?";

            // 3 - passar sql pra o prepareStatement
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 4 - passar os parametnos do SQL de outros para PrepareStatement
            stmt.setInt(1, id);

            // 5 - executar o PrepareStatement
            int rowAffected = stmt.executeUpdate();
            System.out.println("Objeto deletado com sucesso!" + rowAffected + " linha afetada no BD");

        } catch (SQLException throwables) {
            System.out.println("Falha ao remover o Objeto");
            throwables.printStackTrace();
        }
    }

   public void update(Curso curso){
        // 1 - conexao
        try(Connection conn = ConnectionFactory.getConnection()) {

            // 2 - sentença SQL
            String sql = "UPDATE curso SET nome = ? , duracao_horas = ? WHERE id = ?";

            // 3 - passar sql para o preparestatement
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, curso.getNome());
            stmt.setTime(2, curso.getDuracaoHoras());
            stmt.setInt(3, curso.getId());

            int rowAffected =  stmt.executeUpdate();
            System.out.println("Alteração executada com sucesso: " + rowAffected + " Linha afetada!");

        } catch (SQLException throwables) {
            System.out.println("Houve falha ao alterar o curso");
            throwables.printStackTrace();
        }
   }


    public List<Curso> list(){

        // 1 - criar uma lista onde ficaram os cursos retornados do BD
        List<Curso> cursos = new ArrayList<>();

        // 2 - obter uma conexao com o BD em um try/catch -
        // nao é necessario o close no finally por causa da impl. try-with-resources java 7
        try(Connection conn = ConnectionFactory.getConnection()) {
            // 3 - escrever consulta SQL
            String sql = "SELECT * FROM curso";

            // 4 - passar o parametro de consulta SQL para um prepareStatement
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 5 - executa a consulta e armazenar o retorno no ResultSet
            ResultSet rs = stmt.executeQuery();

            // 6 - percorrer a lista de elementos retornados da consulata no ResultSet
            while (rs.next()){

                // 7 - cria um novo objeto cursos
                // 8 - adicionar o novo objeto criado na lista de cursos a ser retornada
                cursos.add(new Curso(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getTime("duracao_horas")));

            }

        } catch (SQLException throwables) {
            System.out.println("Listagem de Cursos FALHOU!");
            throwables.printStackTrace();
        }

        // 9 - retornar objeto consultado
        return cursos;
    }

    // busca por filtro - id
    public Curso getById(int id){
        // 1 - cria objeto de retorno
        Curso curso = new Curso();

        // 2 - obter conexao
        try(Connection conn = ConnectionFactory.getConnection()){

            // 3 - criar sentença SQL
            String sql = "SELECT * FROM curso WHERE id = ? ";

            // 4 - passar sentenca sql para um prepareStatement
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 5 - passar parametros para o prepareStatement
            stmt.setInt(1, id);

            // 6 - executar prepareStatemente ao resultSet
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
            }

            System.out.println("Busca de curso realizada com sucesso!");

        } catch (SQLException throwables) {
            System.out.println("Busca de curso falhou");
            throwables.printStackTrace();
        }

        return curso;
    }

}
