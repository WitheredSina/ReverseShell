package reverseShell.server;

import reverseShell.server.Server;

import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args){
        Server server = new Server();
        server.start(inputInt("Enter port: "));
    }

    static String input(String text) {
        System.out.println(text);
        return new Scanner(System.in).nextLine();
    }
    static int inputInt(String text){
        System.out.println(text);
        return new Scanner(System.in).nextInt();
    }
}
