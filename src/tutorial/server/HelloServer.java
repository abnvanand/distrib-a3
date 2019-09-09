package tutorial.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloServer {
    public static void main(String[] args)
            throws IOException {
        String portNum="12345", registryURL;

        int RMIPortNum = Integer.parseInt(portNum);
        startRegistry(RMIPortNum);
        HelloImpl exportedObj = new HelloImpl();
        registryURL = "rmi://localhost:" + portNum + "/hello";
        Naming.rebind(registryURL, exportedObj);
        System.out.println("Hello Server ready");
        listRegistry(registryURL);
    }

    private static void listRegistry(String registryURL)
            throws MalformedURLException, RemoteException {
        System.out.println("Registry " + registryURL + " contains:-");
        String[] names = Naming.list(registryURL);
        for (String name : names)
            System.out.println(name);
    }

    private static void startRegistry(int rmiPortNum) throws RemoteException {
        try {
            Registry registry = null;
            registry = LocateRegistry.getRegistry(rmiPortNum);
            registry.list();
        } catch (RemoteException e) {
            System.out.println("RMI registry not found at port: " + rmiPortNum);
            Registry registry = LocateRegistry.createRegistry(rmiPortNum);
            System.out.println("RMI registry created at port: " + rmiPortNum);
        }
    }
}
