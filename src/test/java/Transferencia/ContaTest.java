package Transferencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {
    //Objetos↓
    Cliente xuxa;
    Cliente silvioSantos;
    Conta contaXuxa;
    Conta contaSilvio;

    @BeforeEach //Ele executa esse metodo, e sempre que iniciar um novo teste, ele limpa o resultado anterior basicamente
    void setUp() {
        xuxa = new Cliente("Xuxa","123456789","123466788 ");
        silvioSantos = new Cliente("Silvio Santos", "123321123","987654321");
        //posso colocar qualquer nome na variável, desde que faça sentido

        contaXuxa = new Conta("0025","2254",2500.00, xuxa);
        contaSilvio = new Conta("0026","2251",3500.00, silvioSantos);
        //Usando essa estrutura dentro desse metodo, não preciso utilizar os de baixo escrito anteriormente
    }

    @Test
    public void realizarTransacao(){
//        xuxa = new Cliente("Xuxa","123456789","123466788 ");
//        silvioSantos = new Cliente("Silvio Santos", "123321123","987654321");
//        //posso colocar qualquer nome na variável, desde que faça sentido
//
//        contaXuxa = new Conta("0025","2254",2500.00, xuxa);
//        contaSilvio = new Conta("0026","2251",3500.00, silvioSantos);

        contaXuxa.realizarTransferencia(1000.00, contaSilvio);
        assertEquals(1500.00, contaXuxa.getSaldo());
        assertEquals(4500.00, contaSilvio.getSaldo());

    }

    @Test
    public void validarTransferenciaInvalida(){
//        xuxa = new Cliente("Xuxa","123456789","123466788 ");
//        silvioSantos = new Cliente("Silvio Santos", "123321123","987654321");
//        //posso colocar qualquer nome na variável, desde que faça sentido
//
//        contaXuxa = new Conta("0025","2254",2500.00, xuxa);
//        contaSilvio = new Conta("0026","2251",3500.00, silvioSantos);

        boolean resultado = contaXuxa.realizarTransferencia(3500.00,contaSilvio);
        assertFalse(resultado);
    }

}