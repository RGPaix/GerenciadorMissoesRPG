package org.example;

public class Node {
    int nivel;           // Nível de dificuldade da missão (chave)
    String missao;       // Descrição da missão
    int altura;          // Altura do nó na árvore
    Node esquerda;       // Filho esquerdo
    Node direita;        // Filho direito

    /**
     * Construtor do nó
     * @param nivel Nível de dificuldade (1-100)
     * @param missao Descrição da missão
     */
    public Node(int nivel, String missao) {
        this.nivel = nivel;
        this.missao = missao;
        this.altura = 1;
        this.esquerda = null;
        this.direita = null;
    }

    @Override
    public String toString() {
        return String.format("[Nível %d] %s", nivel, missao);
    }
}
