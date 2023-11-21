package classes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import classes.AcessoPorHora;
import exceptions.EstacionamentoFechadoException;

 
public class AcessoPorHoraTest {
   @Test
    public void testCalculaValor(){
        //tarifa com valor de 0.2 o test passa
        AcessoPorHora acessoPorHora = new AcessoPorHora();
        Duration duracao = Duration.ofMinutes(60);
        double tarifa=0.5;
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
}



