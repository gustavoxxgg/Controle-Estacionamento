package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.EstacionamentoFechadoException;



public class EstacionamentoFechadoExceptionTest {
     @Test
     //busca teste e exibe o erro
    public void testMensagemPadrao() {
        EstacionamentoFechadoException exception = new EstacionamentoFechadoException();
        assertEquals("Exception in thread:com.grupo10.estacionamento.exceptions.DadosPessoaisIncompletosException", exception.getMessage());
    }
}
