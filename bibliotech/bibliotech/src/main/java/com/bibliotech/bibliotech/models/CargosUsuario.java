package com.bibliotech.bibliotech.models;

public enum CargosUsuario {
    BIBLIOTECARIO("bibliotecario"),
    ALUNO_MONITOR("aluno_monitor");

    private String cargo;

    CargosUsuario(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}
