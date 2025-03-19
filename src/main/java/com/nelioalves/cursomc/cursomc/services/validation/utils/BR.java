package com.nelioalves.cursomc.cursomc.services.validation.utils;

public class BR {
    public static boolean isValidCPF(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: 00000000000)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0, remainder;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        remainder = (sum * 10) % 11;
        if (remainder == 10) remainder = 0;
        if (remainder != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        remainder = (sum * 10) % 11;
        if (remainder == 10) remainder = 0;
        return remainder == Character.getNumericValue(cpf.charAt(10));
    }

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: 00000000000000)
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int sum = 0, remainder;
        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weights1[i];
        }
        remainder = sum % 11;
        int digit1 = (remainder < 2) ? 0 : 11 - remainder;

        if (digit1 != Character.getNumericValue(cnpj.charAt(12))) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weights2[i];
        }
        remainder = sum % 11;
        int digit2 = (remainder < 2) ? 0 : 11 - remainder;

        return digit2 == Character.getNumericValue(cnpj.charAt(13));
    }
}
