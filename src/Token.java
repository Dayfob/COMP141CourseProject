// Phase 1.1
// Alikhan Semembayev
// Henglay Eung

public class Token {
    private String value = "";
    private String type;

    public Token() {

    }

    public Token(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public void addElement(char element) {
        this.value = this.value + element;
    }

    public void removeLastElement() {
        this.value = this.value.substring(0, this.value.length() - 1);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.value;
    }

    public String getType() {
        return this.type;
    }
}
