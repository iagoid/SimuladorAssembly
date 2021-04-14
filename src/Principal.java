import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        LeitorDeInstrucoes leitorDeInstrucoes = new LeitorDeInstrucoes();

        ArrayList<String> instrucoes = new ArrayList<>();
        instrucoes.add("BCLR 1");
        instrucoes.add("MOV R0, #0");
        instrucoes.add("MOV R1, #3");
        instrucoes.add("MOV R2, #2");
        instrucoes.add("ADD R0, R1");
        instrucoes.add("DEC R2");
        instrucoes.add("SBRS SREG, 1");
        instrucoes.add("GOTO 0x4");
        instrucoes.add("HALT");


        String instrucao = "";
        while (!instrucao.equals("HALT")) {
            leitorDeInstrucoes.atualizaRi();
            instrucao = instrucoes.get(leitorDeInstrucoes.Ri);
            leitorDeInstrucoes.atualizaCl();
            leitorDeInstrucoes.verificaInstrucao(instrucao);
            System.out.println("---------------" + instrucao  + "--------------");
            leitorDeInstrucoes.imprime();
            Thread.sleep(1500);
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