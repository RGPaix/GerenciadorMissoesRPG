package org.example;
import java.util.Scanner;

public class GerenciadorMissoesRPG {
    private ArvoreAVL arvore;
    private Scanner scanner;

    public GerenciadorMissoesRPG() {
        this.arvore = new ArvoreAVL();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Exibe o menu principal
     */
    private void exibirMenu() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║   GERENCIADOR DE MISSÕES RPG - AVL         ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│ 1.  Adicionar Missão                       │");
        System.out.println("│ 2.  Buscar Missão por Nível                │");
        System.out.println("│ 3.  Remover Missão                         │");
        System.out.println("│ 4.  Listar Todas as Missões                │");
        System.out.println("│ 5.  Visualizar Árvore (estrutura)          │");
        System.out.println("│ 6.  Estatísticas                           │");
        System.out.println("│ 7.  Carregar Missões de Exemplo            │");
        System.out.println("│ 8.  Limpar Todas as Missões                │");
        System.out.println("│ 0.  Sair                                   │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Adiciona uma nova missão
     */
    private void adicionarMissao() {
        System.out.println("\n=== ADICIONAR MISSÃO ===");

        System.out.print("Nível de dificuldade (1-100): ");
        int nivel = lerInteiro(1, 100);

        System.out.print("Descrição da missão: ");
        scanner.nextLine(); // Limpar buffer
        String descricao = scanner.nextLine();

        arvore.inserir(nivel, descricao);
        System.out.println("✓ Missão adicionada com sucesso!");
        System.out.println("⚠ Árvore automaticamente balanceada!");
    }

    /**
     * Busca uma missão por nível
     */
    private void buscarMissao() {
        System.out.println("\n=== BUSCAR MISSÃO ===");

        System.out.print("Nível da missão: ");
        int nivel = lerInteiro(1, 100);

        String resultado = arvore.buscar(nivel);
        if (resultado != null) {
            System.out.println("✓ Missão encontrada: " + resultado);
        } else {
            System.out.println("✗ Nenhuma missão encontrada no nível " + nivel);
        }
    }

    /**
     * Remove uma missão
     */
    private void removerMissao() {
        System.out.println("\n=== REMOVER MISSÃO ===");

        System.out.print("Nível da missão a remover: ");
        int nivel = lerInteiro(1, 100);

        boolean removido = arvore.remover(nivel);
        if (removido) {
            System.out.println("✓ Missão removida com sucesso!");
            System.out.println("⚠ Árvore automaticamente balanceada!");
        } else {
            System.out.println("✗ Nenhuma missão encontrada no nível " + nivel);
        }
    }

    /**
     * Lista todas as missões em ordem
     */
    private void listarMissoes() {
        System.out.println("\n=== LISTA DE MISSÕES (ordem crescente) ===");

        if (arvore.estaVazia()) {
            System.out.println("Nenhuma missão cadastrada.");
            return;
        }

        System.out.println(arvore.listarEmOrdem());
        System.out.println("Total: " + arvore.getTotalMissoes() + " missões");
    }

    /**
     * Visualiza a estrutura da árvore
     */
    private void visualizarArvore() {
        System.out.println("\n=== ESTRUTURA DA ÁRVORE AVL ===");
        System.out.println("Legenda: Nv=Nível, h=Altura, FB=Fator de Balanceamento\n");

        System.out.println(arvore.visualizarArvore());
    }

    /**
     * Exibe estatísticas
     */
    private void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS ===");
        System.out.println("Total de missões: " + arvore.getTotalMissoes());
        System.out.println("Status: " + (arvore.estaVazia() ? "Vazia" : "Contém dados"));
        System.out.println("\n💡 Propriedade AVL: A árvore está sempre balanceada!");
        System.out.println("   |Altura(esquerda) - Altura(direita)| ≤ 1 para todos os nós");
    }

    /**
     * Carrega missões de exemplo
     */
    private void carregarExemplos() {
        System.out.println("\n=== CARREGAR MISSÕES DE EXEMPLO ===");

        String[][] missoes = {
                {"15", "Derrotar 5 slimes no vilarejo"},
                {"25", "Coletar 10 ervas medicinais"},
                {"35", "Escoltar mercador até a cidade"},
                {"50", "Explorar caverna abandonada"},
                {"60", "Caçar lobos selvagens na floresta"},
                {"45", "Entregar carta ao prefeito"},
                {"70", "Derrotar o chefe goblin"},
                {"80", "Resgatar prisioneiros da torre"},
                {"90", "Investigar ruínas antigas"},
                {"100", "Enfrentar o dragão da montanha"}
        };

        for (String[] missao : missoes) {
            arvore.inserir(Integer.parseInt(missao[0]), missao[1]);
        }

        System.out.println("✓ " + missoes.length + " missões de exemplo carregadas!");
        System.out.println("⚠ Árvore balanceada automaticamente durante as inserções!");
    }

    /**
     * Limpa todas as missões
     */
    private void limparMissoes() {
        System.out.print("\n⚠ Tem certeza que deseja limpar todas as missões? (S/N): ");
        String resposta = scanner.next().toUpperCase();

        if (resposta.equals("S")) {
            arvore.limpar();
            System.out.println("✓ Todas as missões foram removidas!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    /**
     * Lê um inteiro com validação
     */
    private int lerInteiro(int min, int max) {
        while (true) {
            try {
                int valor = scanner.nextInt();
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.print("Valor deve estar entre " + min + " e " + max + ". Tente novamente: ");
            } catch (Exception e) {
                System.out.print("Entrada inválida. Digite um número: ");
                scanner.next();
            }
        }
    }

    /**
     * Executa o programa principal
     */
    public void executar() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║     BEM-VINDO AO GERENCIADOR DE MISSÕES RPG       ║");
        System.out.println("║        Estrutura de Dados: Árvore AVL             ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.println("\n📚 Árvore AVL: Árvore binária de busca auto-balanceada");
        System.out.println("   - Inserção: O(log n)");
        System.out.println("   - Busca: O(log n)");
        System.out.println("   - Remoção: O(log n)");

        boolean executando = true;

        while (executando) {
            exibirMenu();
            int opcao = lerInteiro(0, 8);

            switch (opcao) {
                case 1:
                    adicionarMissao();
                    break;
                case 2:
                    buscarMissao();
                    break;
                case 3:
                    removerMissao();
                    break;
                case 4:
                    listarMissoes();
                    break;
                case 5:
                    visualizarArvore();
                    break;
                case 6:
                    exibirEstatisticas();
                    break;
                case 7:
                    carregarExemplos();
                    break;
                case 8:
                    limparMissoes();
                    break;
                case 0:
                    System.out.println("\n╔═══════════════════════════════════════════════════╗");
                    System.out.println("║          Obrigado por usar o sistema!            ║");
                    System.out.println("║         Aventuras aguardam você! 🗡️🛡️              ║");
                    System.out.println("╚═══════════════════════════════════════════════════╝\n");
                    executando = false;
                    break;
            }
        }

        scanner.close();
    }

    /**
     * Metodo main - ponto de entrada do programa
     */
    public static void main(String[] args) {
        GerenciadorMissoesRPG gerenciador = new GerenciadorMissoesRPG();
        gerenciador.executar();
    }
}