import java.util.InputMismatchException;
import java.util.Scanner;

public class Impressora {

    public int tipo;
    public String modelo;
    public String conexao;
    public int parametro;
    public boolean conexaoAberta = false;
    public boolean verificarConexao = false;


    public void configurarConexao(Scanner teclado){
        verificarConexao = false;

        try {
            System.out.println("Qual o tipo de conexão?\n " +
                    "1 - USB\n" +
                    " 2 - Serial(RS232)\n" +
                    " 3 - Rede (TCP/IP)\n" +
                    " 4 - Bluetooth\n");
            tipo = teclado.nextInt();

            if(tipo != 1 && tipo != 2 && tipo != 3 && tipo != 4){
                System.out.println("Tipo invalido");
                return;
            } else {
                System.out.println("Tipo: " + tipo + " valido!");
            }

            teclado.nextLine();

            System.out.println("Qual o modelo da impressora? (i8/i9) ");
            modelo = teclado.nextLine();

            if(modelo.equalsIgnoreCase("i8") || modelo.equalsIgnoreCase("i9")){
                System.out.println("Modelo valido");
            } else {
                System.out.println("Modelo invalido");
                return;
            }

            System.out.println("Qual a conexão? ");
            conexao = teclado.nextLine();

            if(conexao.isBlank()){
                System.out.println("Conexão invalida ou vazia");
                return;
            } else {
                System.out.println("Conexão valida");
            }

            System.out.println("Digite o parametro da conexão: ");
            parametro = teclado.nextInt();

            if (tipo == 1) {
                if (parametro != 0) {
                    System.out.println("Parametro invalido para USB");
                    return;
                } else {
                    System.out.println("Parametro USB valido");
                }

            } else if (tipo == 2) {
                if (parametro <= 0) {
                    System.out.println("Velocidade invalida para Serial");
                    return;
                } else {
                    System.out.println("Parametro Serial valido");
                }

            } else if (tipo == 3) {
                if (parametro <= 0) {
                    System.out.println("Porta invalida para Rede");
                    return;
                } else {
                    System.out.println("Parametro Rede valido");
                }

            } else if (tipo == 4) {
                System.out.println("Validacao do Bluetooth ainda nao definida");
            }

            verificarConexao = true;

        } catch (InputMismatchException e) {
            teclado.nextLine();
            System.out.println("Entrada invalida. Digite números");
        }

    }

    public void abrirConexao(){
        try {
            if(!verificarConexao){
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            } else {
                System.out.println("Abrindo conexão...");
                if(!conexaoAberta){
                    int resultado = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);
                    System.out.println("Retorno da DLL: " + resultado);
                    if(resultado == 0){
                        System.out.println("Conexão aberta");
                        conexaoAberta = true;
                    } else {
                        System.out.println("Conexão fechada");
                        conexaoAberta = false;
                    }
                }
            }
        } catch (UnsatisfiedLinkError e){
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void fecharConexao(){
        try {
            if(!verificarConexao){
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            } else {
                if(!conexaoAberta){
                    System.out.println("A conexão já está fechada no sistema.");
                } else{
                    int resultado = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
                    System.out.println("Retorno da DLL (Fechar): " + resultado);
                    if(resultado == 0){
                        System.out.println("Conexão FECHADA com sucesso!");
                        conexaoAberta = false;
                    } else {
                        System.out.println("Falha ao fechar a conexão. A porta continua ocupada ou ativa.");
                        conexaoAberta = true;
                    }
                }
            }
        } catch (UnsatisfiedLinkError e){
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }


}
