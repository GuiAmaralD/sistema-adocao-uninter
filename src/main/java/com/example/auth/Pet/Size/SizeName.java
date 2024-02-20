package com.example.auth.Pet.Size;


public enum SizeName {

    PEQUENO("pequeno"),
    MEDIO("medio"),
    GRANDE("grande");

    private String text;

    SizeName(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static SizeName fromString(String text) {
        for (SizeName b : SizeName.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("sizeName enum fornecido incorretamente");
    }

}
