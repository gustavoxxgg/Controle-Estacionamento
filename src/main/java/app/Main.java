package app;

import classes.Acesso;
import classes.AcessoMensalista;
import classes.SistemaEstacionamento;
import classes.Proprietario;
import classes.Veiculo;
import classes.VeiculoMensalista;
import exceptions.DadosPessoaisIncompletosException;
import exceptions.DadosVeiculosIncompletosException;
import exceptions.EstacionamentoFechadoException;
import exceptions.PeriodoInvalidoException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;


/**
 * <p>
 * Classe <b>Main</b> Recebe o método main que é o runner de toda a
 * aplicação</p>
 *
 * @author Lucas Ramon
 * @since may 2021
 * @version 1.0
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static SistemaEstacionamento sisEstacionamento = new SistemaEstacionamento();
    static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
        menu();
      
    
    }

    public Main(SistemaEstacionamento sistema) {
        sisEstacionamento = sistema;
    }

    public static Veiculo processaCadastroRotativo(String marca, String modelo, String placa) throws DadosVeiculosIncompletosException {
        Veiculo veiculo = new Veiculo(marca, modelo, placa);
        if (sisEstacionamento.buscarVeiculo(placa) == null) {
            sisEstacionamento.cadastrarVeiculo(veiculo);
            return veiculo;
        } else {
            throw new DadosVeiculosIncompletosException("Veículo já cadastrado!");
        }
    }

    private static void menuCadastrarRotativo() {
        try {
            System.out.println("Digite a marca do veículo:");
            String strMarca = teclado.nextLine();

            System.out.println("Digite o modelo do veículo:");
            String strModelo = teclado.nextLine();

            System.out.println("Digite a placa do veículo:");
            String strPlaca = teclado.nextLine();

            Veiculo veiculo = processaCadastroRotativo(strMarca, strModelo, strPlaca);
            System.out.println("Veículo cadastrado com sucesso: " + veiculo);
        } catch (DadosVeiculosIncompletosException erroDadosVeiculosIncompleto) {
            System.out.println(erroDadosVeiculosIncompleto.getMessage());
        }
    }

    private static void menuCadastrarProprietario() {
        Proprietario p = new Proprietario();
        try {
            System.out.println("Digite o nome do proprietário:");
            String strNome = teclado.nextLine();
            p.setNome(strNome);

            System.out.println("Digite o número da CNH do proprietário:");
            String strCnh = teclado.nextLine();
            p.setCnh(strCnh);

            System.out.println("Digite o endereço do proprietário:");
            String strEndereco = teclado.nextLine();
            p.setEndereco(strEndereco);

            System.out.println("Digite o número de telefone celular do proprietário:");
            String strNcelular = teclado.nextLine();
            p.setnCelular(strNcelular);

            System.out.println("Digite o numero do telefone residencial do proprietário:");
            String strNresidencial = teclado.nextLine();
            p.setnResidencial(strNresidencial);
            System.out.println(strCnh);

            if (sisEstacionamento.buscarProprietario(strCnh) == null) {
            	sisEstacionamento.cadastrarProprietario(p);
            } else {
                System.out.println("Proprietário já cadastrado!");
            }

        } catch (DadosPessoaisIncompletosException erroDadosProprietarioIncompleto) {
            System.out.println(erroDadosProprietarioIncompleto.getMessage());
            System.out.println("Proprietário com dados pessoais incompletos");
        }
    }
    private static void menuCadastrarMensalista() {
    	

        /*
        Criando um novo registro de veiculo mensalista
        1-Instaciar um novo veiculo mensalista;
        2-Setar tudo referente ao veiculo;
        3-Se o veiculo já estiver registrado,lançar a exception veiculo já cadastrado;
        4-Adicionar veiculo a lista;
        5-Criar um objeto Proprietario;
        6-Setar tudo do proprietario e adicionar vincular o veiculo
        */
       
      
        try {

        Veiculo vm = new VeiculoMensalista();

        System.out.println("Digite a marca do veículo:");
        String strMarcaMensalista = teclado.nextLine();
        vm.setMarca(strMarcaMensalista);

        System.out.println("Digite o modelo do veículo:");
        String strModeloMensalista = teclado.nextLine();
        vm.setModelo(strModeloMensalista);

        System.out.println("Digite a placa do veículo:");
        String strPlacaMensalista = teclado.nextLine();
        vm.setNumeroPlaca(strPlacaMensalista);
        
        System.out.println("Digite o número da CNH do proprietário:");
        String strCnhMensalista = teclado.nextLine();

        Proprietario proprietario = sisEstacionamento.buscarProprietario(strCnhMensalista);

        if (proprietario == null) {
            System.out.println("Proprietário não cadastrado!");
        }
        else {
            if (sisEstacionamento.buscarVeiculo(strPlacaMensalista) == null) {
                sisEstacionamento.cadastrarVeiculo(vm);
            } else {
                 System.out.println( "Veículo já cadastrado!");
            }
        }
        } catch (DadosVeiculosIncompletosException erroDadosVeiculosIncompleto) {
        	System.out.println(erroDadosVeiculosIncompleto.getMessage());
        	 System.out.println( "Veículo com dados incompletos");
        }
    }
    private static void menuAcessoRotativo() {
        /*
        1-Buscar a referencia de veiculo pela placa;
        2-Setar a hora e data de entrada do veiculo;
        3-Verificar o tipo de acesso;
        4-Realizar o cálculo do valor a ser pago;
        5-adicionar o acesso a lista de acessos;
        6-Adicionar o custo do acesso a lista de faturamento;
         */
        Veiculo comparacao = new Veiculo(); 
        System.out.println("Digite a placa do veículo:");
        String strPlacaRotativo = teclado.nextLine();
        Veiculo veiculoRotativo = sisEstacionamento.buscarVeiculo(strPlacaRotativo);
        if (veiculoRotativo == null) {
             System.out.println( "Veículo não cadastrado!");
        }
        else {
            if(veiculoRotativo.getClass()!= comparacao.getClass()){
                 System.out.println( "Não é possivel acessar como usuário rotativo: Veículo cadastrado como mensalista!");
            }
            else {
                try {
                    System.out.println( "Digite a data da entrada:\n(Use o formato DD/MM/AA)");
                    String strDataEntradaRotativo = teclado.nextLine();
                    int[] inputDataEntradaRotativo = GerenciamentoEstacionamento.lerData(strDataEntradaRotativo);
                    LocalDate dataEntradaRotativo = LocalDate.of(inputDataEntradaRotativo[2], inputDataEntradaRotativo[1], inputDataEntradaRotativo[0]);

                    System.out.println("Digite a hora da entrada:\n(Use o formato HH:MM)");
                    String strHoraEntradaRotativo = teclado.nextLine();
                    int[] inputHoraEntradaRotativo = GerenciamentoEstacionamento.lerHora(strHoraEntradaRotativo);
                    LocalTime horaEntradaRotativo = LocalTime.of(inputHoraEntradaRotativo[0], inputHoraEntradaRotativo[1]);

                    System.out.println("Digite a data da saída:\n(Use o formato DD/MM/AA)");
                    String strDataSaidaRotativo = teclado.nextLine();
                    int[] inputDataSaidaRotativo = GerenciamentoEstacionamento.lerData(strDataSaidaRotativo);
                    LocalDate dataSaidaRotativo = LocalDate.of(inputDataSaidaRotativo[2], inputDataSaidaRotativo[1], inputDataSaidaRotativo[0]);

                    System.out.println("Digite a hora da saída:\n(Use o formato HH:MM)");
                    String strHoraSaidaRotativo = teclado.nextLine();
                    int[] inputHoraSaidaRotativo = GerenciamentoEstacionamento.lerHora(strHoraSaidaRotativo);
                    LocalTime horaSaidaRotativo = LocalTime.of(inputHoraSaidaRotativo[0], inputHoraSaidaRotativo[1]);

                    LocalDateTime entradaRotativo = LocalDateTime.of(dataEntradaRotativo, horaEntradaRotativo);
                    LocalDateTime saidaRotativo = LocalDateTime.of(dataSaidaRotativo, horaSaidaRotativo);

                    Acesso acessoRotativo = GerenciamentoEstacionamento.classificaAcesso(entradaRotativo, saidaRotativo);
                    sisEstacionamento.cadastrarAcesso(acessoRotativo);
                    veiculoRotativo.setAcesso(acessoRotativo);
                } catch (NullPointerException nullPointerException) {
                    System.out.println( "ERROR!.");
                } catch (NumberFormatException numberFormatException) {
                    System.out.println( "Formato ilegal para data ou hora.");
                }
            }
        }
    }

    public static Acesso criarAcessoMensalista(String strDataEntrada, String strHoraEntrada, String strDataSaida, String strHoraSaida) throws PeriodoInvalidoException, EstacionamentoFechadoException {
        int[] inputDataEntrada = GerenciamentoEstacionamento.lerData(strDataEntrada);
        LocalDate dataEntrada = LocalDate.of(inputDataEntrada[2], inputDataEntrada[1], inputDataEntrada[0]);
    
        int[] inputHoraEntrada = GerenciamentoEstacionamento.lerHora(strHoraEntrada);
        LocalTime horaEntrada = LocalTime.of(inputHoraEntrada[0], inputHoraEntrada[1]);
    
        int[] inputDataSaida = GerenciamentoEstacionamento.lerData(strDataSaida);
        LocalDate dataSaida = LocalDate.of(inputDataSaida[2], inputDataSaida[1], inputDataSaida[0]);
    
        int[] inputHoraSaida = GerenciamentoEstacionamento.lerHora(strHoraSaida);
        LocalTime horaSaida = LocalTime.of(inputHoraSaida[0], inputHoraSaida[1]);
    
        Acesso acesso = new AcessoMensalista();
        acesso.setEntrada(dataEntrada, horaEntrada);
        acesso.setSaida(dataSaida, horaSaida);
        acesso.calculaDuracao();
        acesso.caculaPeriodo();
    
        return acesso;
    }
    

    private static void menuAcessoMensalista() {
        System.out.println("Digite a placa do veículo:");
        String strPlaca = teclado.nextLine();
        Veiculo vm = sisEstacionamento.buscarVeiculo(strPlaca);
    
        if (vm == null) {
            System.out.println("Veículo não cadastrado!");
            return;
        }
    
        if (!(vm instanceof VeiculoMensalista)) {
            System.out.println("O veículo da placa número: " + strPlaca + " não tem o plano de mensalista");
            return;
        }
    
        try {
            System.out.println("Digite a data da entrada (Use o formato DD/MM/AA):");
            String strDataEntrada = teclado.nextLine();
            System.out.println("Digite a hora da entrada (Use o formato HH:MM):");
            String strHoraEntrada = teclado.nextLine();
            System.out.println("Digite a data da saída (Use o formato DD/MM/AA):");
            String strDataSaida = teclado.nextLine();
            System.out.println("Digite a hora da saída (Use o formato HH:MM):");
            String strHoraSaida = teclado.nextLine();
    
            Acesso acesso = criarAcessoMensalista(strDataEntrada, strHoraEntrada, strDataSaida, strHoraSaida);
            vm.setAcesso(acesso);
            sisEstacionamento.cadastrarAcesso(acesso);
    
        } catch (PeriodoInvalidoException | EstacionamentoFechadoException | NullPointerException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void menu() {
        int escolha;
        do {
            escolha = obterEscolhaMenu();

            switch (escolha) {
                case 1:
                	menuCadastrarRotativo();
                    break;
                case 2:
                	menuCadastrarProprietario();
                    break;
                case 3:
                	menuCadastrarMensalista();
                	break;
                case 4:
                	menuAcessoRotativo();
                    break;
                case 5:
                	menuAcessoMensalista();
                    break;
                case 6:
                	sisEstacionamento.listarVeiculos();
                    break;
                case 7:
                	sisEstacionamento.listarProprietarios();
                    break;
                case 8:
                	sisEstacionamento.listarAcessos();
                    break;
                case 9:
                    exibeFaturamento();
                    break;
                case 0:
                    exibirMensagemSaida();
                    escolha = 0;
                    break;
                default:
                    System.out.println( "Opção Inválida! Tente novamente ou digite 0 para sair.");
                }
                
        } while (escolha != 0);
    }

    private static int obterEscolhaMenu() {
        exibirOpcoesMenu();
        while (true) {
            try{
                System.out.println("Digite a opção desejada:");
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Opção inválida! Tente novamente.");
            }
            
        }
    }

    private static void exibirOpcoesMenu() {
        System.out.println("Digite\n"
                + "1-Cadastrar veículo rotativo;\n"
                + "2-Cadastrar proprietário;\n"
                + "3-Cadastrar veículo mensalista;\n"
                + "4-Registrar acesso de usuário rotativo;\n"
                + "5-Registrar acesso de usuário mensalista;\n"
                + "6-Exibir veículos cadastrados;\n"
                + "7-Exibir proprietários;\n"
                + "8-Exibir listas de acessos;\n"
                + "9-Exibir faturamento.\n"
                + "0-Sair");
    }

    private static void exibirMensagemSaida() {
        System.out.println( "Obrigado por utilizar o sistema de gerenciamento de estacionamento!");
    }

    public static double calcularFaturamento() {
        double total = 0;
        Veiculo vmensalista = new VeiculoMensalista();
    
        for (Veiculo veiculo : sisEstacionamento.getVeiculos()) {
            if (veiculo.getClass() == vmensalista.getClass()) {
                total += 0.5; // Simplesmente não tem nenhuma explicação a taxa é só 500 mesmo
            }
        }

        for (Acesso acesso : sisEstacionamento.getAcessos()) {
            if (acesso.getValor() != 0) {
                total += acesso.getValor();
            }
        }
    
        return total;
    }    

    public static void exibeFaturamento() {
        StringBuilder listaFaturamento = new StringBuilder("Faturamento:\n\n");
        double total = calcularFaturamento();
    
        Veiculo vmensalista = new VeiculoMensalista();
        listaFaturamento.append("Receita de mensalidades: \n\n");
        for (Veiculo veiculo : sisEstacionamento.getVeiculos()) {
            if (veiculo.getClass() == vmensalista.getClass()) {
                listaFaturamento.append("Mensalidade: ")
                                .append("\nPlaca do veículo mensalista: ").append(veiculo.getNumeroPlaca())
                                .append("\nValor da mensalidade: 500 R$\n\n");
            }
        }
    
        listaFaturamento.append("Receitas de acessos rotativos: \n");
        for (Acesso acesso : sisEstacionamento.getAcessos()) {
            if (acesso.getValor() != 0) {
                long hours = acesso.getDuracao().toHours();
                long minutes = acesso.getDuracao().toMinutes() % 60;
                listaFaturamento.append("Tempo de permanência: ").append(hours).append("h ")
                                .append(minutes).append("min")
                                .append("\n Valor: ").append(acesso.getValor()).append(" R$");
            }
        }
    
        listaFaturamento.append("\n\nFaturamento Total: ").append(total).append(" R$");
        System.out.println(listaFaturamento.toString());
    }    
}


