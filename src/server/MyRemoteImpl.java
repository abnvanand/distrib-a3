package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject
        implements MyRemoteInterface {
    private Graph graph;

    MyRemoteImpl() throws RemoteException {
        graph = new Graph();
        System.out.println("MyRemoteImpl()");
    }

    @Override
    public boolean addEdge(String node1, String node2) throws RemoteException {
        System.out.println("MyRemoteImpl:addEdge()");
        return graph.addEdge(node1, node2);
    }

    @Override
    public String shortestDistance(String node1, String node2) throws RemoteException {
        return String.valueOf(graph.bfs(node1, node2));
    }

    @Override
    public String getGraph()
            throws RemoteException {
        System.out.println("MyRemoteImpl:getGraph()");
        return graph.toString();
    }
}
