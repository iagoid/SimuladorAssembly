import java.util.ArrayList;

public class Simulador {
    private int tamanhoSreg = 8;
    private int tamanhoBancoRegistradores = 4;

    public int Z;
    public int Cl = 0;
    public int Ri;
    public ArrayList<String> memoriaPrincipal = new ArrayList<>();
    public ArrayList<Integer> sreg = new ArrayList<>();
    public ArrayList<Integer> bancoDeRegistradores = new ArrayList<>();

    public Simulador() {
        for (int i = 1; i <= tamanhoSreg; i++) {
            this.sreg.add(0);
        }
        for (int i = 1; i <= tamanhoBancoRegistradores; i++) {
            this.bancoDeRegistradores.add(0);
        }
    }

    public void atualizaCl() {
        this.Cl++;
    }

    public void atualizaRi() {
        this.Ri = this.Cl;
    }

    public void addInstrucao(String instrucao){
        this.memoriaPrincipal.add(instrucao);
    }

    public void imprime() {
        System.out.println("Z " + this.Z);
        System.out.println("Cl " + this.Cl);
        System.out.println("Ri " + this.Ri);
        System.out.println("Banco de registradores");
        for (int i = 0; i < bancoDeRegistradores.size(); i++) {
            System.out.println("\tR" + i + " " + bancoDeRegistradores.get(i));
        }
        System.out.println("Sreg");
        for (int i = 0; i < sreg.size(); i++) {
            System.out.println("\t" + i + " " + sreg.get(i));
        }
    }
}
