# DOCUMENTAÇÃO TÉCNICA
## Sistema Gerenciador de Missões RPG com Árvore AVL

---

## 1. INTRODUÇÃO

### 1.1 Contexto
Este projeto implementa um sistema de gerenciamento de missões para jogos RPG (Role-Playing Game) utilizando uma Árvore AVL como estrutura de dados principal. A escolha da Árvore AVL justifica-se pela necessidade de manter as missões organizadas por nível de dificuldade, permitindo inserções, buscas e remoções eficientes.

### 1.2 Objetivos
- Implementar uma Árvore AVL completa e funcional em Java
- Demonstrar a aplicação prática da estrutura em um contexto de jogo
- Garantir operações eficientes (O(log n)) através do balanceamento automático
- Criar uma interface interativa para demonstração das funcionalidades

---

## 2. FUNDAMENTAÇÃO TEÓRICA

### 2.1 Árvore Binária de Busca (BST)
Uma árvore binária de busca é uma estrutura de dados hierárquica onde cada nó possui no máximo dois filhos, e para qualquer nó:
- Todos os valores na subárvore esquerda são menores que o valor do nó
- Todos os valores na subárvore direita são maiores que o valor do nó

**Problema**: Em casos degenerados (inserções ordenadas), a BST pode se tornar uma lista encadeada, degradando o desempenho para O(n).

### 2.2 Árvore AVL
Nomeada após seus inventores Adelson-Velsky e Landis (1962), a Árvore AVL é uma BST auto-balanceada que mantém a seguinte propriedade:

**Propriedade AVL**: Para todo nó da árvore, a diferença entre as alturas das subárvores esquerda e direita não pode ser maior que 1.

Matematicamente: |altura(esquerda) - altura(direita)| ≤ 1

### 2.3 Fator de Balanceamento
O fator de balanceamento (FB) de um nó é definido como:

FB(nó) = altura(subárvore_esquerda) - altura(subárvore_direita)

Valores possíveis em uma AVL válida: -1, 0, +1

### 2.4 Operações de Rotação
Quando um nó viola a propriedade AVL após uma inserção ou remoção, rotações são realizadas para restaurar o balanceamento:

#### 2.4.1 Rotação Simples à Direita (RR)
Aplicada quando FB(nó) > 1 e FB(filho_esquerdo) ≥ 0

#### 2.4.2 Rotação Simples à Esquerda (LL)
Aplicada quando FB(nó) < -1 e FB(filho_direito) ≤ 0

#### 2.4.3 Rotação Dupla Esquerda-Direita (LR)
Aplicada quando FB(nó) > 1 e FB(filho_esquerdo) < 0
Executa: rotação_esquerda(filho) seguida de rotação_direita(nó)

#### 2.4.4 Rotação Dupla Direita-Esquerda (RL)
Aplicada quando FB(nó) < -1 e FB(filho_direito) > 0
Executa: rotação_direita(filho) seguida de rotação_esquerda(nó)

---

## 3. IMPLEMENTAÇÃO

### 3.1 Estrutura de Classes

#### 3.1.1 Classe Node
Representa um nó individual da árvore.

**Atributos**:
- `int nivel`: Chave de ordenação (nível de dificuldade da missão)
- `String missao`: Valor armazenado (descrição da missão)
- `int altura`: Altura do nó na árvore
- `Node esquerda`: Referência para filho esquerdo
- `Node direita`: Referência para filho direito

**Invariante**: altura = 1 + max(altura(esquerda), altura(direita))

#### 3.1.2 Classe ArvoreAVL
Implementa a estrutura AVL e suas operações.

**Atributos**:
- `Node raiz`: Referência para o nó raiz
- `int totalMissoes`: Contador de missões armazenadas

**Métodos principais**:

1. **inserir(nivel, missao)**
    - Complexidade: O(log n)
    - Descrição: Insere nova missão e rebalanceia
    - Algoritmo:
      ```
      1. Inserção BST padrão (recursiva)
      2. Atualizar altura do nó
      3. Calcular fator de balanceamento
      4. Aplicar rotações se necessário
      5. Retornar nova raiz da subárvore
      ```

2. **remover(nivel)**
    - Complexidade: O(log n)
    - Descrição: Remove missão e rebalanceia
    - Algoritmo:
      ```
      1. Buscar nó a ser removido
      2. Remover conforme caso (0, 1 ou 2 filhos)
      3. Atualizar altura dos nós afetados
      4. Rebalancear caminho até a raiz
      5. Retornar nova raiz da subárvore
      ```

3. **buscar(nivel)**
    - Complexidade: O(log n)
    - Descrição: Busca binária por nível
    - Algoritmo: BST padrão

