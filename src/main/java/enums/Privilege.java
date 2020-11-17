package enums;

public enum  Privilege {
    ORDINARY(1),
    RECEPTIONIST(2),
    OWNER(3);

    private final int id;

    Privilege(int id) { this.id = id; }

    public int getValue() { return id; }

    public static Privilege getPrivilage(int id) {
        switch (id) {
            default:
            case (1):
                return ORDINARY;
            case (2):
                return RECEPTIONIST;
            case (3):
                return OWNER;
        }
    }
}
