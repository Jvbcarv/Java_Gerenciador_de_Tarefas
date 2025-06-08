package alura;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class GerenciadorTarefas {
    // Vetor para tarefas prioritárias (tamanho fixo 10)
    static String[] tarefasPrioritarias = new String[10];
    static int countPrioritarias = 0;

    // Lista para tarefas normais
    static ArrayList<String> tarefasNormais = new ArrayList<>();

    // Pilha para tarefas pendentes
    static Stack<String> tarefasPendentes = new Stack<>();

    // Fila para tarefas em espera
    static Queue<String> tarefasEspera = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            mostrarMenu();
            System.out.print("Opcao: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consumir enter

            switch (opcao) {
                case 1 -> adicionarTarefaPrioritaria(sc);
                case 2 -> listarTarefasPrioritarias();
                case 3 -> adicionarTarefaNormal(sc);
                case 4 -> removerTarefaNormal(sc);
                case 5 -> listarTarefasNormais();
                case 6 -> adicionarTarefaPendente(sc);
                case 7 -> removerTarefaPendente();
                case 8 -> mostrarTarefaAtualPilha();
                case 9 -> adicionarTarefaEspera(sc);
                case 10 -> processarTarefaFila();
                case 11 -> mostrarProximaTarefaFila();
                case 0 -> System.out.println("Saindo do programa. Ate mais!");
                default -> System.out.println("Opcao inválida. Tente novamente.");
            }
            System.out.println("---");
        } while (opcao != 0);

        sc.close();
    }

    static void mostrarMenu() {
        System.out.println("=== Gerenciador de Tarefas ===");
        System.out.println("1 - Adicionar tarefa prioritaria");
        System.out.println("2 - Listar tarefas prioritarias");
        System.out.println("3 - Adicionar tarefa normal");
        System.out.println("4 - Remover tarefa normal");
        System.out.println("5 - Listar tarefas normais");
        System.out.println("6 - Adicionar tarefa pendente (pilha)");
        System.out.println("7 - Remover ultima tarefa pendente (pilha)");
        System.out.println("8 - Mostrar tarefa atual da pilha");
        System.out.println("9 - Adicionar tarefa em espera (fila)");
        System.out.println("10 - Processar proxima tarefa da fila");
        System.out.println("11 - Mostrar proxima tarefa da fila");
        System.out.println("0 - Sair");
    }

    // Vetor - Tarefas Prioritárias
    static void adicionarTarefaPrioritaria(Scanner sc) {
        if (countPrioritarias >= tarefasPrioritarias.length) {
            System.out.println("Vetor de tarefas prioritarias está cheio!");
            return;
        }
        System.out.print("Digite a tarefa prioritaria: ");
        String tarefa = sc.nextLine();
        tarefasPrioritarias[countPrioritarias] = tarefa;
        countPrioritarias++;
        System.out.println("Tarefa adicionada com sucesso!");
    }

    static void listarTarefasPrioritarias() {
        if (countPrioritarias == 0) {
            System.out.println("Nenhuma tarefa prioritaria cadastrada.");
            return;
        }
        System.out.println("Tarefas prioritarias:");
        for (int i = 0; i < countPrioritarias; i++) {
            System.out.printf("%d - %s%n", i + 1, tarefasPrioritarias[i]);
        }
    }

    // Lista - Tarefas Normais
    static void adicionarTarefaNormal(Scanner sc) {
        System.out.print("Digite a tarefa normal: ");
        String tarefa = sc.nextLine();
        tarefasNormais.add(tarefa);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    static void removerTarefaNormal(Scanner sc) {
        if (tarefasNormais.isEmpty()) {
            System.out.println("Não há tarefas normais para remover.");
            return;
        }
        System.out.print("Digite o nome da tarefa normal a remover: ");
        String tarefa = sc.nextLine();
        if (tarefasNormais.remove(tarefa)) {
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Tarefa não encontrada na lista.");
        }
    }

    static void listarTarefasNormais() {
        if (tarefasNormais.isEmpty()) {
            System.out.println("Nenhuma tarefa normal cadastrada.");
            return;
        }
        System.out.println("Tarefas normais:");
        for (int i = 0; i < tarefasNormais.size(); i++) {
            System.out.printf("%d - %s%n", i + 1, tarefasNormais.get(i));
        }
    }

    // Pilha - Tarefas Pendentes
    static void adicionarTarefaPendente(Scanner sc) {
        System.out.print("Digite a tarefa pendente para revisao: ");
        String tarefa = sc.nextLine();
        tarefasPendentes.push(tarefa);
        System.out.println("Tarefa adicionada na pilha!");
    }

    static void removerTarefaPendente() {
        if (tarefasPendentes.isEmpty()) {
            System.out.println("Pilha de tarefas pendentes esta vazia.");
            return;
        }
        String removida = tarefasPendentes.pop();
        System.out.println("Tarefa removida da pilha: " + removida);
    }

    static void mostrarTarefaAtualPilha() {
        if (tarefasPendentes.isEmpty()) {
            System.out.println("Pilha de tarefas pendentes esta vazia.");
            return;
        }
        System.out.println("Tarefa atual na pilha: " + tarefasPendentes.peek());
    }

    // Fila - Tarefas em Espera
    static void adicionarTarefaEspera(Scanner sc) {
        System.out.print("Digite a tarefa em espera: ");
        String tarefa = sc.nextLine();
        tarefasEspera.offer(tarefa);
        System.out.println("Tarefa adicionada na fila!");
    }

    static void processarTarefaFila() {
        if (tarefasEspera.isEmpty()) {
            System.out.println("Fila de tarefas em espera esta vazia.");
            return;
        }
        String processada = tarefasEspera.poll();
        System.out.println("Processando tarefa: " + processada);
    }

    static void mostrarProximaTarefaFila() {
        if (tarefasEspera.isEmpty()) {
            System.out.println("Fila de tarefas em espera esta vazia.");
            return;
        }
        System.out.println("Proxima tarefa na fila: " + tarefasEspera.peek());
    }
}