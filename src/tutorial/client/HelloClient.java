package tutorial.client;

import tutorial.server.HelloInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class HelloClient {
    public static void main(String[] args) {
        try {
            String hostName = "localhost";
            int RMIPort = Integer.parseInt("12345");
            String registryURL = "rmi://" + hostName + ":" + RMIPort + "/hello";
            HelloInterface h = null;
            h = (HelloInterface) Naming.lookup(registryURL);
            String message = h.sayHello("Donald Duck");
            System.out.println("tutorial.client.HelloClient: " + message);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
