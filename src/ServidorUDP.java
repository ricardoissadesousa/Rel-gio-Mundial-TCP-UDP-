import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Autor: Ricardo Issa de Sousa
 * Data: 14/03/2026
 * Resumo: Servidor UDP que recebe requisições de regiões geográficas e retorna a hora local.
 */
public class ServidorUDP {
    public static void main(String[] args) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[1024];
            System.out.println("Servidor UDP iniciado na porta 6789...");

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                String regiao = new String(request.getData(), 0, request.getLength()).trim();
                String resposta;

                try {
                    ZonedDateTime horaLocal = ZonedDateTime.now(ZoneId.of(regiao));
                    resposta = horaLocal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z"));
                } catch (Exception e) {
                    resposta = "Erro: Região inválida (" + regiao + ").";
                }

                byte[] dadosResposta = resposta.getBytes();
                DatagramPacket reply = new DatagramPacket(dadosResposta, dadosResposta.length, request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) aSocket.close();
        }
    }
}