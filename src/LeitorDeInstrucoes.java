import static java.lang.Integer.parseInt;

public class LeitorDeInstrucoes extends Simulador {
    Utils utils = new Utils();

    public void verificaInstrucao(String instrucao) {
        int destino = 0;
        int valorOrigem = 0;

        String[] instrucaoSeparada = instrucao.split(" ");


        try {
            if (!instrucaoSeparada[1].startsWith("(")) {
                instrucaoSeparada[1] = utils.replaceString(instrucaoSeparada[1]);
                destino = parseInt(instrucaoSeparada[1]);
            }
        } catch (Exception e) {}


        try {
            if (instrucaoSeparada[2].contains("R")) {
                // Pega o valor no registrador
                instrucaoSeparada[2] = utils.replaceString(instrucaoSeparada[2]);
                valorOrigem = bancoDeRegistradores.get(parseInt(instrucaoSeparada[2]));
            } else {
                // Pega apenar o valor fixo
                instrucaoSeparada[2] = utils.replaceString(instrucaoSeparada[2]);
                valorOrigem = parseInt(instrucaoSeparada[2]);
            }
        } catch (Exception e) {
        }


        switch (instrucaoSeparada[0]) {
            case "LD":
                LD(destino, instrucaoSeparada[1]);
                break;
            case "MOV":
                MOV(destino, valorOrigem);
                break;
            case "ST":
                ST(instrucaoSeparada[1], valorOrigem);
                break;
            case "ADD":
                ADD(destino, valorOrigem);
                break;
            case "DEC":
                DEC(destino);
                break;
            case "INC":
                INC(destino);
                break;
            case "SUB":
                SUB(destino, valorOrigem);
                break;
            case "GOTO":
                GOTO(destino);
                break;
            case "SBRS":
                SBRS(instrucaoSeparada[1], instrucaoSeparada[2]);
                break;
            case "SBRC":
                SBRC(instrucaoSeparada[1], instrucaoSeparada[2]);
                break;
            case "BSET":
                BSET(destino);
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

    public void SBRC(String local, String posicao) {
        int valorSreg;
        if (local.equals("SREG")) {
            valorSreg = this.sreg.get(parseInt(posicao));
        } else {
            posicao = utils.replaceString(posicao);
            valorSreg = this.sreg.get(parseInt(posicao));
        }
        if (valorSreg == 0) {
            this.Cl++;
        }
    }

    public void BSET(int posicaoSreg) {
        this.sreg.set(posicaoSreg, 1);
    }

    public void LD(int destino, String valorOrigem) {
        int valor;
        if (valorOrigem.contains("R")) {
            // Pega o valor do Registrador e então na memória principal
            valorOrigem = utils.replaceString(valorOrigem);
            int valorRegistrador = this.bancoDeRegistradores.get(parseInt(valorOrigem));
            valor = parseInt(this.memoriaPrincipal.get(valorRegistrador));
        } else {
            // Pega direto na memória principal
            valorOrigem = utils.replaceString(valorOrigem);
            valor = parseInt(this.memoriaPrincipal.get(parseInt(valorOrigem)));
        }

        this.bancoDeRegistradores.set(destino, valor);
    }

    public void ST(String destino, int valorOrigem) {
        int valor;
        if (destino.contains("R")) {
            // Pega o valor do Registrador e então na memória principal
            destino = utils.replaceString(destino);
            int valorRegistrador = this.bancoDeRegistradores.get(parseInt(destino));
            valor = parseInt(this.memoriaPrincipal.get(valorRegistrador));
        } else {
            // Pega direto na memória principal
            destino = utils.replaceString(destino);
            valor = parseInt(this.memoriaPrincipal.get(parseInt(destino)));
        }

        this.bancoDeRegistradores.set(valor, valorOrigem);
    }

    public void SUB(int destino, int valorOrigem) {
        int valorDestino = bancoDeRegistradores.get(destino);
        valorDestino = valorDestino - valorOrigem;
        this.bancoDeRegistradores.set(destino, valorDestino);
        if (valorDestino >= 0) {
            zeroFlag(valorDestino);
        } else if (valorDestino < 0) {
            negativeFlag(valorDestino);
        }
    }

    public void INC(int destino) {
        int valorDestino = bancoDeRegistradores.get(destino);
        valorDestino++;
        this.bancoDeRegistradores.set(destino, valorDestino);
        zeroFlag(valorDestino);
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


    public void zeroFlag(int resultadoOperacao) {
        if (resultadoOperacao == 0) {
            this.sreg.set(1, 1);
        }
    }

    public void negativeFlag(int resultadoOperacao) {
        if (resultadoOperacao < 0) {
            this.sreg.set(2, 1);
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