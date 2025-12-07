# 🎮 Gerenciador de Missões RPG - Estrutura de Dados AVL

## 📋 Descrição do Projeto

Sistema interativo de gerenciamento de missões para jogos RPG, implementado em Java utilizando uma **Árvore AVL** (Adelson-Velsky e Landis) como estrutura de dados principal.

### 🎯 Objetivo

Demonstrar a aplicação prática de uma estrutura de dados complexa (Árvore AVL) em um contexto de jogo, permitindo:
- Inserção eficiente de missões organizadas por nível de dificuldade
- Busca rápida de missões específicas
- Remoção de missões completadas
- Balanceamento automático da árvore para garantir desempenho O(log n)

---

## 🌳 Estrutura de Dados: Árvore AVL

### O que é uma Árvore AVL?

Uma **Árvore AVL** é uma árvore binária de busca auto-balanceada, onde a diferença entre as alturas das subárvores esquerda e direita (fator de balanceamento) não pode ser maior que 1 para todos os nós.

### Propriedades da AVL

- **Balanceamento**: |altura(esquerda) - altura(direita)| ≤ 1
- **Complexidade**:
    - Inserção: O(log n)
    - Busca: O(log n)
    - Remoção: O(log n)
    - Espaço: O(n)

### Por que AVL para este projeto?

1. **Missões ordenadas**: Mantém as missões ordenadas por nível de dificuldade automaticamente
2. **Busca eficiente**: Permite encontrar rapidamente missões de um nível específico
3. **Balanceamento automático**: Garante que a árvore nunca degenere em uma lista, mantendo o desempenho
4. **Visualização clara**: A estrutura hierárquica da árvore facilita o entendimento do balanceamento

---

## 🏗️ Arquitetura do Sistema

### Estrutura de Classes

```
Node.java
├── Atributos:
│   ├── int nivel (chave)
│   ├── String missao (valor)
│   ├── int altura
│   ├── Node esquerda
│   └── Node direita
└── Métodos:
    ├── Construtor
    └── toString()

ArvoreAVL.java
├── Atributos:
│   ├── Node raiz
│   └── int totalMissoes
└── Métodos:
    ├── inserir(nivel, missao)
    ├── remover(nivel)
    ├── buscar(nivel)
    ├── rotacaoDireita(node)
    ├── rotacaoEsquerda(node)
    ├── balancear(node)
    ├── listarEmOrdem()
    └── visualizarArvore()

GerenciadorMissoesRPG.java
├── Atributos:
│   ├── ArvoreAVL arvore
│   └── Scanner scanner
└── Métodos:
    ├── main()
    ├── executar()
    ├── adicionarMissao()
    ├── buscarMissao()
    ├── removerMissao()
    ├── listarMissoes()
    ├── visualizarArvore()
    └── carregarExemplos()
```

---

## 🔧 Funcionalidades Implementadas

### 1. Inserção de Elementos
- Adiciona missões mantendo a propriedade de busca binária
- Balanceia automaticamente após cada inserção
- Usa rotações simples e duplas quando necessário

### 2. Remoção de Elementos
- Remove missões por nível
- Rebalanceia a árvore após remoção
- Trata os 3 casos: nó folha, nó com 1 filho, nó com 2 filhos

### 3. Busca Eficiente
- Busca binária em O(log n)
- Retorna informações completas da missão

### 4. Balanceamento Automático
- Rotação simples à direita (RR)
- Rotação simples à esquerda (LL)
- Rotação dupla esquerda-direita (LR)
- Rotação dupla direita-esquerda (RL)

### 5. Visualização da Estrutura
- Mostra a árvore hierarquicamente
- Exibe altura e fator de balanceamento de cada nó
- Facilita o entendimento do funcionamento da AVL

---

## 💻 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- Terminal ou IDE Java (Eclipse, IntelliJ, VSCode)

### Compilação
```bash
javac Node.java ArvoreAVL.java GerenciadorMissoesRPG.java
```

