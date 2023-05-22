package com.mycompany.jogodaforca;

import java.util.Scanner;

public class JogoDaForca {

    static int MAX_TENTATIVAS = 5;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean jogarNovamente = true;

        while (jogarNovamente) {
            jogarForca();
            System.out.print("Deseja jogar novamente? (s/n): ");
            String resposta = input.nextLine();
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            jogarNovamente = resposta.equalsIgnoreCase("s");
        }
        
        System.out.println("Obrigado por jogar!");
    }

    private static void jogarForca() {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite uma palavra: ");
        String palavra = input.nextLine().toUpperCase();
        System.out.print("Digite uma dica: ");
        String dica = input.nextLine();

        char[] palavraEscondida = criarPalavraEscondida(palavra);
        int tentativas = 0;

        while (tentativas < MAX_TENTATIVAS && !palavraCompleta(palavraEscondida)) {
            System.out.println("\nPalavra: " + new String(palavraEscondida));
            System.out.println("Dica é: " + dica);
            System.out.println("Digite uma letra: ");

            char letra = input.nextLine().toUpperCase().charAt(0);
            boolean letraEncontrada = false;

            for (int i = 0; i < palavra.length(); i++) {
                if (palavra.charAt(i) == letra) {
                    palavraEscondida[i] = letra;    
                    letraEncontrada = true;
                }
            }

            if (!letraEncontrada) {
                tentativas++;
                System.out.println("Letra incorreta! Tentativas restantes: " + (MAX_TENTATIVAS - tentativas));
            }
        }

        if (palavraCompleta(palavraEscondida)) {
            System.out.println("\nParabéns! Você acertou a palavra: " + palavra);
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        } else {
            System.out.println("\nVocê perdeu! A palavra correta era: " + palavra);
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        }
    }

    private static char[] criarPalavraEscondida(String palavra) {
        char[] palavraEscondida = new char[palavra.length()];

       for (int i = 0; i < palavra.length(); i++) {
            if (Character.isLetter(palavra.charAt(i))) {
                palavraEscondida[i] = '_';
            } else {
                palavraEscondida[i] = palavra.charAt(i);
            }
        }

        return palavraEscondida;
    }

    private static boolean palavraCompleta(char[] palavraEscondida) {
        for (char c : palavraEscondida) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
}