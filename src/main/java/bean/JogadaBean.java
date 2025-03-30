package bean;

import dao.JogadaDAO;
import entidades.Jogada;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class JogadaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Jogada jogada;
    private List<Jogada> jogadas;
    private JogadaDAO jogadaDAO;

    @PostConstruct
    public void init() {
        jogadaDAO = new JogadaDAO(); // Cria a instância do DAO
        jogada = new Jogada();
        carregarJogadas(); // Carrega as jogadas na inicialização
    }

    public void salvar() {
        try {
            // Validação para verificar se as jogadas dos jogadores não estão vazias
            if (jogada.getJogador1() == null || jogada.getJogador1().isEmpty() ||
                jogada.getJogador2() == null || jogada.getJogador2().isEmpty()) {
                addMessage("Erro", "Jogador 1 e Jogador 2 devem ser preenchidos.");
                return; // Não salva se algum jogador estiver vazio
            }

            String[] opcoes = {"Pedra", "Papel", "Tesoura"};
            jogada.setJogada1(opcoes[(int) (Math.random() * 3)]);
            jogada.setJogada2(opcoes[(int) (Math.random() * 3)]);

            // Calculando o resultado com as jogadas convertidas para minúsculas
            jogada.setResultado(calcularResultado(jogada.getJogada1().toLowerCase(), jogada.getJogada2().toLowerCase()));
            jogada.setData(new Date());

            jogadaDAO.salvar(jogada);
            carregarJogadas();
            addMessage("Sucesso", "Jogada salva com sucesso! Resultado: " + jogada.getResultado());
            
            jogada = new Jogada(); // Reseta o objeto após salvar
        } catch (Exception e) {
            addMessage("Erro", "Falha ao salvar a jogada.");
        }
    }


    public void editar(Jogada jogada) {
        try {
            jogadaDAO.editar(jogada); // Edita o registro
            carregarJogadas(); // Recarrega a lista de jogadas após editar
            addMessage("Sucesso", "Jogada editada com sucesso!");
        } catch (Exception e) {
            addMessage("Erro", "Erro ao editar jogada.");
        }
    }

    public void excluir(int id) {
        try {
            jogadaDAO.excluir(id); // Exclui o registro
            carregarJogadas(); // Recarrega a lista de jogadas após excluir
            addMessage("Sucesso", "Jogada excluída com sucesso!");
        } catch (Exception e) {
            addMessage("Erro", "Erro ao excluir jogada.");
        }
    }

    public void carregarJogadas() {
        jogadas = jogadaDAO.listar(); // Carrega as jogadas
        System.out.println("Jogadas carregadas: " + jogadas.size());  // Log para verificar quantos registros foram carregados
    }


    public void recarregar() {
        carregarJogadas(); // Recarrega as jogadas
        addMessage("Info", "Lista de jogadas atualizada.");
    }

    private String calcularResultado(String j1, String j2) {
        // Converte ambas as jogadas para minúsculas
        j1 = j1.toLowerCase();
        j2 = j2.toLowerCase();

        if (j1.equals(j2)) return "Empate";
        if ((j1.equals("pedra") && j2.equals("tesoura")) ||
            (j1.equals("tesoura") && j2.equals("papel")) ||
            (j1.equals("papel") && j2.equals("pedra"))) {
            return "Jogador 1 venceu";
        }
        return "Jogador 2 venceu";
    }



    private void addMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary, detail)); // Exibe mensagens no frontend
    }

    // Getters e Setters
    public Jogada getJogada() {
        return jogada;
    }

    public void setJogada(Jogada jogada) {
        this.jogada = jogada;
    }

    public List<Jogada> getJogadas() {
        return jogadas;
    }
}
