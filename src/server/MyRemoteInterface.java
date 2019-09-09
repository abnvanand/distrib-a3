package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemoteInterface extends Remote {
    public boolean addEdge(String node1, String node2)
            throws RemoteException;

    public String shortestDistance(String node1, String node2)
            throws RemoteException;

    public String getGraph()
            throws RemoteException;
}
