import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 * Autor: Ricardo Issa de Sousa
 * Data: 14/03/2026
 * Resumo: Cliente UDP que envia uma região e aguarda a hora com timeout de 5 segundos.
 */
public class ClienteUDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a região (ex: America/Sao_Paulo): ");
        String regiao = scanner.nextLine();

        try (DatagramSocket aSocket = new DatagramSocket()) {
            aSocket.setSoTimeout(5000); // Define o tempo limite para 5 segundos

            byte[] msg = regiao.getBytes();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789;

            DatagramPacket request = new DatagramPacket(msg, msg.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1024];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            aSocket.receive(reply);
            System.out.println("Hora na região solicitada: " + new String(reply.getData(), 0, reply.getLength()));

        } catch (SocketTimeoutException e) {
            System.out.println("Servidor ocupado ou offline.");
        } catch (IOException e) {
            System.out.println("Erro de IO: " + e.getMessage());
        }
    }
}