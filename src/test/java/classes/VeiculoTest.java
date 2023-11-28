package classes;
import exceptions.DadosVeiculosIncompletosException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VeiculoTest {
    
    @Test
    public void VeiculoValido() throws DadosVeiculosIncompletosException {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("Honda");
        veiculo.setModelo("Civic");
        veiculo.setNumeroPlaca("abc1234");

        assertEquals("Honda", veiculo.getMarca());
        assertEquals("Civic", veiculo.getModelo());
        assertEquals("abc1234", veiculo.getNumeroPlaca());
    }

    @Test
    public void naoChegaMarca() {
        Veiculo veiculo = new Veiculo();
        assertThrows(DadosVeiculosIncompletosException.class, () -> {
            veiculo.setMarca("");
        });
    }

    @Test
    public void naoChegaModelo(){
        Veiculo veiculo = new Veiculo();
        assertThrows(DadosVeiculosIncompletosException.class, () -> {
        veiculo.setModelo("");
        });
    }

    @Test
    public void naoChegaPlaca(){
        Veiculo veiculo = new Veiculo();
        assertThrows(DadosVeiculosIncompletosException.class, () -> {
        veiculo.setNumeroPlaca("");
        });
    }
    //testes unitarios 
    @Test
    public void testSetMarca() throws DadosVeiculosIncompletosException {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("Toyota");
        assertEquals("Toyota", veiculo.getMarca());
    }

    @Test
    public void testSetModelo() throws DadosVeiculosIncompletosException {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo("Camry");
        assertEquals("Camry", veiculo.getModelo());
    }
     @Test
    public void testSetAcesso() {
        Acesso mockAcesso = mock(Acesso.class);
        Veiculo veiculo= new Veiculo();
        veiculo.setAcesso(mockAcesso);
        assertEquals(1, veiculo.getAcessos().size());
    }
}