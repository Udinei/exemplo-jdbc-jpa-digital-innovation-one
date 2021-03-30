package consultas.jdbc;

import dao.CursoDAO;
import model.Curso;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

public class QueriesExecutionCurso {

    private static CursoDAO cursoDAO = new CursoDAO();
    static  LocalTime hora = LocalTime.now();
    static Date currentDate = new Date();


    // obtem a hora atual do sistema em millisegundos
    private  static long milliSeconds = currentDate.getTime();

    // somente como teste a hora (tempo de duracao do curso sera a hora atual do sistema)
    private static Curso cursoHaSerInserido = new Curso("Geografia", new Time(milliSeconds));


    public static void main(String[] args) {
        // convertendo horas para millisegundos
        System.out.println(Long.valueOf(TimeUnit.HOURS.toMillis(LocalTime.now().getHour())));
        // removendo os segundos da hora
        System.out.println("hora " + hora.truncatedTo(SECONDS));

        //listaCursos();
        //insertCurso();
        update();
        //delete();
    }

    private static void update() {
        // recupera o curso a ser alterado
        Curso curso = new Curso(2 ,"Curso do update Geografia", new Time(milliSeconds));
        cursoDAO.update(curso);
    }

    private static void delete() {
        cursoDAO.delete(1);
    }

    private static void insertCurso() {
        cursoDAO.create(cursoHaSerInserido);
    }

    private static void listaCursos() {
        List<Curso> cursos = cursoDAO.list();
        cursos.stream().forEach(System.out::println);
    }
}
