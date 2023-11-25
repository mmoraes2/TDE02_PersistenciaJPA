package model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;

    private String nome;

    @ManyToMany
    @JoinTable(
            name = "aluno_curso",
            joinColumns = @JoinColumn(name = "matricula_aluno"),
            inverseJoinColumns = @JoinColumn(name = "codigo_curso"))
    private Set<Curso> cursos;

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
}
