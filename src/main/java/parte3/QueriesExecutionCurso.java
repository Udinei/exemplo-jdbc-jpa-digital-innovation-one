package parte3;

import java.sql.Time;
import java.util.List;

public class QueriesExecutionCurso {




    public static void main(String[] args) {

        //listaCursos();
        //insertCurso();
        //delete();
        Curso curso = new Curso(3, "Historia", new Time(300000));
        CursoDAO cursoDAO = new CursoDAO();
        cursoDAO.update(curso);
    }

    private static void delete() {
        CursoDAO cursoDAO = new CursoDAO();
        cursoDAO.delete(1);
    }

    private void insertCurso() {
        CursoDAO cursoDAO = new CursoDAO();
        Curso curso = new Curso(6, "Matematica", new Time(200000));
        cursoDAO.create(curso);
    }

    private  void listaCursos() {
        List<Curso> cursos = new CursoDAO().list();
        cursos.stream().forEach(System.out::println);
    }
}
