import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws InterruptedException, IOException {
        LeitorDeInstrucoes leitorDeInstrucoes = new LeitorDeInstrucoes();

        // Pega o diretorio
        File diretorio = new File (".") ;
        String nome = diretorio.getCanonicalPath() + "\\src\\instrucoes.txt";


        // Lê o TXT e adiciona as instruções para a memoria principal
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); // lê a primeira linha
            System.out.println("Código Assembly\n");
            while (linha != null) {
                System.out.println(linha);
                leitorDeInstrucoes.addInstrucao(linha);
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }


        // Executa as instruções
        Scanner ler = new Scanner(System.in);
        System.out.println("\n\nDeseja executar o debug? S = Sim | Outra tecla = Não ");
        String debug = ler.next();

        String instrucao = "";
        while (!instrucao.equals("HALT")) {
            leitorDeInstrucoes.atualizaRi();
            instrucao = leitorDeInstrucoes.memoriaPrincipal.get(leitorDeInstrucoes.Ri);
            leitorDeInstrucoes.atualizaCl();
            leitorDeInstrucoes.verificaInstrucao(instrucao);
            if(debug.equals("s") || debug.equals("S") || instrucao.equals("HALT")){
                System.out.println();
                System.out.println("\n\n---------------" + instrucao  + "--------------");
                leitorDeInstrucoes.imprime();
            }
            Thread.sleep(500);
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