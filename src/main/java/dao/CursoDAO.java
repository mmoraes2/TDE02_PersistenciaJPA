package dao;

import model.Curso;

import javax.persistence.EntityManager;

public class CursoDAO {

    private final EntityManager em;

    public CursoDAO(EntityManager em) {
        this.em = em;
    }

    public void salvarCurso(Curso curso) {
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
    }

    public Curso buscarPorCodigo(Long codigo) {
        return em.find(Curso.class, codigo);
    }

    public void atualizarCurso(Curso curso) {
        em.getTransaction().begin();
        em.merge(curso);
        em.getTransaction().commit();
    }
}
