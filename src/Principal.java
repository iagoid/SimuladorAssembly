import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        LeitorDeInstrucoes leitorDeInstrucoes = new LeitorDeInstrucoes();


        leitorDeInstrucoes.addInstrucao("BCLR 1");
        leitorDeInstrucoes.addInstrucao("MOV R0, #0");
        leitorDeInstrucoes.addInstrucao("MOV R1, #3");
        leitorDeInstrucoes.addInstrucao("MOV R2, #2");
        leitorDeInstrucoes.addInstrucao("ADD R0, R1");
        leitorDeInstrucoes.addInstrucao("DEC R2");
        leitorDeInstrucoes.addInstrucao("SBRS SREG, 1");
        leitorDeInstrucoes.addInstrucao("GOTO 0x4");
        // leitorDeInstrucoes.addInstrucao("ST (R3), R0");//instrucao teste(armazenar um numero na posicao 0 das instruções)
        //leitorDeInstrucoes.addInstrucao("INC R3");//instrucao teste
        //leitorDeInstrucoes.addInstrucao("SUB R1, R3");//instrucao teste

        leitorDeInstrucoes.addInstrucao("HALT");


        String instrucao = "";
        while (!instrucao.equals("HALT")) {
            leitorDeInstrucoes.atualizaRi();
            instrucao = leitorDeInstrucoes.memoriaPrincipal.get(leitorDeInstrucoes.Ri);
            leitorDeInstrucoes.atualizaCl();
            leitorDeInstrucoes.verificaInstrucao(instrucao);
            System.out.println("---------------" + instrucao  + "--------------");
            leitorDeInstrucoes.imprime();
            Thread.sleep(0);
        }
    }
}

/*
• bit 0: C - Carry Flag: indica que foi feito “vem-um” em uma subtracao
• bit 1: Z - Zero Flag: indica que o resultado de uma operacao matematica foi 0
• bit 2: N - Negative Flag: indica que o resultado de uma operacao matematica foi negativo
• bit 3: V - Overflow Flag: indica que houve um overflow em uma operacao de complemento a dois
• bit 4: S - Sign Flag: uso especıfico do AVR
• bit 5: H - Half carry: uso especıfico do AVR
• bit 6: T - Bit copy : uso especıfico do AVR
• bit 7: I - Interrupt Flag: e definido quando as interrupcoes estao habilitadas
• RW: indica que pode ser feita uma operacao de leitura e escrita no bit
 */