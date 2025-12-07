package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Interface Gráfica para o Gerenciador de Missões RPG usando JavaFX
 * Versão moderna e intuitiva da aplicação
 */
public class GerenciadorMissoesGUI extends Application {

    private ArvoreAVL arvore;
    private TextArea outputArea;
    private TextField nivelField;
    private TextField missaoField;

    @Override
    public void start(Stage primaryStage) {
        arvore = new ArvoreAVL();

        primaryStage.setTitle("🎮 Gerenciador de Missões RPG - Árvore AVL");

        // Layout principal
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2b2b2b;");

        // Header
        VBox header = createHeader();
        root.setTop(header);

        // Painel de controles (esquerda)
        VBox controlPanel = createControlPanel();
        root.setLeft(controlPanel);

        // Área de saída (centro)
        VBox outputPanel = createOutputPanel();
        root.setCenter(outputPanel);

        // Painel de estatísticas (direita)
        VBox statsPanel = createStatsPanel();
        root.setRight(statsPanel);

        // Criar cena
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Mensagem de boas-vindas
        showWelcomeMessage();
    }

    /**
     * Cria o cabeçalho da aplicação
     */
    private VBox createHeader() {
        VBox header = new VBox(10);
        header.setPadding(new Insets(20));
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: linear-gradient(to right, #2196F3, #764ba2);");

        Label titleLabel = new Label("🎮 GERENCIADOR DE MISSÕES RPG");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.WHITE);

        Label subtitleLabel = new Label("Estrutura de Dados: Árvore AVL Auto-Balanceada");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        subtitleLabel.setTextFill(Color.web("#e0e0e0"));

        header.getChildren().addAll(titleLabel, subtitleLabel);
        return header;
    }

