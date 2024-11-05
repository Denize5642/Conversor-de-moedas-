import java.util.Scanner;

public class Converter {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            int opcao = lerValorValido();

            if (opcao == 7) {
                continuar = false;
                System.out.println("Obrigado por usar o conversor de moedas!");
            } else if (opcao >= 1 && opcao <= 6) {
                String[] moedas = obterMoedasParaConversao(opcao);
                ConversorMoedas conversor = new ConversorMoedas();
                conversor.converter(moedas[0], moedas[1]);
            } else {
                System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- Conversor de Moedas ---");
        System.out.println("1. Dólar para Peso Argentino");
        System.out.println("2. Peso Argentino para Dólar");
        System.out.println("3. Dólar para Real Brasileiro");
        System.out.println("4. Peso Colombiano para Dólar");
        System.out.println("5. Euro para Dólar");
        System.out.println("6. Yuan Chinês para Dólar");
        System.out.println("7. Sair");
        System.out.println("Escolha uma opção: ");
    }

    private static int lerValorValido() {
        while (!scanner.hasNextInt()) {

            if (scanner.hasNext()) {
                scanner.next();
                System.out.println("Por favor, digite um número válido.");
            }
        }
        return scanner.nextInt();
    }

    private static String[] obterMoedasParaConversao(int opcao) {
        try {
            return switch (opcao) {
                case 1 -> new String[]{"USD", "ARS"};
                case 2 -> new String[]{"ARS", "USD"};
                case 3 -> new String[]{"USD", "BRL"};
                case 4 -> new String[]{"COP", "USD"};
                case 5 -> new String[]{"EUR", "USD"};
                case 6 -> new String[]{"CNY", "USD"};
                default -> throw new IllegalAccessException("Opção inválida!");
            };
        } catch (IllegalAccessException e) {
            System.out.println("Erro: Opção inválida! " + e.getMessage());
            return null;

        }
    }
}

