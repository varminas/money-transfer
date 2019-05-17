package lt.arminai.moneyTransfer.sequrity;

public enum AuthRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    AuthRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
