package dao;

import model.Aluno;

import javax.persistence.EntityManager;

public class AlunoDAO {

    private final EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void salvarAluno(Aluno aluno) {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public Aluno buscarPorMatricula(Long matricula) {
        return em.find(Aluno.class, matricula);
    }

    public void atualizarAluno(Aluno aluno) {
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
