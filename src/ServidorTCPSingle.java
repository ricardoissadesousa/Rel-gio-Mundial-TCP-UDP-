import java.io.*;
import java.net.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Autor: Ricardo Issa de Sousa
 * Data: 14/03/2026
 * Resumo: Servidor TCP (Single-thread) que atende requisições de fuso horário de forma sequencial.
 */
public class ServidorTCPSingle {
    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor TCP Single-Thread aguardando conexões na porta " + port + "...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                    System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                    String regiao = in.readLine();

                    if (regiao != null) {
                        String resposta;
                        try {
                            ZonedDateTime horaLocal = ZonedDateTime.now(ZoneId.of(regiao));
                            resposta = horaLocal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z"));
                        } catch (Exception e) {
                            resposta = "Erro: Região inválida.";
                        }

                        out.write(resposta + "\n");
                        out.flush();
                    }
                } catch (IOException e) {
                    System.out.println("Erro na comunicação com o cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}