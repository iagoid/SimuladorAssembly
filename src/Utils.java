public class Utils {
    public String replaceString(String frase) {
        frase = frase.replaceAll(",", "");
        frase = frase.replaceAll("#", "");
        frase = frase.replaceAll("0x", "");
        frase = frase.replaceAll("\\(", "");
        frase = frase.replaceAll("\\)", "");
        if (frase.startsWith("R")) {
            frase = frase.replaceAll("R", "");
        }
        return frase;
    }
}
