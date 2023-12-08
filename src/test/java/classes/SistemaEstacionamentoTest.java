package classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SistemaEstacionamentoTest {

    private SistemaEstacionamento sistemaEstacionamento;
    private CadastroVeiculos mockVeiculos;
    private CadastroProprietarios mockProprietarios;
    private CadastroAcessos mockAcessos;

    @BeforeEach
    public void setUp() {
        mockVeiculos = mock(CadastroVeiculos.class);
        mockProprietarios = mock(CadastroProprietarios.class);
        mockAcessos = mock(CadastroAcessos.class);

        sistemaEstacionamento = new SistemaEstacionamento();
        sistemaEstacionamento.veiculos = mockVeiculos;
        sistemaEstacionamento.proprietarios = mockProprietarios;
        sistemaEstacionamento.acessos = mockAcessos;
    }

    @Test
    public void testCadastrarVeiculoIntegration() {
        Veiculo veiculo = new Veiculo();
        sistemaEstacionamento.cadastrarVeiculo(veiculo);

        verify(mockVeiculos, times(1)).cadastrar(veiculo);
    }
    @Test
    public void testGetVeiculosIntegration() {
        List<Veiculo> veiculos = Arrays.asList(new Veiculo(), new Veiculo());
        
        when(mockVeiculos.getVeiculos()).thenReturn(veiculos);

        assertEquals(veiculos, sistemaEstacionamento.getVeiculos());
    }

    @Test
    public void testGetProprietariosIntegration() {
        List<Proprietario> proprietarios = Arrays.asList(new Proprietario(), new Proprietario());
        when(mockProprietarios.getProprietarios()).thenReturn(proprietarios);

        assertEquals(proprietarios, sistemaEstacionamento.getProprietarios());
    }

    @Test
    public void testGetAcessosIntegration() {
        List<Acesso> acessos = Arrays.asList(new AcessoMensalista(), new AcessoMensalista());
        when(mockAcessos.getAcessos()).thenReturn(acessos);

        assertEquals(acessos, sistemaEstacionamento.getAcessos());
    }
}