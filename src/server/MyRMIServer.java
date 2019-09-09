package server;

import common.Constants;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MyRMIServer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a port for RMI Server");
        String rmiPort = in.nextLine();
        if (rmiPort.trim().isEmpty())
            rmiPort = Constants.DEFAULT_RMI_PORT;

        try {
            // make sure registry exists
            startRegistry(Integer.parseInt(rmiPort));
            String registryURL = String.format("rmi://localhost:%s/%s", rmiPort, Constants.RMI_REGISTRY_ENDPOINT);
            // Bind the remote object that could be used by clients
            Naming.rebind(registryURL, new MyRemoteImpl());
            System.out.println("Published remote object");
            listRegistry(registryURL);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static void listRegistry(String registryURL)
            throws MalformedURLException, RemoteException {
        System.out.println("Registry " + registryURL + " contains:-");
        String[] names = Naming.list(registryURL);
        for (String name : names)
            System.out.println(name);
    }

    /**
     * Publishes the RMI registry if not already published
     */
    private static void startRegistry(int rmiPort)
            throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(rmiPort);
            registry.list();
        } catch (RemoteException e) {
            System.out.println("RMI registry not found at port: " + rmiPort);
            Registry registry = LocateRegistry.createRegistry(rmiPort);
            System.out.println("RMI registry created at port: " + rmiPort);
        }
    }
}
