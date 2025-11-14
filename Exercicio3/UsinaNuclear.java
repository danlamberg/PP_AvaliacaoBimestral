/* A classe principal. Mantém a referência ao seu estado atual e delega
 todo o comportamento dependente de estado para o objeto de estado.
 Padrão State foi escolhido porque a Usina muda seu comportamento drasticamente 
com base no estado*/

package Exercicio3;

public class UsinaNuclear {

    private EstadoUsina estadoAtual;
    private EstadoUsina estadoSalvoAntesManutencao; // Usado pelo Decorator

    public UsinaNuclear() {
        // Estado inicial
        this.estadoAtual = new EstadoDesligada();
        System.out.println("Usina criada. Estado inicial: Desligada.");
    }

    public void setEstado(EstadoUsina novoEstado) {
        this.estadoAtual = novoEstado;
        System.out.println("--- NOVO ESTADO: " + novoEstado.getClass().getSimpleName() + " ---");
    }

    public void verificarSensores(Sensores dados) {
        this.estadoAtual.verificarSensores(this, dados);
    }

    public void ligar() {
        this.estadoAtual.ligar(this);
    }

    public void desligar() {
        this.estadoAtual.desligar(this);
    }
    public void entrarManutencao() {
        if (this.estadoAtual instanceof EstadoManutencaoDecorator) {
            System.out.println("Já está em manutenção.");
            return;
        }
        
        this.estadoSalvoAntesManutencao = this.estadoAtual;
        this.setEstado(new EstadoManutencaoDecorator(this.estadoSalvoAntesManutencao));
    }
    
    public void sairManutencao() {
        if (!(this.estadoAtual instanceof EstadoManutencaoDecorator)) {
            System.out.println("Não está em modo de manutenção.");
            return;
        }

        System.out.println("Saindo do modo de manutenção.");
        this.setEstado(this.estadoSalvoAntesManutencao);
        this.estadoSalvoAntesManutencao = null;
    }
}