public class Utils {
    public String replaceString(String frase) {
        frase = frase.replaceAll(",", "");
        frase = frase.replaceAll("#", "");
        frase = frase.replaceAll("0x", "");
        if (frase.startsWith("R")) {
            frase = frase.replaceAll("R", "");
        }
        return frase;
    }
}
