package server;

import common.MyRemoteInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import static common.Constants.DEFAULT_GRAPH_NAME;

public class MyRemoteImpl extends UnicastRemoteObject
        implements MyRemoteInterface {

    private HashMap<String, Graph> graphs;

    MyRemoteImpl() throws RemoteException {
        graphs = new HashMap<>();
        graphs.putIfAbsent(DEFAULT_GRAPH_NAME, new Graph());
        System.out.println("MyRemoteImpl()");
    }

    @Override
    public String addEdge(String node1, String node2)
            throws RemoteException {
        return addEdge(DEFAULT_GRAPH_NAME, node1, node2);
    }

    @Override
    public String shortestDistance(String node1, String node2)
            throws RemoteException {
        return shortestDistance(DEFAULT_GRAPH_NAME, node1, node2);
    }

    @Override
    public String getGraph()
            throws RemoteException {
        return getGraph(DEFAULT_GRAPH_NAME);
    }

    @Override
    public String addEdge(String graphName, String node1, String node2)
            throws RemoteException {
        System.out.println("MyRemoteImpl:addEdge()");
        Graph graph = graphs.getOrDefault(graphName, new Graph());
        graph.addEdge(node1, node2);
        graphs.put(graphName, graph);   // Make sure to update the existing graph

        return "Added";
    }

    @Override
    public String shortestDistance(String graphName, String node1, String node2)
            throws RemoteException {
        Graph graph = graphs.get(graphName);
        if (graph == null) {
            return "No such graph";
        }

        return String.valueOf(graph.bfs(node1, node2));
    }

    @Override
    public String getGraph(String graphName)
            throws RemoteException {
        System.out.println("MyRemoteImpl:getGraph()");
        Graph graph = graphs.get(graphName);

        if (graph == null) {
            return "No such graph";
        }

        return graph.toString();
    }
}