4. **balancear(node)**
    - Complexidade: O(1)
    - Descrição: Verifica e corrige desbalanceamento
    - Algoritmo:
      ```
      1. Calcular FB do nó
      2. Se FB > 1: caso esquerda
         a. Se FB(esquerda) ≥ 0: rotação direita
         b. Caso contrário: rotação LR
      3. Se FB < -1: caso direita
         a. Se FB(direita) ≤ 0: rotação esquerda
         b. Caso contrário: rotação RL
      4. Retornar nó balanceado
      ```

#### 3.1.3 Classe GerenciadorMissoesRPG
Interface de usuário e controle principal.

**Responsabilidades**:
- Gerenciar interação com usuário
- Validar entradas
- Exibir resultados formatados
- Demonstrar funcionalidades da AVL

### 3.2 Decisões de Design

#### 3.2.1 Chave: Nível de Dificuldade
- Tipo: int (1-100)
- Justificativa: Ordem natural para progressão em jogos
- Vantagem: Permite buscas por faixa de dificuldade

#### 3.2.2 Valores Únicos
- Decisão: Níveis únicos (sem missões duplicadas no mesmo nível)
- Alternativa considerada: Lista de missões por nível
- Escolha: Simplicidade e clareza na demonstração

#### 3.2.3 Atualização de Altura
- Método: Recursivo durante inserção/remoção
- Complexidade adicional: O(1) por nó
- Vantagem: Mantém invariante sem busca adicional

---

## 4. ANÁLISE DE COMPLEXIDADE

### 4.1 Complexidade Temporal

| Operação | Melhor Caso | Caso Médio | Pior Caso |
|----------|-------------|------------|-----------|
| Inserção | O(log n) | O(log n) | O(log n) |
| Busca | O(log n) | O(log n) | O(log n) |
| Remoção | O(log n) | O(log n) | O(log n) |
| Listagem | O(n) | O(n) | O(n) |

**Justificativa do O(log n)**:
- A AVL mantém altura h ≈ log₂(n)
- Cada operação percorre no máximo h níveis
- Rotações são O(1)
- Logo, operações são O(h) = O(log n)

### 4.2 Complexidade Espacial

- **Espaço para dados**: O(n) - um nó por missão
- **Espaço recursivo**: O(log n) - pilha de chamadas
- **Espaço total**: O(n)

### 4.3 Comparação com Alternativas

#### Array Dinâmico Ordenado:
- Busca: O(log n) [busca binária]
- Inserção: O(n) [deslocamento de elementos]
- Remoção: O(n) [deslocamento de elementos]
- **Desvantagem**: Inserções/remoções custosas

#### Hash Table:
- Busca: O(1) esperado
- Inserção: O(1) esperado
- Remoção: O(1) esperado
- **Desvantagem**: Não mantém ordem, não permite range queries

#### BST não balanceada:
- Busca: O(n) pior caso
- Inserção: O(n) pior caso
- Remoção: O(n) pior caso
- **Desvantagem**: Degeneração em lista

#### Red-Black Tree:
- Mesma complexidade que AVL
- **Diferença**: AVL é mais rigidamente balanceada (melhor para buscas frequentes)

**Conclusão**: AVL é ideal para este cenário onde buscas são frequentes e dados precisam estar ordenados.

---

## 5. DEMONSTRAÇÃO E TESTES

### 5.1 Cenários de Teste

#### 5.1.1 Teste de Inserção
**Objetivo**: Verificar balanceamento automático

**Sequência**: Inserir níveis {50, 25, 75, 10, 30, 60, 80}

**Resultado esperado**: Árvore balanceada com raiz = 50

#### 5.1.2 Teste de Rotação RR
**Objetivo**: Forçar rotação à direita

**Sequência**: Inserir níveis {30, 20, 10} em ordem

**Resultado esperado**:
```
Antes:       Depois:
  30           20
 /            / \
20           10  30
/
10
```

#### 5.1.3 Teste de Rotação LR
**Objetivo**: Forçar rotação dupla

**Sequência**: Inserir níveis {30, 10, 20} em ordem

**Resultado esperado**:
```
Antes:       Depois:
  30           20
 /            / \
10           10  30
 \
  20
```

#### 5.1.4 Teste de Remoção
**Objetivo**: Verificar rebalanceamento após remoção

**Sequência**:
1. Inserir {50, 25, 75, 10, 30, 60, 80}
2. Remover 10
3. Verificar balanceamento

### 5.2 Validação

#### Critérios de Validação:
1. **Propriedade BST**: esquerda < raiz < direita (sempre)
2. **Propriedade AVL**: |FB| ≤ 1 para todos os nós
3. **Altura correta**: altura = 1 + max(h_esq, h_dir)
4. **Contagem**: totalMissoes corresponde aos nós presentes

---

