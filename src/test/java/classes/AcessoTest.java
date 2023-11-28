package classes;

import exceptions.EstacionamentoFechadoException;
import exceptions.PeriodoInvalidoException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import classes.Acesso;
import org.junit.jupiter.api.Test;

public class AcessoTest {

    @Test
    public void setSaida_ThrowsPeriodoInvalidoException() throws EstacionamentoFechadoException {
        
       
        Acesso acesso = new AcessoMensalista() ;
        acesso.setEntrada(LocalDateTime.of(2023, 12, 12, 12, 0));
        

        try {
             Acesso acessos = new AcessoMensalista();
            acessos.setSaida(LocalDate.of(2023, 12, 12), LocalTime.of(12, 0));
            fail("Deveria ter lançado uma exceção");
        } catch (PeriodoInvalidoException e) {
            // Exceção esperada
        }
    }

    @Test
    public void calculaPeriodo_ReturnsCorrectPeriod() {
        Acesso acesso = new AcessoMensalista();
        acesso.setEntrada(LocalDateTime.of(2023, Month.JANUARY, 1, 12, 0));
        //acesso.setSaida(LocalDate.of(2023, Month.JANUARY, 2), LocalTime.of(12, 0));

        Period periodo = acesso.caculaPeriodo();

        assertEquals(1, periodo.getDays());
    }

    @Test
    public void calculaDuracao_ReturnsCorrectDuration() {
        Acesso acesso = new AcessoMensalista();
        acesso.setEntrada(LocalDateTime.of(2023, Month.JANUARY, 1, 12, 0));
        acesso.setSaida(LocalDateTime.of(2023, 12, 12, 15, 30));

        Duration duracao = acesso.calculaDuracao();

        assertEquals(3, duracao.toHours());
        assertEquals(30, duracao.toMinutes() % 60);
    }

    @Test
    public void calculaValor_ReturnsCorrectValue() {
        Acesso acesso = new Acesso() {
            @Override
            public double calculaValor(Duration duracao, double tarifa) {
                return duracao.toHours() * tarifa;
            }

            @Override
            public void setEntrada(LocalDate dia, LocalTime hora) throws EstacionamentoFechadoException {
                
            }
        };

        Duration duracao = Duration.ofHours(5);
        acesso.setTarifa(10.0);

        double valor = acesso.calculaValor(duracao, acesso.getTarifa());

        assertEquals(50.0, valor, 0.01); // Adicionando uma tolerância para comparação de doubles
    }

    

}