import static java.lang.Integer.parseInt;

public class LeitorDeInstrucoes extends Simulador {
    Utils utils = new Utils();

    public void verificaInstrucao(String instrucao) {
        int destino = 0;
        int valorOrigem = 0;

        String[] instrucaoSeparada = instrucao.split(" ");


        try {
            instrucaoSeparada[1] = utils.replaceString(instrucaoSeparada[1]);
            destino = parseInt(instrucaoSeparada[1]);
        } catch (Exception e) { }


        try {
            if (instrucaoSeparada[2].contains("R")) {
                instrucaoSeparada[2] = utils.replaceString(instrucaoSeparada[2]);
                valorOrigem = bancoDeRegistradores.get(parseInt(instrucaoSeparada[2]));
            } else {
                instrucaoSeparada[2] = utils.replaceString(instrucaoSeparada[2]);
                valorOrigem = parseInt(instrucaoSeparada[2]);
            }
        } catch (
                Exception e) {
        }


        switch (instrucaoSeparada[0]) {
            case "LD":
                break;
            case "MOV":
                MOV(destino, valorOrigem);
                break;
            case "ST":

                break;
            case "ADD":
                ADD(destino, valorOrigem);
                break;
            case "DEC":
                DEC(destino);
                break;
            case "INC":

                break;
            case "SUB":

                break;
            case "GOTO":
                GOTO(destino);
                break;
            case "SBRS":
                SBRS(instrucaoSeparada[1], instrucaoSeparada[2]);
                break;

            case "BSET":

                break;
            case "BCLR":
                BCLR(destino);

                break;
            case "HALT":

                break;
            case "NULL":

                break;
        }

    }

    public void MOV(int destino, int valorOrigem) {
        this.bancoDeRegistradores.set(destino, valorOrigem);
    }

    public void ADD(int destino, int valorOrigem) {
        int valorDestino = bancoDeRegistradores.get(destino);
        valorDestino = valorDestino + valorOrigem;
        this.bancoDeRegistradores.set(destino, valorDestino);
        zeroFlag(valorDestino);
    }

    public void DEC(int destino) {
        int valorDestino = bancoDeRegistradores.get(destino);
        valorDestino--;
        this.bancoDeRegistradores.set(destino, valorDestino);
        zeroFlag(valorDestino);
    }

    public void GOTO(int posicaoMemoria) {
        this.Cl = posicaoMemoria;
    }

    public void SBRS(String local, String posicao) {
        int valorSreg;
        if (local.equals("SREG")) {
            valorSreg = this.sreg.get(parseInt(posicao));
        } else {
            posicao = utils.replaceString(posicao);
            valorSreg = this.sreg.get(parseInt(posicao));
        }
        if (valorSreg == 1) {
            this.Cl++;
        }
    }

    public void BCLR(int posicaoSREG) {
        this.sreg.set(posicaoSREG, 0);
    }


    public void zeroFlag(int resultadoOperacao){
        if(resultadoOperacao == 0){
            this.sreg.set(1, 1);
        }
    }
}


    /*
    • #k: valor constante
    • Rd: Registrador Destino
    • Ro: Registrador Origem
    • s: bit s de um registrador ou palavra
    • Z: Registrador Z
    • 0x...: Endereco da Memoria Principal (o ... vai conter o endereço da memoria)
    • (X): A operacao vai utilizar o valor armazenado no endereço X da Memoria Principal
    • (Ro): A operacao vai utilizar o valor armazenado no endereco que esta armazenado no Ro
     */