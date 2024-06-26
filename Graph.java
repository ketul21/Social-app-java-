package io.itpl;

import java.util.*;

public class Graph {
    private Map<Node, List<Node>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(Node node) {
        adjacencyList.put(node, new ArrayList<>());
    }

    public void addEdge(Node source, Node destination) {
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    public void removeNode(Node node) {
        adjacencyList.remove(node);
        for (List<Node> neighbors : adjacencyList.values()) {
            neighbors.remove(node);
        }
    }

    public void removeEdge(Node source, Node destination) {
        adjacencyList.get(source).remove(destination);
        adjacencyList.get(destination).remove(source);
    }

    public List<Node> getNeighbors(Node node) {
        return adjacencyList.getOrDefault(node, new ArrayList<>());
    }

    public Set<Node> getNodes() {
        return adjacencyList.keySet();
    }

    public List<Node> getUsers() {
        return new ArrayList<>(getNodes());
    }

    public int getShortestPathDistance(Node source, Node destination) {
        if (source == null || destination == null)
            return -1;

        if (source.equals(destination))
            return 0;

        Map<Node, Integer> distances = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.offer(source);
        distances.put(source, 0);
        visited.add(source);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    distances.put(neighbor, distances.get(current) + 1);

                    if (neighbor.equals(destination)) {
                        return distances.get(neighbor);
                    }
                }
            }
        }

        return -1; // No path found
    }

    public Node findUserByName(String userName) {
        for (Node user : getNodes()) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
}

class Node {
    private String name;
    private String profilePicture;

    public Node(String name, String profilePicture) {
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}

class SocialApp {
    private Graph socialGraph;

    public SocialApp() {
        socialGraph = new Graph();
    }

    public void addUser(String name, String profilePicture) {
        Node user = new Node(name, profilePicture);
        socialGraph.addNode(user);
    }

    public void addFriendship(String userName1, String userName2) {
        Node user1 = findUserByName(userName1);
        Node user2 = findUserByName(userName2);
        if (user1 != null && user2 != null) {
            socialGraph.addEdge(user1, user2);
        }
    }

    public List<Node> getFriends(String userName) {
        Node user = findUserByName(userName);
        if (user != null) {
            return socialGraph.getNeighbors(user);
        }
        return Collections.emptyList();
    }

    public int getRelationshipDistance(String userName1, String userName2) {
        Node user1 = socialGraph.findUserByName(userName1);
        Node user2 = socialGraph.findUserByName(userName2);

        if (user1 != null && user2 != null) {
            return socialGraph.getShortestPathDistance(user1, user2);
        }

        return -1; // Either user1 or user2 is not found
    }

    public List<Node> getUsers() {
        return socialGraph.getUsers();
    }

    private Node findUserByName(String userName) {
        return socialGraph.findUserByName(userName);
}
}
