package br.ifood.utils;

public class Utils {
    public static boolean validarCPF(String cpf) {
        // Implemente validações de CPF
        return cpf.matches("\\d{11}");
    }
}
