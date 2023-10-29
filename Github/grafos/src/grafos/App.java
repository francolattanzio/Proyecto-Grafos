/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;


/**
 *
 * @author user
 */
public class App {

    private Stick<String> nombresUsuarios = new Stick<String>();
    private Stick<String> conexionesUsuarios = new Stick<String>();
    private Graph grafo;
    


    
    
    
    // Abre el archivo de texto a seleccioanar
    public void abrirArchivo() throws Exception{
        boolean valid = false;
        while(!valid){
            try{
                JFileChooser file=new JFileChooser();
                file.showOpenDialog(null);
                File abre=file.getSelectedFile();
                String aux="";   
                valid = true;
                if(abre!=null){ 
                    while(!nombresUsuarios.isEmpty()){
                        nombresUsuarios.pop();
                    }
                    while(!conexionesUsuarios.isEmpty()){
                        conexionesUsuarios.pop();
                    }                    FileReader archivos=new FileReader(abre);
                    BufferedReader lee=new BufferedReader(archivos);
                    while((aux=lee.readLine())!=null){
                        if(aux.contains("@") && aux.contains(",")){
                            String[] auxSeparado = aux.split(", ");
                            String User1 = auxSeparado[0];
                            String User2 = auxSeparado[1];
                            conexionesUsuarios.push(User1 + ", " + User2);
                        }
                        else if(aux.contains("@")|| aux.contains(",")){
                            nombresUsuarios.push(aux);
                        }
                    }
                lee.close();
                valid = true;
                }
            }
            catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Error.");
            }
        }
    }
    
    //Guarda los datos en un archivo de texto
    public void guardarArchivo() {
        try{
            JFileChooser file=new JFileChooser();
            file.showSaveDialog(null);
            File guarda =file.getSelectedFile();
            if(guarda !=null) {
                FileWriter  save=new FileWriter(guarda+".txt");
                save.write("usuarios\n");
                Stick<String> copiaUsuarios = new Stick();
                nombresUsuarios.copy(nombresUsuarios, copiaUsuarios);
                while(!copiaUsuarios.isEmpty()){
                    String aux = copiaUsuarios.pop();
                    save.append(aux + "\n");
                }
                save.append("relaciones\n");
                Stick<String> copiaConexiones = new Stick();
                conexionesUsuarios.copy(conexionesUsuarios, copiaConexiones);
                while(!copiaConexiones.isEmpty()){
                    String aux = copiaConexiones.pop();
                    save.append(aux);
                    if(!copiaConexiones.isEmpty()){
                        save.append("\n");
                    }
                }
                save.close();
                JOptionPane.showMessageDialog(null, "El archivo se ha guardado exitosamente.", "Información",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Su archivo no se ha guardado.", "Advertencia",JOptionPane.WARNING_MESSAGE);
        }
    }

    // Agrega una conexion entre dos usuarios
    public void agregarConexion(){
        String Usuario1 = JOptionPane.showInputDialog("Ingrese el nombre del usuario (sin arroba (@)) de donde parte el seguimiento.");
        String Usuario2 = JOptionPane.showInputDialog("Ingrese el nombre del usuario (sin arroba (@)) a seguir.");
        String buenUsuario1 = "@" + Usuario1;
        String buenUsuario2 = "@" + Usuario2;
        Stick<String> copiaUsuarios = new Stick();
        nombresUsuarios.copy(nombresUsuarios, copiaUsuarios);
        boolean valid1 = false;
        while(!copiaUsuarios.isEmpty()){
            String aux1 = copiaUsuarios.pop();
            if(aux1.equals(buenUsuario1)){
                valid1 = true;
                break;
            }
        }
        boolean valid2 = false;
        Stick<String> copiaUsuarios2 = new Stick();
        nombresUsuarios.copy(nombresUsuarios, copiaUsuarios2);
        while(!copiaUsuarios2.isEmpty()){
            String aux2 = copiaUsuarios2.pop();
            if(aux2.equals(buenUsuario2)){
                valid2 = true;
                break;
            }
        }
        boolean valid = false;
        Stick<String> copiaConexiones = new Stick();
        conexionesUsuarios.copy(conexionesUsuarios, copiaConexiones);
        while(!copiaConexiones.isEmpty()){
            String aux3 = copiaConexiones.pop();
            String union = buenUsuario1 + ", " + buenUsuario2;
            if(aux3.equals(union)){
                valid = true;
                break;
            }
        }
        if(valid){
            JOptionPane.showMessageDialog(null, "Error. Conexión ya añadida. Se le devolverá al menú.");
        }
        else if(valid1 && valid2 && !valid){
            String lineaBuena = buenUsuario1 + ", " + buenUsuario2;
            conexionesUsuarios.push(lineaBuena);
            JOptionPane.showMessageDialog(null, "Conexión agregada exitosamente!\nCargue los datos en el archivo de texto para guardar los cambios realizados.");
        }
        else if(!valid1){
            JOptionPane.showMessageDialog(null, "Error. Usuarios no encontrados o no ingresó ningún valor. Se le devolverá al menú.");
        }
        else if(!valid2){
        JOptionPane.showMessageDialog(null, "Error. Usuarios no encontrados o no ingresó ningún valor. Se le devolverá al menú.");
        }
    }
    
    // Agrega un usuario nuevo a la pila
    public void agregarUsuario(){
        String nuevoUsuario = JOptionPane.showInputDialog("Ingrese el nombre del usuario sin arroba (@)\n(si su nombre lo incluye, agreguelo).");
        String buenUsuario = "@" + nuevoUsuario;
        Stick<String> copiaUsuarios = new Stick();
        nombresUsuarios.copy(nombresUsuarios, copiaUsuarios);
        boolean valid = true;
        while(!copiaUsuarios.isEmpty()){
            String aux = copiaUsuarios.pop();
            if(aux.equals(buenUsuario) || aux.equals("@null")){
                valid = false;
                break;
            }
        }
        if(!valid){
            JOptionPane.showMessageDialog(null, "Error. Usuario existente o no ingresó ningún valor. Se le devolverá al menú.");
        }
        else{
            nombresUsuarios.push(buenUsuario);
            JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente!\nCargue los datos en el archivo de texto para guardar los cambios realizados.");
        }
    }

    //Elimina un usuario dando el nombre
    public void eliminarUsuario(){
        String usuarioEliminar = JOptionPane.showInputDialog("Ingrese el nombre del usuario sin arroba (@) a eliminar");
        String usuarioE = "@" + usuarioEliminar;
        Stack<String> copiaUsuarios = new Stack();
        while(!nombresUsuarios.isEmpty()){
            String aux = nombresUsuarios.pop();
            if(aux != null){
                copiaUsuarios.push(aux);
            }
        }
        boolean valid = false;
        while(!copiaUsuarios.isEmpty()){
            String auxB = copiaUsuarios.pop();
            if(auxB.equals(usuarioE)){
                valid = true;
            }
            else{
                nombresUsuarios.push(auxB);
            }
        }
        if(!valid){
            JOptionPane.showMessageDialog(null, "Error. Usuario no encontrado. Se le devolverá al menú.");
        }
        else{
            Stack<String> copiaConexiones = new Stack();
            while(!conexionesUsuarios.isEmpty()){
                String aux = conexionesUsuarios.pop();
                if(aux != null){
                    copiaConexiones.push(aux);
                }
            }
            while(!copiaConexiones.isEmpty()){
                String aux = copiaConexiones.pop();
                String[] auxSeparado = aux.split(", ");
                String User1 = auxSeparado[0];
                String User2 = auxSeparado[1];
                if(User1.equals(usuarioE) || User2.equals(usuarioE)){
                }
                else{
                    conexionesUsuarios.push(aux);
                }
            }
            JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente!.\nCargue los datos en el archivo de texto para guardar los cambios realizados.");
        }
    }

    //Muestra el grafo dirigido
    public void showGrafo() {
        Graph grafo1 = new SingleGraph("Grafos");
        Stick<String> copiaUsuarios = new Stick();
        nombresUsuarios.copy(nombresUsuarios, copiaUsuarios);
        while(!copiaUsuarios.isEmpty()){
            String aux = copiaUsuarios.pop();
            if(aux != null){
                grafo1.addNode(aux);
            }
        }
        Stick<String> copiaConexiones = new Stick();
        conexionesUsuarios.copy(conexionesUsuarios, copiaConexiones);
        while(!copiaConexiones.isEmpty()){
            String aux = copiaConexiones.pop();
            if(aux != null){
                String[] auxSeparado = aux.split(", ");
                String User1 = auxSeparado[0];
                String User2 = auxSeparado[1];
                grafo1.addEdge(User1+"-"+User2, User1, User2, true);
            }
        }
        for (org.graphstream.graph.Node node : grafo1) {
            node.addAttribute("ui.label", node.getId());
        }

        grafo1.display();
    }

    //Crea un objeto de GrafoKosaraju para determinar si el grafo resultante es fuertemente enlazado o no
    public void FuertementeEnlazado(){
        GrafoKosaraju KGrafo = new GrafoKosaraju();
        if(nombresUsuarios == null || conexionesUsuarios == null) {
            System.out.println("Los datos de los usuarios o las conexiones son nulos");
            return;
        }

        Stick<String> copiaUsuarios = new Stick();
        nombresUsuarios.copy(nombresUsuarios, copiaUsuarios);
        while(!copiaUsuarios.isEmpty()){
            String aux = copiaUsuarios.pop();
            if(aux != null) {
                KGrafo.agregarNodo(aux);
            }
        }
        Stick<String> copiaConexiones = new Stick();
        conexionesUsuarios.copy(conexionesUsuarios, copiaConexiones);
        while(!copiaConexiones.isEmpty()){
            String aux = copiaConexiones.pop();
            if(aux != null) {
                String[] auxSeparado = aux.split(", ");
                if(auxSeparado.length >= 2) {
                    String User1 = auxSeparado[0];
                    String User2 = auxSeparado[1];
                    KGrafo.agregarArco(User1,User2);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Si los nodos son de color verde, entonces el grafo dado es fuertemente enlazado.\nDe lo contrario, si son rojos, significa que el grafo no lo es.");
        KGrafo.display();
        KGrafo.kosaraju();
    }

}