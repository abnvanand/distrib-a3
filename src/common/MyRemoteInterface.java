package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemoteInterface extends Remote {
    public String addEdge(String node1, String node2)
            throws RemoteException;

    public String shortestDistance(String node1, String node2)
            throws RemoteException;

    public String getGraph()
            throws RemoteException;

    public String addEdge(String graphName, String node1, String node2)
            throws RemoteException;

    public String shortestDistance(String graphName, String node1, String node2)
            throws RemoteException;

    public String getGraph(String graphName)
            throws RemoteException;
}