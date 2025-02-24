package com.laisa.callSystem.domain.enums;

public enum Prioridade {
    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    private Prioridade(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static Prioridade toEnum(Integer cod) {
        if(cod == null){
            return null;
        }

        for(Prioridade x : Prioridade.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade Inválida");
    }
}
