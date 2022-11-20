package project.rico.darirumah.exception.model;


public enum ExceptionEnum {
    SER_UNEXPECTED_ERROR("SE-GENERAL", "System Error / Timeout");

    public final String code;
    public final String description;
    ExceptionEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
