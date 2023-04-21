package main.java.core.responses;

import main.java.core.domain.FieldEnum;

public class CoreError {

    private FieldEnum field;
    private String message;

    public CoreError(FieldEnum field, String message) {
        this.field = field;
        this.message = message;
    }

    public FieldEnum getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