    /**
     * Cria o painel de controles
     */
    private VBox createControlPanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(20));
        panel.setPrefWidth(300);
        panel.setStyle("-fx-background-color: #363636;");

        Label controlLabel = new Label("⚙️ Controles");
        controlLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        controlLabel.setTextFill(Color.WHITE);

        // Campos de entrada
        VBox inputBox = createInputFields();

        // Botões de ação
        VBox buttonBox = createActionButtons();

        Separator sep = new Separator();
        sep.setStyle("-fx-background-color: #555555;");

        panel.getChildren().addAll(controlLabel, inputBox, sep, buttonBox);
        return panel;
    }

    /**
     * Cria os campos de entrada
     */
    private VBox createInputFields() {
        VBox box = new VBox(10);

        Label nivelLabel = new Label("Nível da Missão (1-100):");
        nivelLabel.setTextFill(Color.web("#e0e0e0"));

        nivelField = new TextField();
        nivelField.setPromptText("Digite o nível...");
        nivelField.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white; -fx-prompt-text-fill: gray;");

        Label missaoLabel = new Label("Descrição da Missão:");
        missaoLabel.setTextFill(Color.web("#e0e0e0"));

        missaoField = new TextField();
        missaoField.setPromptText("Digite a descrição...");
        missaoField.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white; -fx-prompt-text-fill: gray;");

        box.getChildren().addAll(nivelLabel, nivelField, missaoLabel, missaoField);
        return box;
    }

    /**
     * Cria os botões de ação
     */
    private VBox createActionButtons() {
        VBox box = new VBox(10);

        Button addButton = createStyledButton("➕ Adicionar Missão", "#4CAF50");
        addButton.setOnAction(e -> adicionarMissao());

        Button searchButton = createStyledButton("🔍 Buscar Missão", "#2196F3");
        searchButton.setOnAction(e -> buscarMissao());

        Button removeButton = createStyledButton("❌ Remover Missão", "#f44336");
        removeButton.setOnAction(e -> removerMissao());

        Button listButton = createStyledButton("📋 Listar Todas", "#FF9800");
        listButton.setOnAction(e -> listarMissoes());

        Button visualizeButton = createStyledButton("🌳 Visualizar Árvore", "#9C27B0");
        visualizeButton.setOnAction(e -> visualizarArvore());

        Button examplesButton = createStyledButton("🎮 Carregar Exemplos", "#00BCD4");
        examplesButton.setOnAction(e -> carregarExemplos());

        Button clearButton = createStyledButton("🗑️ Limpar Tudo", "#607D8B");
        clearButton.setOnAction(e -> limparTudo());

        box.getChildren().addAll(
                addButton, searchButton, removeButton,
                listButton, visualizeButton, examplesButton, clearButton
        );

        return box;
    }

    /**
     * Cria um botão estilizado
     */
    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setPrefWidth(250);
        button.setPrefHeight(40);
        button.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 5;" +
                        "-fx-cursor: hand;"
        );

        // Efeito hover
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: derive(" + color + ", -10%);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 5;" +
                        "-fx-cursor: hand;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 5;" +
                        "-fx-cursor: hand;"
        ));

        return button;
    }

    /**
     * Cria o painel de saída
     */
    private VBox createOutputPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(20));

        Label outputLabel = new Label("📊 Resultado das Operações");
        outputLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        outputLabel.setTextFill(Color.WHITE);

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setStyle(
                "-fx-control-inner-background: #1e1e1e;" +
                        "-fx-text-fill: #00ff00;" +
                        "-fx-font-family: 'Courier New';" +
                        "-fx-font-size: 13px;"
        );
        VBox.setVgrow(outputArea, Priority.ALWAYS);

        panel.getChildren().addAll(outputLabel, outputArea);
        return panel;
    }

    /**
     * Cria o painel de estatísticas
     */
    private VBox createStatsPanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(20));
        panel.setPrefWidth(250);
        panel.setStyle("-fx-background-color: #363636;");

        Label statsLabel = new Label("📈 Estatísticas");
        statsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        statsLabel.setTextFill(Color.WHITE);

        Label infoLabel = new Label(
                "💡 Propriedades AVL:\n\n" +
                        "✓ Balanceamento automático\n" +
                        "✓ |FB| ≤ 1 para todos os nós\n" +
                        "✓ Altura = O(log n)\n\n" +
                        "⚡ Complexidades:\n\n" +
                        "• Inserção: O(log n)\n" +
                        "• Busca: O(log n)\n" +
                        "• Remoção: O(log n)\n\n" +
                        "🎯 Total de missões:\n" +
                        "Será atualizado dinamicamente"
        );
        infoLabel.setTextFill(Color.web("#e0e0e0"));
        infoLabel.setWrapText(true);
        infoLabel.setFont(Font.font("Arial", 12));

        panel.getChildren().addAll(statsLabel, infoLabel);
        return panel;
    }

    /**
     * Mostra mensagem de boas-vindas
     */
    private void showWelcomeMessage() {
        outputArea.setText(
                "╔══════════════════════════════════════════════════════════════════╗\n" +
                        "║         BEM-VINDO AO GERENCIADOR DE MISSÕES RPG!                 ║\n" +
                        "║              Estrutura de Dados: Árvore AVL                      ║\n" +
                        "╚══════════════════════════════════════════════════════════════════╝\n\n" +
                        "🎮 Sistema pronto para uso!\n\n" +
                        "📝 Instruções:\n" +
                        "1. Use os botões à esquerda para interagir com o sistema\n" +
                        "2. Digite o nível (1-100) e descrição para adicionar missões\n" +
                        "3. Clique em 'Carregar Exemplos' para testar rapidamente\n" +
                        "4. A árvore se balanceia automaticamente após cada operação\n\n" +
                        "✨ Começe agora e explore a estrutura AVL!\n"
        );
    }

    /**
     * Adiciona uma nova missão
     */
    private void adicionarMissao() {
        try {
            String nivelText = nivelField.getText().trim();
            String missaoText = missaoField.getText().trim();

            if (nivelText.isEmpty() || missaoText.isEmpty()) {
                showError("Por favor, preencha o nível e a descrição da missão!");
                return;
            }

            int nivel = Integer.parseInt(nivelText);

            if (nivel < 1 || nivel > 100) {
                showError("Nível deve estar entre 1 e 100!");
                return;
            }

            arvore.inserir(nivel, missaoText);

            outputArea.setText(
                    "══════════════════════════════════════════════════════════\n" +
                            "✅ MISSÃO ADICIONADA COM SUCESSO!\n" +
                            "══════════════════════════════════════════════════════════\n\n" +
                            "📍 Nível: " + nivel + "\n" +
                            "📝 Missão: " + missaoText + "\n\n" +
                            "⚠️  Árvore balanceada automaticamente!\n" +
                            "📊 Total de missões: " + arvore.getTotalMissoes() + "\n"
            );

            nivelField.clear();
            missaoField.clear();

        } catch (NumberFormatException e) {
            showError("Nível deve ser um número inteiro!");
        }
    }

    /**
     * Busca uma missão por nível
     */
    private void buscarMissao() {
        try {
            String nivelText = nivelField.getText().trim();

            if (nivelText.isEmpty()) {
                showError("Digite o nível da missão para buscar!");
                return;
            }

            int nivel = Integer.parseInt(nivelText);
            String resultado = arvore.buscar(nivel);

            if (resultado != null) {
                outputArea.setText(
                        "══════════════════════════════════════════════════════════\n" +
                                "✅ MISSÃO ENCONTRADA!\n" +
                                "══════════════════════════════════════════════════════════\n\n" +
                                "🔍 Resultado: " + resultado + "\n\n" +
                                "⚡ Busca realizada em O(log n)\n"
                );
            } else {
                outputArea.setText(
                        "══════════════════════════════════════════════════════════\n" +
                                "❌ MISSÃO NÃO ENCONTRADA\n" +
                                "══════════════════════════════════════════════════════════\n\n" +
                                "🔍 Nenhuma missão encontrada no nível " + nivel + "\n\n" +
                                "💡 Dica: Use 'Listar Todas' para ver as missões disponíveis\n"
                );
            }

        } catch (NumberFormatException e) {
            showError("Nível deve ser um número inteiro!");
        }
    }

    /**
     * Remove uma missão
     */
    private void removerMissao() {
        try {
            String nivelText = nivelField.getText().trim();

            if (nivelText.isEmpty()) {
                showError("Digite o nível da missão para remover!");
                return;
            }

            int nivel = Integer.parseInt(nivelText);
            boolean removido = arvore.remover(nivel);

            if (removido) {
                outputArea.setText(
                        "══════════════════════════════════════════════════════════\n" +
                                "✅ MISSÃO REMOVIDA COM SUCESSO!\n" +
                                "══════════════════════════════════════════════════════════\n\n" +
                                "📍 Nível removido: " + nivel + "\n\n" +
                                "⚠️  Árvore rebalanceada automaticamente!\n" +
                                "📊 Total de missões: " + arvore.getTotalMissoes() + "\n"
                );
                nivelField.clear();
            } else {
                showError("Nenhuma missão encontrada no nível " + nivel);
            }

        } catch (NumberFormatException e) {
            showError("Nível deve ser um número inteiro!");
        }
    }

    /**
     * Lista todas as missões
     */
    private void listarMissoes() {
        if (arvore.estaVazia()) {
            outputArea.setText(
                    "══════════════════════════════════════════════════════════\n" +
                            "📋 LISTA DE MISSÕES\n" +
                            "══════════════════════════════════════════════════════════\n\n" +
                            "⚠️  Nenhuma missão cadastrada.\n\n" +
                            "💡 Dica: Use 'Carregar Exemplos' para testar o sistema!\n"
            );
            return;
        }

        outputArea.setText(
                "══════════════════════════════════════════════════════════\n" +
                        "📋 LISTA DE MISSÕES (Ordem Crescente)\n" +
                        "══════════════════════════════════════════════════════════\n\n" +
                        arvore.listarEmOrdem() + "\n" +
                        "──────────────────────────────────────────────────────────\n" +
                        "📊 Total: " + arvore.getTotalMissoes() + " missões cadastradas\n"
        );
    }

    /**
     * Visualiza a estrutura da árvore
     */
    private void visualizarArvore() {
        if (arvore.estaVazia()) {
            outputArea.setText(
                    "══════════════════════════════════════════════════════════\n" +
                            "🌳 ESTRUTURA DA ÁRVORE AVL\n" +
                            "══════════════════════════════════════════════════════════\n\n" +
                            "⚠️  Árvore vazia.\n\n" +
                            "💡 Adicione missões para visualizar a estrutura!\n"
            );
            return;
        }

        outputArea.setText(
                "══════════════════════════════════════════════════════════\n" +
                        "🌳 ESTRUTURA DA ÁRVORE AVL\n" +
                        "══════════════════════════════════════════════════════════\n" +
                        "Legenda: Nv=Nível | h=Altura | FB=Fator de Balanceamento\n" +
                        "──────────────────────────────────────────────────────────\n\n" +
                        arvore.visualizarArvore() + "\n" +
                        "──────────────────────────────────────────────────────────\n" +
                        "✅ Propriedade AVL mantida: |FB| ≤ 1 para todos os nós\n" +
                        "📊 Total de missões: " + arvore.getTotalMissoes() + "\n"
        );
    }

    /**
     * Carrega missões de exemplo
     */
    private void carregarExemplos() {
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

        outputArea.setText(
                "══════════════════════════════════════════════════════════\n" +
                        "✅ MISSÕES DE EXEMPLO CARREGADAS!\n" +
                        "══════════════════════════════════════════════════════════\n\n" +
                        "🎮 " + missoes.length + " missões foram adicionadas com sucesso!\n\n" +
                        "⚠️  A árvore foi balanceada automaticamente durante as inserções.\n\n" +
                        "💡 Experimente:\n" +
                        "   • Visualizar Árvore → Para ver a estrutura balanceada\n" +
                        "   • Listar Todas → Para ver as missões em ordem\n" +
                        "   • Buscar/Remover → Para testar outras operações\n\n" +
                        "📊 Total de missões: " + arvore.getTotalMissoes() + "\n"
        );
    }

    /**
     * Limpa todas as missões
     */
    private void limparTudo() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Limpeza");
        alert.setHeaderText("Limpar todas as missões?");
        alert.setContentText("Esta ação não pode ser desfeita!");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                arvore.limpar();
                outputArea.setText(
                        "══════════════════════════════════════════════════════════\n" +
                                "✅ TODAS AS MISSÕES FORAM REMOVIDAS!\n" +
                                "══════════════════════════════════════════════════════════\n\n" +
                                "🗑️  A árvore foi limpa com sucesso.\n\n" +
                                "💡 Use 'Carregar Exemplos' ou adicione novas missões!\n"
                );
            }
        });
    }

    /**
     * Mostra mensagem de erro
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Método main
     */
    public static void main(String[] args) {
        launch(args);
    }
}