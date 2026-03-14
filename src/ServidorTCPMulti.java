import java.io.*;
import java.net.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Autor: Ricardo Issa de Sousa
 * Data: 14/03/2026
 * Resumo: Servidor TCP Concorrente que utiliza Threads para atender múltiplos clientes simultaneamente.
 */
public class ServidorTCPMulti {
    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor TCP Multi-Thread aguardando conexões na porta " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Delega o processamento para uma nova Thread
                new Thread(new TrataCliente(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}

class TrataCliente implements Runnable {
    private Socket clientSocket;

    public TrataCliente(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] Atendendo cliente: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

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
            System.out.println("[" + threadName + "] Erro: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar socket: " + e.getMessage());
            }
        }
    }
}