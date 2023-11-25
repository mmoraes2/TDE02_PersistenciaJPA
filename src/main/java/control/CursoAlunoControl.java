package control;

import dao.AlunoDAO;
import dao.CursoDAO;
import model.Aluno;
import model.Curso;

import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class CursoAlunoControl {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    private final AlunoDAO alunoDAO;
    private final CursoDAO cursoDAO;

    public CursoAlunoControl() {
        emf = Persistence.createEntityManagerFactory("PersistenciaJPA");
        em = emf.createEntityManager();

        alunoDAO = new AlunoDAO(em);
        cursoDAO = new CursoDAO(em);
    }

    public void cadastrarCurso(String nome, int cargaHoraria) {
        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setCargaHoraria(cargaHoraria);

        cursoDAO.salvarCurso(curso);
    }

    public void cadastrarAluno(String nome) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);

        alunoDAO.salvarAluno(aluno);
    }

    @Transactional
    public void associarAlunoAoCurso(Long matricula, Long codigoCurso) {
        Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
        Curso curso = cursoDAO.buscarPorCodigo(codigoCurso);

        try {
            if (aluno != null && curso != null) {
                Set<Curso> cursosAluno = aluno.getCursos();
                if (cursosAluno == null) {
                    cursosAluno = new HashSet<>();
                }
                cursosAluno.add(curso);
                aluno.setCursos(cursosAluno);
                alunoDAO.atualizarAluno(aluno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        CursoAlunoControl control = new CursoAlunoControl();

        // Cadastrar Cursos
        control.cadastrarCurso("Java Avançado", 40);
        control.cadastrarCurso("Banco de Dados", 30);

        // Cadastrar Alunos
        control.cadastrarAluno("João");
        control.cadastrarAluno("Carla");

        // Associar Alunos aos Cursos
        control.associarAlunoAoCurso(1L, 1L);
        control.associarAlunoAoCurso(2L, 1L);
        control.associarAlunoAoCurso(2L, 2L);
    }
}
