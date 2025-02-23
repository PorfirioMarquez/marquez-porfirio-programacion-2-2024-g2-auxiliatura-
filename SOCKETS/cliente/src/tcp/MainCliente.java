package tcp;

import java.util.Scanner;

public class MainCliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nickname: ");
        String nickname = scanner.nextLine();

        SocketTcp client = new SocketTcp("10.0.22631.4112", 4112);
        client.connected();

        boolean conectado = true;
        while (conectado) {
            String mensaje = scanner.nextLine();
            if (mensaje.equals(":quit")) {
                conectado = false;
                break;
            }

            PackageData data = new PackageData(nickname, mensaje, "localhost");
            client.emmit(data, (response) -> {
                System.out.println(response.getNick() + " > " + response.getMsn());
            });
        }

        System.out.println("Desconectando...");
        client.dissConnect();
        scanner.close();
    }
}

