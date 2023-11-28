package classes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.mockito.InjectMocks;

import classes.AcessoPorHora;
import exceptions.EstacionamentoFechadoException;

 
public class AcessoPorHoraTest {



   @Test
    public void testCalculaValor(){
        //tarifa com valor de 0.2 o test passa
        AcessoPorHora acessoPorHora = new AcessoPorHora();
        Duration duracao = Duration.ofMinutes(60);
        double tarifa=0.2;
        double resultado = acessoPorHora.calculaValor(duracao, tarifa);

        double valorEsperado = 9.0;

        assertEquals(Math.max(valorEsperado, 0), Math.max(resultado, 0),0.01);

    }
     @Test
    public void testSetEntradaEstacionamentoFechadoException() {
        AcessoPorHora acessoPorHora = new AcessoPorHora();

        try {
            //test para estacionamento fechado
            acessoPorHora.setEntrada(LocalDate.now(), LocalTime.of(22, 00));
            fail("EstacionamentoFechadoException esperada, mas não foi lançada.");
        } catch (EstacionamentoFechadoException e) {
            
        }
    }

    @Test
    public void testSetEntrada() {
        //
        AcessoPorHora acessoPorHora = new AcessoPorHora();

        
        try {
            acessoPorHora.setEntrada(LocalDate.now(), LocalTime.of(12, 0));
        } catch (EstacionamentoFechadoException e) {
            fail("EstacionamentoFechadoException não esperada, mas foi lançada.");
        }

        
        assertEquals(LocalTime.of(12, 0), acessoPorHora.getEntrada().toLocalTime());
    }
    @Test
    void testCalculaValorMock() {
        // Criar um mock da classe AcessoPorQuinze
        AcessoPorQuinzeTest mockAcessoPorQuinze = mock(AcessoPorQuinzeTest.class);

        // Configurar o comportamento desejado para o mock
        when(mockAcessoPorQuinze.calculaValor(any(Duration.class),anyDouble())).thenReturn(10.0);

        // Criar uma instância de AcessoPorHora
        AcessoPorHora acessoPorHora = new AcessoPorHora();

        // Chamar o método calculaValor usando o mock de AcessoPorQuinze
        double resultado = acessoPorHora.calculaValor(Duration.ofHours(2), 5.0);

        // Verificar se o método calculaValor do mock foi chamado corretamente
        verify(mockAcessoPorQuinze).calculaValor(Duration.ofHours(2),  5.0);

        // Verificar se o resultado está correto
        assertEquals(8.0, resultado); // Supondo que o desconto seja de 2.0
    }

    @Test
    void testSetEntradaMock() throws EstacionamentoFechadoException {
        // Criar uma instância de AcessoPorHora
        AcessoPorHora acessoPorHora = new AcessoPorHora();

        // Criar um mock para a exceção EstacionamentoFechadoException
        EstacionamentoFechadoException mockException = mock(EstacionamentoFechadoException.class);

        // Configurar o comportamento desejado para o mock de EstacionamentoFechadoException
        doThrow(mockException).when(mockException).setEntrada(any(LocalDate.class), any(LocalTime.class));

        // Tentar chamar o método setEntrada com uma hora de entrada fora do horário permitido
        try {
            acessoPorHora.setEntrada(LocalDate.now(), LocalTime.of(22, 0));
            fail("EstacionamentoFechadoException esperada, mas não foi lançada");
        } catch (EstacionamentoFechadoException e) {
            // Verificar se a exceção foi lançada corretamente
            verify(mockException).setEntrada(any(LocalDate.class), any(LocalTime.class));
        }
    }
}