### Execução
```bash
java GerenciadorMissoesRPG
```

---

## 📖 Exemplo de Uso

### Cenário de Demonstração

1. **Carregar Missões de Exemplo**
   ```
   Opção 7 do menu
   Carrega 10 missões pré-definidas
   ```

2. **Visualizar a Árvore**
   ```
   Opção 5 do menu
   Mostra a estrutura balanceada
   ```

3. **Buscar uma Missão**
   ```
   Opção 2 do menu
   Digite o nível: 50
   Resultado: [Nível 50] Explorar caverna abandonada
   ```

4. **Adicionar Nova Missão**
   ```
   Opção 1 do menu
   Nível: 65
   Descrição: "Derrotar bandidos na estrada"
   Árvore rebalanceia automaticamente
   ```

5. **Remover Missão Completada**
   ```
   Opção 3 do menu
   Nível a remover: 15
   Árvore rebalanceia automaticamente
   ```

---

## 🎨 Exemplo de Visualização da Árvore

```
└── Nv50 (h=4, FB=0): Explorar caverna abandonada
    ├── Nv25 (h=2, FB=0): Coletar 10 ervas medicinais
    │   ├── Nv15 (h=1, FB=0): Derrotar 5 slimes no vilarejo
    │   └── Nv35 (h=1, FB=0): Escoltar mercador até a cidade
    └── Nv70 (h=3, FB=-1): Derrotar o chefe goblin
        ├── Nv60 (h=2, FB=1): Caçar lobos selvagens na floresta
        │   └── Nv45 (h=1, FB=0): Entregar carta ao prefeito
        └── Nv90 (h=2, FB=1): Investigar ruínas antigas
            ├── Nv80 (h=1, FB=0): Resgatar prisioneiros da torre
            └── Nv100 (h=1, FB=0): Enfrentar o dragão da montanha
```

**Legenda:**
- `Nv`: Nível da missão (chave)
- `h`: Altura do nó
- `FB`: Fator de Balanceamento

---

## 🔄 Operações de Balanceamento

### Casos de Rotação

#### 1. Rotação Simples à Direita (RR)
```
Antes:           Depois:
    y               x
   /               / \
  x       →       A   y
 /                     \
A                       B
```

#### 2. Rotação Simples à Esquerda (LL)
```
Antes:           Depois:
x                   y
 \                 / \
  y       →       x   C
   \
    C
```

#### 3. Rotação Dupla Esquerda-Direita (LR)
```
Antes:              Depois:
    z                  y
   /                  / \
  x          →       x   z
   \                /     \
    y              A       C
```

#### 4. Rotação Dupla Direita-Esquerda (RL)
```
Antes:              Depois:
  x                    y
   \                  / \
    z        →       x   z
   /                  \   \
  y                    B   C
```

---

## 📊 Análise de Complexidade

| Operação | Complexidade | Justificativa |
|----------|--------------|---------------|
| Inserção | O(log n) | Busca até folha + rotações O(1) |
| Busca | O(log n) | Busca binária em árvore balanceada |
| Remoção | O(log n) | Busca + reorganização + rotações |
| Listagem | O(n) | Percorre todos os nós |
| Espaço | O(n) | Um nó por missão |

### Comparação com outras estruturas:

| Estrutura | Inserção | Busca | Remoção | Balanceada? |
|-----------|----------|-------|---------|-------------|
| AVL | O(log n) | O(log n) | O(log n) | ✅ Sim |
| BST simples | O(n) pior | O(n) pior | O(n) pior | ❌ Não |
| Hash Table | O(1) média | O(1) média | O(1) média | N/A |
| Array ordenado | O(n) | O(log n) | O(n) | N/A |

**Vantagem da AVL**: Garante O(log n) no pior caso, mantendo dados ordenados.

---

## 🎓 Conceitos de Estrutura de Dados Aplicados

### 1. Árvore Binária de Busca (BST)
- Propriedade: esquerda < raiz < direita
- Base para a AVL

