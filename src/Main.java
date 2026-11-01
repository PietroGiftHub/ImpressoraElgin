import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Usuario u1 = new Usuario("admin", "1234");
        boolean logado = false;

        for(int tentativa = 1; tentativa <= 3; tentativa++){

            System.out.println("Digite seu usuário: ");
            String loginDigitado = teclado.nextLine();

            System.out.println("Digite sua senha: ");
            String senhaDigitada = teclado.nextLine();

            if(u1.verificar(loginDigitado, senhaDigitada)){
                System.out.println("Bem-vindo!");
                logado = true;
                break;
            } else {
                System.out.println("Login ou senha incorretos.");
                System.out.println("Tentativas restantes: " + (3 - tentativa));
            }

        }

        if(!logado){
            System.out.println("Número máximo de tentativas atingido. Encerrando...");
            return;
        } else {

            int opcao = -1;

            do {
                System.out.println("\n==============================");
                System.out.println("       IMPRESSORA ELGIN");
                System.out.println("==============================\n");

                System.out.println("1 - Configurar conexão");
                System.out.println("2 - Abrir conexão");
                System.out.println("3 - Fechar conexão");
                System.out.println("4 - Imprimir texto");
                System.out.println("5 - Imprimir QR Code");
                System.out.println("6 - Imprimir código de barras");
                System.out.println("7 - Imprimir XML SAT");
                System.out.println("8 - Imprimir XML de cancelamento SAT");
                System.out.println("9 - Abrir gaveta");
                System.out.println("10 - Sinal sonoro (bip)");
                System.out.println("0 - SAIR");

                System.out.println("\n==============================");

                try {
                    System.out.print("Digite a opção: ");
                    opcao = teclado.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println("Configurar conexão");
                            break;

                        case 2:
                            System.out.println("Abrir conexão");
                            break;

                        case 3:
                            System.out.println("Fechar conexão");
                            break;

                        case 4:
                            System.out.println("Imprimir texto");
                            break;

                        case 5:
                            System.out.println("Imprimir QR Code");
                            break;

                        case 6:
                            System.out.println("Imprimir código de barras");
                            break;

                        case 7:
                            System.out.println("Imprimir XML SAT");
                            break;

                        case 8:
                            System.out.println("Imprimir XML de cancelamento SAT");
                            break;

                        case 9:
                            System.out.println("Abrir gaveta");
                            break;

                        case 10:
                            System.out.println("Sinal sonoro (bip)");
                            break;

                        case 0:
                            System.out.println("Encerrando o programa...");
                            break;

                        default:
                            System.out.println("Opção inválida. Escolha uma opção de 0 a 10.");
                            break;
                    }
                } catch (InputMismatchException e){
                    System.out.println("Entrada inválida. Digite números, não digite por extenso.");
                    teclado.nextLine();
                    opcao = -1;
                }

            } while (opcao != 0);

        }

    }
}
