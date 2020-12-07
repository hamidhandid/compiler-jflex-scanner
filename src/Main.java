import classes.CompilerScanner;
import classes.Highlighter;
import classes.Symbol;
import classes.Type;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final String preAddressOfFiles = args != null ? args[0] : "../files/";
        final String nameOfCodeFile = args != null && args.length > 1 ? args[1] : "code.txt";

        CompilerScanner scanner = new CompilerScanner(new FileReader(preAddressOfFiles + nameOfCodeFile));
        Highlighter highlighter = new Highlighter();
        while (true) {
            Symbol symbol = scanner.nextToken();
            if (scanner.yyatEOF()) {
                break;
            }
//            final Type type = symbol.getType();
//            if (type == Type.BOOLEAN || type == Type.INTEGER_NUMBER || type == Type.REAL_NUMBER || type == Type.HEX || type == Type.SCIENTIFIC_NOTATION || type == Type.STRING || type == Type.ESCAPE_CHAR) {
//                System.out.println(type);
//                System.out.println("int: " + scanner.intValue);
//                System.out.println("double: " + scanner.doubleValue);
//                System.out.println("boolean: " + scanner.booleanValue);
//                System.out.println("string: " + scanner.string);
//                System.out.println();
//            }
//            System.out.println(symbol.getToken());
            highlighter.addHtmlText(symbol.getToken(), symbol.getType());
        }
        highlighter.writeToHtml(highlighter.getDocument().outerHtml(), preAddressOfFiles + "highlighter.html");
    }
}
