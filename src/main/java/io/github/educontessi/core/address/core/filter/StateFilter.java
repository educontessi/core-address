package io.github.educontessi.core.address.core.filter;

public class StateFilter {

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_UF = "uf";

    private String name;
    private String uf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
