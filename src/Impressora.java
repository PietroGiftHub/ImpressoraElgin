import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;


public class Impressora {

    public int tipo;
    public String modelo;
    public String conexao;
    public int parametro;
    public boolean conexaoAberta = false;
    public boolean verificarConexao = false;


    public void configurarConexao(Scanner teclado) {
        verificarConexao = false;

        try {
            System.out.println("Qual o tipo de conexão?\n " +
                    "1 - USB\n" +
                    " 2 - Serial(RS232)\n" +
                    " 3 - Rede (TCP/IP)\n" +
                    " 4 - Bluetooth\n");
            tipo = teclado.nextInt();

            if (tipo != 1 && tipo != 2 && tipo != 3 && tipo != 4) {
                System.out.println("Tipo invalido");
                return;
            } else {
                System.out.println("Tipo: " + tipo + " valido!");
            }

            teclado.nextLine();

            System.out.println("Qual o modelo da impressora? (i8/i9) ");
            modelo = teclado.nextLine();

            if (modelo.equalsIgnoreCase("i8") || modelo.equalsIgnoreCase("i9")) {
                System.out.println("Modelo valido");
            } else {
                System.out.println("Modelo invalido");
                return;
            }

            System.out.println("Qual a conexão? ");
            conexao = teclado.nextLine();

            if (conexao.isBlank()) {
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

    public void abrirConexao() {
        try {
            if (!verificarConexao) {
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            } else {
                System.out.println("Abrindo conexão...");
                if (!conexaoAberta) {
                    int resultado = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);
                    System.out.println("Retorno da DLL: " + resultado);
                    if (resultado == 0) {
                        System.out.println("Conexão aberta");
                        conexaoAberta = true;
                    } else {
                        System.out.println("Conexão fechada");
                        //conexaoAberta = false;
                    }
                }
            }
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void fecharConexao() {
        try {
            if (!verificarConexao) {
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            } else {
                if (!conexaoAberta) {
                    System.out.println("A conexão já está fechada no sistema.");
                } else {
                    System.out.println("Fechando conexão...");
                    int resultado = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
                    System.out.println("Retorno da DLL (Fechar): " + resultado);
                    if (resultado == 0) {
                        System.out.println("Conexão FECHADA com sucesso!");
                        conexaoAberta = false;
                    } else {
                        System.out.println("Falha ao fechar a conexão. A porta continua ocupada ou ativa.");
                        conexaoAberta = true;
                    }
                }
            }
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void impressaoTexto(Scanner teclado) {
        try {
            if (!verificarConexao) {
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            }

            if (conexaoAberta) {
                teclado.nextLine();

                System.out.println("Digite o texto a ser impresso: ");
                String texto = teclado.nextLine();

                if(texto.isBlank()){
                    System.out.println("Texto vazio. Digite algo....");
                    return;
                }

                int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(texto, 1, 0, 50);
                if (retorno > 0) {
                    System.out.println("Texto enviado pra impressão com sucesso!");

                    int retornoCorte = ImpressoraDLL.INSTANCE.Corte(5);

                    if (retornoCorte == 0) {
                        System.out.println("Papel cortado com sucesso!");
                    } else {
                        System.out.println("Erro ao cortar o papel: " + retornoCorte);
                    }

                } else {
                    System.out.println("Erro na impressão: " + retorno);
                }
            } else {
                System.out.println("Conexão fechada. Configure corretamente na opção 2 do menu");
                return;
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void impressaoQRCode(Scanner teclado){
        try {
            if (!verificarConexao) {
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            }

            if (conexaoAberta) {
                teclado.nextLine();

                System.out.println("Digite o texto do QRcode: ");
                String texto = teclado.nextLine();

                if(texto.isBlank()){
                    System.out.println("Texto vazio. Digite algo....");
                    return;
                }

                int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(texto, 6, 4);
                if (retorno == 0) {
                    System.out.println("QR code impresso com sucesso");

                    int retornoCorte = ImpressoraDLL.INSTANCE.Corte(5);

                    if (retornoCorte == 0) {
                        System.out.println("Papel cortado com sucesso!");
                    } else {
                        System.out.println("Erro ao cortar o papel: " + retornoCorte);
                    }

                } else {
                    System.out.println("Erro na impressão: " + retorno);
                }
            } else {
                System.out.println("Conexão fechada. Configure corretamente na opção 2 do menu");
                return;
            }

        } catch (UnsatisfiedLinkError e){
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void impressaoCodigoBarras(Scanner teclado){
        try {
            if (!verificarConexao) {
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            }

            if (conexaoAberta) {
                teclado.nextLine();

                System.out.println("Digite o texto do código de barras: ");
                String texto = teclado.nextLine();

                if(texto.isBlank()){
                    System.out.println("Texto vazio. Digite algo....");
                    return;
                }

                int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, "{A" +  texto, 100, 3, 2);
                if (retorno == 0) {
                    System.out.println("Codígo de barras impresso com sucesso");

                    int retornoCorte = ImpressoraDLL.INSTANCE.Corte(5);

                    if (retornoCorte == 0) {
                        System.out.println("Papel cortado com sucesso!");
                    } else {
                        System.out.println("Erro ao cortar o papel: " + retornoCorte);
                    }

                } else {
                    System.out.println("Erro na impressão: " + retorno);
                }
            } else {
                System.out.println("Conexão fechada. Configure corretamente na opção 2 do menu");
                return;
            }

        } catch (UnsatisfiedLinkError e){
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void imprimeXMLSAT(){
        try {
            if (!verificarConexao) {
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            }

            if (conexaoAberta){
                File arquivo = new File("XMLSAT.xml");

                if(arquivo.exists()){
                    System.out.println("Arquivo encontrado");

                    String dados = "path=" + arquivo.getAbsolutePath();
                    int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(dados, 0);

                    if (retorno == 0) {
                        System.out.println("Arquivo XMLSAT impresso com sucesso!");

                        int retornoCorte = ImpressoraDLL.INSTANCE.Corte(5);

                        if (retornoCorte == 0) {
                            System.out.println("Papel cortado com sucesso!");
                        } else {
                            System.out.println("Erro ao cortar o papel: " + retornoCorte);
                        }

                    } else {
                        System.out.println("Erro na impressão: " + retorno);
                    }

                } else {
                    System.out.println("Arquivo XML não encontrado!");
                }
            } else {
                System.out.println("Conexão fechada. Configure corretamente na opção 2 do menu");
                return;
            }

        } catch (UnsatisfiedLinkError e){
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void imprimeXMLCancelamentoSAT(){
        try {
            if (!verificarConexao) {
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            }

            if (conexaoAberta){
                File arquivo = new File("CANC_SAT.xml");

                if(arquivo.exists()){
                    System.out.println("Arquivo encontrado");

                    String dados = "path=" + arquivo.getAbsolutePath();
                    String assQRCode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0" +
                            "E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQ" +
                            "sE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+Z" +
                            "fO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";

                    int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(dados, assQRCode, 0);

                    if (retorno == 0) {
                        System.out.println("Arquivo CANC_SAT impresso com sucesso!");

                        int retornoCorte = ImpressoraDLL.INSTANCE.Corte(5);

                        if (retornoCorte == 0) {
                            System.out.println("Papel cortado com sucesso!");
                        } else {
                            System.out.println("Erro ao cortar o papel: " + retornoCorte);
                        }

                    } else {
                        System.out.println("Erro na impressão: " + retorno);
                    }

                } else {
                    System.out.println("Arquivo XML não encontrado!");
                }
            } else {
                System.out.println("Conexão fechada. Configure corretamente na opção 2 do menu");
                return;
            }

        } catch (UnsatisfiedLinkError e){
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void abreGaveta(){
        try {
            if (!verificarConexao){
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            }

            if (conexaoAberta){
                System.out.println("Enviando comando para abrir gaveta...");

                int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(1, 5, 0);

                if (retorno == 0){
                    System.out.println("Comando de abertura de gaveta enviado com sucesso!");
                } else {
                    System.out.println("Erro ao abrir a gaveta: " + retorno);
                }
            } else {
                System.out.println("Conexão fechada. Abra a conexão na opção 2 do menu primeiro.");
                return;
            }
        } catch (UnsatisfiedLinkError e){
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void sinalSonoro(Scanner teclado){
        try {
            if (!verificarConexao){
                System.out.println("Conexão não foi configurada! Por favor preencha os dados solicitados na opção 1 do menu.");
                return;
            }

            if (conexaoAberta){
                teclado.nextLine();

                System.out.println("Quantas vezes você quer tocar o sinal? (max 5) ");
                int qtd = teclado.nextInt();

                if(qtd > 5 || qtd <= 0){
                    System.out.println("Quantidade inválida. Digite um número de 1 a 5.");
                } else {
                    int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(qtd, 50, 5);

                    if (retorno == 0){
                        System.out.println("Som ATIVADO");
                    } else {
                        System.out.println("Erro ao ativar o som: " + retorno);
                    }
                }

            } else {
                System.out.println("Conexão fechada. Abra a conexão na opção 2 do menu primeiro.");
                return;
            }
        } catch (InputMismatchException e){
            teclado.nextLine();
            System.out.println("Entrada inválida. Digite apenas números inteiros.");
        } catch (UnsatisfiedLinkError e){
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }
}
