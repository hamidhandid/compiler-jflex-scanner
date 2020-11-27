package classes;

public class Symbol {
    private final String token;
    private final Type type;

    public Symbol(String token, Type type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public Type getType() {
        return type;
    }
}
