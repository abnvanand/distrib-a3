package client;

import common.Constants;
import server.MyRemoteInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import static common.Constants.*;

public class MyClient {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter RMI server host");
        String hostName = in.nextLine();
        if (hostName.trim().isEmpty())
            hostName = "localhost";
        System.out.println("Enter RMI server port");
        String rmiPort = in.nextLine();
        if (rmiPort.trim().isEmpty())
            rmiPort = Constants.DEFAULT_RMI_PORT;

        String registryURL = String.format("rmi://%s:%s/%s", hostName, rmiPort, Constants.RMI_REGISTRY_ENDPOINT);
        try {
            MyRemoteInterface remoteObject = (MyRemoteInterface) Naming.lookup(registryURL);
            menu(remoteObject);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static void menu(MyRemoteInterface remoteObject)
            throws RemoteException {
        while (true) {
            System.out.println("Enter command");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            String[] splits = line.split(" ");
            String commandType = splits[0];
            String response = null;
            if (commandType.equals(CMD_ADD_EDGE)) {
                response = String.valueOf(remoteObject.addEdge(splits[1], splits[2]));
            } else if (commandType.equals(CMD_SHORTEST_DISTANCE)) {
                response = remoteObject.shortestDistance(splits[1], splits[2]);
            } else if (commandType.equals(CMD_GET_GRAPH)) {
                response = remoteObject.getGraph();
            }
            System.out.println("Response from server: " + response);
        }
    }
}