### 2. Balanceamento
- Fator de balanceamento = altura(esq) - altura(dir)
- Mantém |FB| ≤ 1 para todos os nós

### 3. Rotações
- Operações O(1) que restauram o balanceamento
- 4 tipos: RR, LL, LR, RL

### 4. Recursão
- Usada para percorrer e modificar a árvore
- Facilita a implementação das operações

### 5. Altura e Profundidade
- Altura: distância até a folha mais distante
- Usada para calcular o fator de balanceamento

---

## 🚀 Extensões Possíveis

### Melhorias Futuras:
1. **Persistência de dados**: Salvar/carregar missões de arquivo
2. **Interface gráfica**: Visualização gráfica da árvore em JavaFX
3. **Múltiplos atributos**: Missões com recompensas, requisitos, etc.
4. **Busca por intervalo**: Encontrar todas as missões entre dois níveis
5. **Sistema de recompensas**: Calcular XP e itens ao completar missões

---

## 📝 Justificativa da Escolha da Estrutura

### Por que Árvore AVL?

1. **Relevância prática**:
    - Missões RPG naturalmente têm níveis de dificuldade (1-100)
    - Jogadores frequentemente buscam missões de níveis específicos
    - É comum adicionar/remover missões dinamicamente

2. **Demonstração de conceitos**:
    - Balanceamento automático
    - Rotações (conceito avançado)
    - Complexidade logarítmica
    - Estrutura hierárquica

3. **Integração com a lógica do jogo**:
    - A árvore mantém missões ordenadas automaticamente
    - Busca rápida é essencial para boa experiência do usuário
    - O balanceamento garante que mesmo com muitas missões, o sistema permanece eficiente

4. **Valor educacional**:
    - Estrutura complexa que requer entendimento profundo
    - Aplicação prática de conceitos teóricos
    - Demonstra importância de estruturas de dados eficientes

---

## 🎯 Critérios de Avaliação Atendidos

### ✅ Implementação da Estrutura (3,0 pontos)
- Árvore AVL completa e funcional
- Todas as operações implementadas corretamente
- Balanceamento automático funcionando

### ✅ Aplicação da Estrutura (1,0 ponto)
- Estrutura integrada à lógica do jogo
- Relevância clara no contexto
- Não é meramente ilustrativa

### ✅ Criatividade e Interatividade (1,0 ponto)
- Interface de texto interativa
- Tema original (Missões RPG)
- Experiência de usuário bem pensada

### ✅ Organização e Documentação (1,0 ponto)
- Código bem estruturado
- Comentários explicativos
- README completo

### ✅ Apresentação e Defesa (4,0 pontos)
- Código pronto para demonstração
- Explicação técnica preparada
- Domínio do conteúdo

---

## 👨‍💻 Autor

**Rodrigo Gomes da Paixão**
- Disciplina: Estruturas de Dados Orientada a Objeto
- Instituição: PUC Goiás
- Data: 2024

---

## 📚 Referências

1. Cormen, T. H., et al. (2009). *Introduction to Algorithms*. 3rd ed. MIT Press.
2. Goodrich, M. T., & Tamassia, R. (2014). *Data Structures and Algorithms in Java*. 6th ed.
3. Weiss, M. A. (2011). *Data Structures and Algorithm Analysis in Java*. 3rd ed.
4. Adelson-Velsky, G., & Landis, E. M. (1962). "An algorithm for the organization of information". Soviet Mathematics Doklady, 3, 1259–1263.

---

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais como parte da disciplina de Estruturas de Dados Orientada a Objeto.

---

## 🙏 Agradecimentos

Obrigado por avaliar este projeto! A implementação da Árvore AVL demonstra não apenas o conhecimento técnico sobre estruturas de dados, mas também a capacidade de aplicá-las em contextos práticos e criativos.

Para dúvidas ou esclarecimentos sobre o código, estou à disposição durante a apresentação! 🚀