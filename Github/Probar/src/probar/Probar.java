/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probar;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
/**
 *
 * @author user
 */
public class Probar {
    public static void main(String[] args) {
        GrafoKosaraju grafo = new GrafoKosaraju();
        // Agrega nodos y arcos al grafo
        grafo.agregarNodo("@pepe");
        grafo.agregarNodo("@mazinger");
        grafo.agregarNodo("@juanc");
        grafo.agregarNodo("@xoxojaime");
        grafo.agregarNodo("@tuqui33");
        grafo.agregarNodo("@sancho23");
        grafo.agregarArco("@pepe", "@mazinger");
        grafo.agregarArco("@mazinger", "@pepe");
        grafo.agregarArco("@mazinger", "@juanc");
        grafo.agregarArco("@juanc", "@xoxojaime");
        grafo.agregarArco("@xoxojaime", "@tuqui33");
        grafo.agregarArco("@tuqui33", "@juanc");
        grafo.agregarArco("@juanc", "@tuqui33");
        grafo.agregarArco("@tuqui33", "@sancho23");
        grafo.agregarArco("@sancho23", "@mazinger");
        // Muestra el grafo
        grafo.display();

        // Ejecuta el algoritmo de Kosaraju
        grafo.kosaraju();
    }
}
