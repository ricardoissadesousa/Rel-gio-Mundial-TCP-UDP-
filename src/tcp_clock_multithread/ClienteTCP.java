package tcp_clock_multithread;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Autor: Ricardo Issa de Sousa
 * Data: 14/03/2026
 * Resumo: Cliente TCP genérico para consultar hora mundial.
 */
public class ClienteTCP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a região (ex: Europe/London): ");
        String regiao = scanner.nextLine();

        String server = "localhost";
        int serverPort = 12345;

        try (Socket s = new Socket(server, serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()))) {

            out.write(regiao + "\n");
            out.flush();

            String resposta = in.readLine();
            System.out.println("Resposta do servidor: " + resposta);

        } catch (UnknownHostException e) {
            System.out.println("Sock: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}