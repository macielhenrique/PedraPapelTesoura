package dao;

import entidades.Jogada;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JogadaDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    public void salvar(Jogada jogada) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(jogada);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void editar(Jogada jogada) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(jogada);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void excluir(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Jogada jogada = em.find(Jogada.class, id);
            if (jogada != null) {
                em.remove(jogada);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Jogada> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Jogada> jogadas = em.createQuery("SELECT j FROM Jogada j", Jogada.class).getResultList();
            System.out.println("Total de jogadas encontradas no banco: " + jogadas.size()); // Log adicional
            return jogadas;
        } finally {
            em.close();
        }
    }

    public static void fechar() {
        emf.close();
    }
}
