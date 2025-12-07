package org.example;

public class ArvoreAVL {
    private Node raiz;
    private int totalMissoes;

    public ArvoreAVL() {
        this.raiz = null;
        this.totalMissoes = 0;
    }

    /**
     * Retorna a altura de um nó
     */
    private int altura(Node node) {
        return (node == null) ? 0 : node.altura;
    }

    /**
     * Calcula o fator de balanceamento de um nó
     * Fator = altura(esquerda) - altura(direita)
     */
    private int fatorBalanceamento(Node node) {
        return (node == null) ? 0 : altura(node.esquerda) - altura(node.direita);
    }

    /**
     * Atualiza a altura de um nó
     */
    private void atualizarAltura(Node node) {
        if (node != null) {
            node.altura = 1 + Math.max(altura(node.esquerda), altura(node.direita));
        }
    }

    /**
     * Rotação simples à direita
     *       y                    x
     *      / \                  / \
     *     x   C    -->         A   y
     *    / \                      / \
     *   A   B                    B   C
     */
    private Node rotacaoDireita(Node y) {
        Node x = y.esquerda;
        Node B = x.direita;

        // Realizar rotação
        x.direita = y;
        y.esquerda = B;

        // Atualizar alturas
        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    /**
     * Rotação simples à esquerda
     *     x                      y
     *    / \                    / \
     *   A   y      -->         x   C
     *      / \                / \
     *     B   C              A   B
     */
    private Node rotacaoEsquerda(Node x) {
        Node y = x.direita;
        Node B = y.esquerda;

        // Realizar rotação
        y.esquerda = x;
        x.direita = B;

        // Atualizar alturas
        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    /**
     * Balanceia um nó após inserção ou remoção
     */
    private Node balancear(Node node) {
        if (node == null) return null;

        // Atualizar altura
        atualizarAltura(node);

        // Obter fator de balanceamento
        int balance = fatorBalanceamento(node);

        // Caso Esquerda-Esquerda
        if (balance > 1 && fatorBalanceamento(node.esquerda) >= 0) {
            return rotacaoDireita(node);
        }

        // Caso Esquerda-Direita
        if (balance > 1 && fatorBalanceamento(node.esquerda) < 0) {
            node.esquerda = rotacaoEsquerda(node.esquerda);
            return rotacaoDireita(node);
        }

        // Caso Direita-Direita
        if (balance < -1 && fatorBalanceamento(node.direita) <= 0) {
            return rotacaoEsquerda(node);
        }

        // Caso Direita-Esquerda
        if (balance < -1 && fatorBalanceamento(node.direita) > 0) {
            node.direita = rotacaoDireita(node.direita);
            return rotacaoEsquerda(node);
        }

        return node;
    }

    /**
     * Insere uma nova missão na árvore
     * @param nivel Nível de dificuldade (chave)
     * @param missao Descrição da missão
     */
    public void inserir(int nivel, String missao) {
        raiz = inserirRecursivo(raiz, nivel, missao);
        totalMissoes++;
    }

    private Node inserirRecursivo(Node node, int nivel, String missao) {
        // Inserção normal de BST
        if (node == null) {
            return new Node(nivel, missao);
        }

        if (nivel < node.nivel) {
            node.esquerda = inserirRecursivo(node.esquerda, nivel, missao);
        } else if (nivel > node.nivel) {
            node.direita = inserirRecursivo(node.direita, nivel, missao);
        } else {
            // Nível duplicado - atualiza a missão
            node.missao = missao;
            totalMissoes--;
            return node;
        }

        // Balancear o nó
        return balancear(node);
    }

    /**
     * Remove uma missão da árvore pelo nível
     * @param nivel Nível da missão a ser removida
     * @return true se removeu, false se não encontrou
     */
    public boolean remover(int nivel) {
        int antigo = totalMissoes;
        raiz = removerRecursivo(raiz, nivel);
        return totalMissoes < antigo;
    }

    private Node removerRecursivo(Node node, int nivel) {
        if (node == null) {
            return null;
        }

        // Procurar o nó a ser removido
        if (nivel < node.nivel) {
            node.esquerda = removerRecursivo(node.esquerda, nivel);
        } else if (nivel > node.nivel) {
            node.direita = removerRecursivo(node.direita, nivel);
        } else {
            // Nó encontrado - remover
            totalMissoes--;

            // Caso 1: Nó folha ou com um filho
            if (node.esquerda == null) {
                return node.direita;
            } else if (node.direita == null) {
                return node.esquerda;
            }

            // Caso 2: Nó com dois filhos
            // Encontrar o sucessor (menor nó da subárvore direita)
            Node sucessor = encontrarMinimo(node.direita);
            node.nivel = sucessor.nivel;
            node.missao = sucessor.missao;
            node.direita = removerRecursivo(node.direita, sucessor.nivel);
            totalMissoes++;
        }

        // Balancear o nó
        return balancear(node);
    }

    private Node encontrarMinimo(Node node) {
        while (node.esquerda != null) {
            node = node.esquerda;
        }
        return node;
    }

    /**
     * Busca uma missão pelo nível
     * @param nivel Nível da missão
     * @return String com a missão ou null se não encontrar
     */
    public String buscar(int nivel) {
        Node node = buscarRecursivo(raiz, nivel);
        return (node != null) ? node.toString() : null;
    }

    private Node buscarRecursivo(Node node, int nivel) {
        if (node == null || node.nivel == nivel) {
            return node;
        }

        if (nivel < node.nivel) {
            return buscarRecursivo(node.esquerda, nivel);
        } else {
            return buscarRecursivo(node.direita, nivel);
        }
    }

    /**
     * Retorna todas as missões em ordem crescente de nível
     */
    public String listarEmOrdem() {
        StringBuilder sb = new StringBuilder();
        listarEmOrdemRecursivo(raiz, sb);
        return sb.toString();
    }

    private void listarEmOrdemRecursivo(Node node, StringBuilder sb) {
        if (node != null) {
            listarEmOrdemRecursivo(node.esquerda, sb);
            sb.append(node.toString()).append("\n");
            listarEmOrdemRecursivo(node.direita, sb);
        }
    }

    /**
     * Visualiza a estrutura da árvore de forma hierárquica
     */
    public String visualizarArvore() {
        if (raiz == null) {
            return "Árvore vazia";
        }
        StringBuilder sb = new StringBuilder();
        visualizarRecursivo(raiz, "", true, sb);
        return sb.toString();
    }

    private void visualizarRecursivo(Node node, String prefixo, boolean isUltimo, StringBuilder sb) {
        if (node != null) {
            sb.append(prefixo);
            sb.append(isUltimo ? "└── " : "├── ");
            sb.append(String.format("Nv%d (h=%d, FB=%d): %s\n",
                    node.nivel, node.altura, fatorBalanceamento(node), node.missao));

            String novoPrefixo = prefixo + (isUltimo ? "    " : "│   ");

            if (node.esquerda != null || node.direita != null) {
                if (node.esquerda != null) {
                    visualizarRecursivo(node.esquerda, novoPrefixo, node.direita == null, sb);
                }
                if (node.direita != null) {
                    visualizarRecursivo(node.direita, novoPrefixo, true, sb);
                }
            }
        }
    }

    /**
     * Verifica se a árvore está vazia
     */
    public boolean estaVazia() {
        return raiz == null;
    }

    /**
     * Retorna o total de missões
     */
    public int getTotalMissoes() {
        return totalMissoes;
    }

    /**
     * Limpa todas as missões
     */
    public void limpar() {
        raiz = null;
        totalMissoes = 0;
    }
}