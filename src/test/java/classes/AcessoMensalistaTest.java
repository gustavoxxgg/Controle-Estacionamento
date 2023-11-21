package classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import exceptions.EstacionamentoFechadoException;

public class AcessoMensalistaTest {
      @Test
    public void testSetEntrada() {
        AcessoMensalista acessoMensalista = new AcessoMensalista();
        LocalDate entrada = LocalDate.of(2023, 1, 1);
        LocalTime horaEntrada = LocalTime.of(12, 0);

        try {
            acessoMensalista.setEntrada(entrada, horaEntrada);
            assertEquals(LocalDateTime.of(entrada, horaEntrada), acessoMensalista.getEntrada());
        } catch (EstacionamentoFechadoException e) {
            fail("Não era esperada uma exceção de EstacionamentoFechadoException");
        }
    }
}
