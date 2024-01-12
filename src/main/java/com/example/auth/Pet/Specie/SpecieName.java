package com.example.auth.Pet.Specie;

public enum SpecieName {
    CACHORRO("cachorro"),
    GATO("gato"),
    AVE("ave");

    private String text;

    SpecieName(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static SpecieName fromString(String text) {
        for (SpecieName b : SpecieName.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("specieName enum fornecido incorretamente");
    }

}
