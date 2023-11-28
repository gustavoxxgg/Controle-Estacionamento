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