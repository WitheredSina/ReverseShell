package reverseShell.client;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args){
        Client server = new Client();
        server.connect(input("IP Address: "), inputInt("Enter port: "));
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