## 6. RELEVÂNCIA E APLICAÇÕES

### 6.1 Contexto de Jogos

#### Requisitos Típicos:
- **Progressão por níveis**: Jogadores buscam missões adequadas ao seu nível
- **Adição dinâmica**: Novas missões são adicionadas (DLCs, eventos)
- **Remoção**: Missões completadas são removidas da lista ativa
- **Busca rápida**: Interface responsiva requer buscas eficientes

#### Como a AVL atende:
- Mantém missões ordenadas automaticamente
- O(log n) garante responsividade mesmo com milhares de missões
- Estrutura hierárquica facilita implementação de sistemas de progressão

### 6.2 Outras Aplicações de AVL

1. **Bancos de Dados**: Índices B-Tree (variação de AVL)
2. **Sistemas de Arquivos**: Organização hierárquica
3. **Compiladores**: Tabelas de símbolos
4. **Roteadores de Rede**: Tabelas de roteamento
5. **Sistemas Operacionais**: Gerenciamento de memória

---

## 7. LIMITAÇÕES E TRABALHOS FUTUROS

### 7.1 Limitações Atuais

1. **Chave única**: Não suporta múltiplas missões no mesmo nível
    - Solução: Usar lista encadeada como valor

2. **Sem persistência**: Dados perdidos ao fechar programa
    - Solução: Implementar serialização

3. **Interface texto**: Limitada para demonstração visual
    - Solução: GUI com JavaFX ou Swing

### 7.2 Extensões Propostas

1. **Busca por intervalo**: Encontrar missões em faixa de níveis
   ```java
   List<Missao> buscarIntervalo(int nivelMin, int nivelMax)
   ```

2. **Múltiplos critérios**: Ordenação secundária por recompensa
   ```java
   class Missao implements Comparable<Missao> {
       int nivel;
       int recompensa;
       // ...
   }
   ```

3. **Estatísticas avançadas**: Análise de balanceamento
   ```java
   int calcularRotacoesRealizadas()
   double calcularFatorBalanceamentoMedio()
   ```

4. **Visualização gráfica**: Renderização da árvore
    - Usar JavaFX Canvas
    - Animações de rotações

---

## 8. CONCLUSÃO

### 8.1 Objetivos Alcançados

✅ Implementação completa de Árvore AVL
✅ Aplicação prática em contexto de jogo
✅ Interface interativa funcional
✅ Demonstração de conceitos avançados (rotações)
✅ Código documentado e organizado

### 8.2 Aprendizados

1. **Teóricos**:
    - Importância do balanceamento em estruturas de dados
    - Trade-offs entre diferentes estruturas
    - Análise de complexidade na prática

2. **Práticos**:
    - Implementação de algoritmos recursivos complexos
    - Design de interfaces intuitivas
    - Documentação técnica profissional

### 8.3 Contribuições

Este projeto demonstra que estruturas de dados complexas não são apenas conceitos teóricos, mas ferramentas práticas para resolver problemas reais de forma eficiente. A escolha cuidadosa da estrutura de dados adequada pode ser a diferença entre um sistema lento e responsivo.

---

## 9. REFERÊNCIAS BIBLIOGRÁFICAS

[1] ADELSON-VELSKY, G.; LANDIS, E. M. An algorithm for the organization of information. Soviet Mathematics Doklady, v. 3, p. 1259-1263, 1962.

[2] CORMEN, Thomas H. et al. Introduction to Algorithms. 3rd ed. Cambridge: MIT Press, 2009.

[3] GOODRICH, Michael T.; TAMASSIA, Roberto. Data Structures and Algorithms in Java. 6th ed. Hoboken: Wiley, 2014.

[4] WEISS, Mark Allen. Data Structures and Algorithm Analysis in Java. 3rd ed. Boston: Pearson, 2011.

[5] KNUTH, Donald E. The Art of Computer Programming, Volume 3: Sorting and Searching. 2nd ed. Reading: Addison-Wesley, 1998.

[6] SEDGEWICK, Robert; WAYNE, Kevin. Algorithms. 4th ed. Boston: Addison-Wesley, 2011.

[7] ORACLE. Java Platform, Standard Edition 8 API Specification. Disponível em: https://docs.oracle.com/javase/8/docs/api/

---

## APÊNDICE A: Código Completo

[Ver arquivos: Node.java, ArvoreAVL.java, GerenciadorMissoesRPG.java]

---

## APÊNDICE B: Instruções de Execução

1. Compilar: `javac *.java`
2. Executar: `java GerenciadorMissoesRPG`
3. Escolher opção 7 para carregar exemplos
4. Explorar as funcionalidades pelo menu

---

**Documento gerado para o projeto de Estruturas de Dados**
**Data**: 2024
**Versão**: 1.0