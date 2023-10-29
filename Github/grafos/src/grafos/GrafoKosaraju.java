/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class GrafoKosaraju {
    private Graph grafo;
    private Map<String, Integer> nodeIndices = new HashMap<>();
    private int componentes = 0;

    //Crea un grafo que determina si es feurtemente enlazado o no
    public GrafoKosaraju() {
        grafo = new SingleGraph("Grafo");
        System.setProperty("org.graphstream.ui", "swing");
        grafo.setAttribute("ui.stylesheet", "node { fill-color: red; }");
    }

    //Agrega el nombre sobre el nodo del grafico
    public void agregarNodo(String id) {
        Node node = grafo.addNode(id);
        nodeIndices.put(id, nodeIndices.size());
        node.setAttribute("ui.label", id);
    }

    //Agrega una nueva conexion entre dos usuarios
    public void agregarArco(String origen, String destino) {
        grafo.addEdge(origen + "-" + destino, origen, destino, true);
    }

    
    public void kosaraju() {
        // Paso 1: Realizar un recorrido DFS en el grafo original y guardar el orden de finalización en un stack
        Stack<String> stack = new Stack<>();
        boolean[] visitado = new boolean[grafo.getNodeCount()];
        for (Node node : grafo) {
            if (!visitado[nodeIndices.get(node.getId())]) {
                llenarOrden(node, visitado, stack);
            }
        }

        // Paso 2: Obtener el grafo transpuesto
        Graph grafoTranspuesto = obtenerGrafoTranspuesto();

        // Paso 3: Realizar un recorrido DFS en el grafo transpuesto en el orden definido por el stack
        visitado = new boolean[grafo.getNodeCount()];
        while (!stack.isEmpty()) {
            Node node = grafoTranspuesto.getNode(stack.pop());
            if (!visitado[nodeIndices.get(node.getId())]) {
                DFS(node, visitado);
                System.out.println();
                componentes++;
            }
        }

        // Si todo el grafo es fuertemente enlazado, cambia el color del grafo a verde; de lo contrario, a rojo.
        String color = componentes == 1 ? "green" : "red";
        for (Node node : grafo) {
            node.setAttribute("ui.style", "fill-color: " + color + ";");
        }
    }

    //Agrega los nodos que ya terminaron su reorrido a un stack
    private void llenarOrden(Node node, boolean[] visitado, Stack<String> stack) {
        visitado[nodeIndices.get(node.getId())] = true;
        for (Iterator<Node> k = node.getNeighborNodeIterator(); k.hasNext();) {
            Node vecino = k.next();
            if (!visitado[nodeIndices.get(vecino.getId())]) {
                llenarOrden(vecino, visitado, stack);
            }
        }
        stack.push(node.getId());
    }

    //Transpone el grafo
    private Graph obtenerGrafoTranspuesto() {
        Graph grafoTranspuesto = new SingleGraph("Grafo Transpuesto");
        for (Node node : grafo) {
            Node newNode = grafoTranspuesto.addNode(node.getId());
            newNode.setAttribute("ui.label", node.getId()); // Agrega una etiqueta al nodo con su ID
        }
        for (org.graphstream.graph.Edge edge : grafo.getEachEdge()) {
            grafoTranspuesto.addEdge(edge.getId(), edge.getTargetNode().getId(), edge.getSourceNode().getId(), true);
        }
        return grafoTranspuesto;
    }

    //Visita cada nodo para determinar el camino del algoritmo
    private void DFS(Node node, boolean[] visitado) {
        visitado[nodeIndices.get(node.getId())] = true;
        
         for (Iterator<Node> k = node.getNeighborNodeIterator(); k.hasNext();) {
            Node vecino = k.next();
            if (!visitado[nodeIndices.get(vecino.getId())]) {
                DFS(vecino, visitado);
            }
         }
        
         
    }
    
    //Muestra el grafo
    public void display() {
        grafo.display();
    }
}
