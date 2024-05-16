
package biblioteca2;

import javax.swing.JOptionPane;

class Cliente {
    String nombre;
    int id;
    String telefono;

    public Cliente(String nombre, int id,String telefono) {
        this.nombre = nombre;
        this.id = id;
        this.telefono = telefono;
    }
}

// Clase para representar un libro
class Libro {
    String titulo;
    String autor;
    int id;
    int cantidadDisponible;

    public Libro(String titulo, String autor, int id, int cantidadDisponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.id = id;
        this.cantidadDisponible = cantidadDisponible;
    }
}

// Clase para representar un préstamo
class Prestamo {
    Cliente cliente;
    Libro libro;

    public Prestamo(Cliente cliente, Libro libro) {
        this.cliente = cliente;
        this.libro = libro;
    }
}

// Clase para representar un Nodo de Árbol Binario de Clientes
class NodoCliente {
    Cliente cliente;
    NodoCliente izquierda;
    NodoCliente derecha;

    public NodoCliente(Cliente cliente) {
        this.cliente = cliente;
        izquierda = null;
        derecha = null;
    }
}

// Clase para representar un Árbol Binario de Clientes
class ArbolClientes {
    NodoCliente raiz;

    public ArbolClientes() {
        raiz = null;
    }

    // Método para insertar un nuevo cliente en el árbol
    public void insertar(Cliente cliente) {
        raiz = insertarRec(raiz, cliente);
    }

    private NodoCliente insertarRec(NodoCliente raiz, Cliente cliente) {
        if (raiz == null) {
            raiz = new NodoCliente(cliente);
            return raiz;
        }
        // Si el id del cliente del nuevo cliente es menor, lo insertamos en la izquierda
        if (cliente.id < raiz.cliente.id)
            raiz.izquierda = insertarRec(raiz.izquierda, cliente);
        // Si el id del cliente del nuevo cliente es mayor, lo insertamos en la derecha
        else if (cliente.id > raiz.cliente.id)
            raiz.derecha = insertarRec(raiz.derecha, cliente);
        return raiz;
    }

    // Método para buscar un cliente por su id
    public Cliente buscar(int idCliente) {
        return buscarRec(raiz, idCliente);
    }

    private Cliente buscarRec(NodoCliente raiz, int idCliente) {
        if (raiz == null || raiz.cliente.id == idCliente)
            return (raiz != null) ? raiz.cliente : null;
        if (raiz.cliente.id < idCliente)
            return buscarRec(raiz.derecha, idCliente);
        return buscarRec(raiz.izquierda, idCliente);
    }

    // Método para eliminar un cliente por su ID
    public void eliminarCliente(int idCliente) {
        raiz = eliminarClienteRec(raiz, idCliente);
    }

    private NodoCliente eliminarClienteRec(NodoCliente raiz, int idCliente) {
        if (raiz == null) return null;
        if (idCliente < raiz.cliente.id)
            raiz.izquierda = eliminarClienteRec(raiz.izquierda, idCliente);
        else if (idCliente > raiz.cliente.id)
            raiz.derecha = eliminarClienteRec(raiz.derecha, idCliente);
        else {
            // Caso 1: El nodo es una hoja o tiene solo un hijo
            if (raiz.izquierda == null)
                return raiz.derecha;
            else if (raiz.derecha == null)
                return raiz.izquierda;

            // Caso 2: El nodo tiene dos hijos
            // Encontrar el sucesor in-order (el menor nodo en el subárbol derecho)
            raiz.cliente = encontrarMenor(raiz.derecha);
            // Eliminar el sucesor in-order
            raiz.derecha = eliminarClienteRec(raiz.derecha, raiz.cliente.id);
        }
        return raiz;
    }

    // Método auxiliar para encontrar el menor nodo en un árbol
    private Cliente encontrarMenor(NodoCliente raiz) {
        return (raiz.izquierda == null) ? raiz.cliente : encontrarMenor(raiz.izquierda);
    }
}

// Clase para representar un Nodo de Árbol Binario de Libros
class NodoLibro {
    Libro libro;
    NodoLibro izquierda;
    NodoLibro derecha;

    public NodoLibro(Libro libro) {
        this.libro = libro;
        izquierda = null;
        derecha = null;
    }
}

// Clase para representar un Árbol Binario de Libros
class ArbolLibros {
    NodoLibro raiz;

    public ArbolLibros() {
        raiz = null;
    }

    // Método para insertar un nuevo libro en el árbol
    public void insertar(Libro libro) {
        raiz = insertarRec(raiz, libro);
    }

    private NodoLibro insertarRec(NodoLibro raiz, Libro libro) {
        if (raiz == null) {
            raiz = new NodoLibro(libro);
            return raiz;
        }
        // Si el id del libro del nuevo libro es menor, lo insertamos en la izquierda
        if (libro.id < raiz.libro.id)
            raiz.izquierda = insertarRec(raiz.izquierda, libro);
        // Si el id del libro del nuevo libro es mayor, lo insertamos en la derecha
        else if (libro.id > raiz.libro.id)
            raiz.derecha = insertarRec(raiz.derecha, libro);
        return raiz;
    }

    // Método para buscar un libro por su id
    public Libro buscar(int idLibro) {
        return buscarRec(raiz, idLibro);
    }

    private Libro buscarRec(NodoLibro raiz, int idLibro) {
        if (raiz == null || raiz.libro.id == idLibro)
            return (raiz != null) ? raiz.libro : null;
        if (raiz.libro.id < idLibro)
            return buscarRec(raiz.derecha, idLibro);
        return buscarRec(raiz.izquierda, idLibro);
    }

    // Método para eliminar un libro por su ID
    public void eliminarLibro(int idLibro) {
        raiz = eliminarLibroRec(raiz, idLibro);
    }

    private NodoLibro eliminarLibroRec(NodoLibro raiz, int idLibro) {
        if (raiz == null) return null;
        if (idLibro < raiz.libro.id)
            raiz.izquierda = eliminarLibroRec(raiz.izquierda, idLibro);
        else if (idLibro > raiz.libro.id)
            raiz.derecha = eliminarLibroRec(raiz.derecha, idLibro);
        else {
            // Caso 1: El nodo es una hoja o tiene solo un hijo
            if (raiz.izquierda == null)
                return raiz.derecha;
            else if (raiz.derecha == null)
                return raiz.izquierda;

            // Caso 2: El nodo tiene dos hijos
            // Encontrar el sucesor in-order (el menor nodo en el subárbol derecho)
            raiz.libro = encontrarMenor(raiz.derecha);
            // Eliminar el sucesor in-order
            raiz.derecha = eliminarLibroRec(raiz.derecha, raiz.libro.id);
        }
        return raiz;
    }

    // Método auxiliar para encontrar el menor nodo en un árbol
    private Libro encontrarMenor(NodoLibro raiz) {
        return (raiz.izquierda == null) ? raiz.libro : encontrarMenor(raiz.izquierda);
    }
}

// Clase para representar un Nodo de Árbol Binario de Préstamos
class NodoPrestamo {
    Prestamo prestamo;
    NodoPrestamo izquierda;
    NodoPrestamo derecha;

    public NodoPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
        izquierda = null;
        derecha = null;
    }
}

// Clase para representar un Árbol Binario de Préstamos
class ArbolPrestamos {
    NodoPrestamo raiz;

    public ArbolPrestamos() {
        raiz = null;
    }

    // Método para insertar un nuevo préstamo en el árbol
    public void insertar(Prestamo prestamo) {
        raiz = insertarRec(raiz, prestamo);
    }

    private NodoPrestamo insertarRec(NodoPrestamo raiz, Prestamo prestamo) {
        if (raiz == null) {
            raiz = new NodoPrestamo(prestamo);
            return raiz;
        }
        // Si el id del cliente del nuevo préstamo es menor, lo insertamos en la izquierda
        if (prestamo.cliente.id < raiz.prestamo.cliente.id)
            raiz.izquierda = insertarRec(raiz.izquierda, prestamo);
        // Si el id del cliente del nuevo préstamo es mayor, lo insertamos en la derecha
        else if (prestamo.cliente.id > raiz.prestamo.cliente.id)
            raiz.derecha = insertarRec(raiz.derecha, prestamo);
        return raiz;
    }

    // Método para buscar un préstamo por el id del cliente
    public Prestamo buscar(int idCliente) {
        return buscarRec(raiz, idCliente);
    }

    private Prestamo buscarRec(NodoPrestamo raiz, int idCliente) {
        if (raiz == null || raiz.prestamo.cliente.id == idCliente)
            return (raiz != null) ? raiz.prestamo : null;
        if (raiz.prestamo.cliente.id < idCliente)
            return buscarRec(raiz.derecha, idCliente);
        return buscarRec(raiz.izquierda, idCliente);
    }
    
    // Método para eliminar un préstamo por el ID del cliente y el ID del libro
public void eliminar(int idCliente, int idLibro) {
    raiz = eliminarRec(raiz, idCliente, idLibro);
}

private NodoPrestamo eliminarRec(NodoPrestamo raiz, int idCliente, int idLibro) {
    if (raiz == null) return null;
    if (idCliente < raiz.prestamo.cliente.id || (idCliente == raiz.prestamo.cliente.id && idLibro < raiz.prestamo.libro.id))
        raiz.izquierda = eliminarRec(raiz.izquierda, idCliente, idLibro);
    else if (idCliente > raiz.prestamo.cliente.id || (idCliente == raiz.prestamo.cliente.id && idLibro > raiz.prestamo.libro.id))
        raiz.derecha = eliminarRec(raiz.derecha, idCliente, idLibro);
    else {
        // Caso 1: El nodo es una hoja o tiene solo un hijo
        if (raiz.izquierda == null)
            return raiz.derecha;
        else if (raiz.derecha == null)
            return raiz.izquierda;

        // Caso 2: El nodo tiene dos hijos
        // Encontrar el sucesor in-order (el menor nodo en el subárbol derecho)
        raiz.prestamo = encontrarMenor(raiz.derecha);
        // Eliminar el sucesor in-order
        raiz.derecha = eliminarRec(raiz.derecha, raiz.prestamo.cliente.id, raiz.prestamo.libro.id);
    }
    return raiz;
}


    // Método para eliminar un préstamo por el id del cliente
    public void eliminar(int idCliente) {
        raiz = eliminarRec(raiz, idCliente);
    }

    private NodoPrestamo eliminarRec(NodoPrestamo raiz, int idCliente) {
        if (raiz == null) return null;
        if (idCliente < raiz.prestamo.cliente.id)
            raiz.izquierda = eliminarRec(raiz.izquierda, idCliente);
        else if (idCliente > raiz.prestamo.cliente.id)
            raiz.derecha = eliminarRec(raiz.derecha, idCliente);
        else {
            // Caso 1: El nodo es una hoja o tiene solo un hijo
            if (raiz.izquierda == null)
                return raiz.derecha;
            else if (raiz.derecha == null)
                return raiz.izquierda;

            // Caso 2: El nodo tiene dos hijos
            // Encontrar el sucesor in-order (el menor nodo en el subárbol derecho)
            raiz.prestamo = encontrarMenor(raiz.derecha);
            // Eliminar el sucesor in-order
            raiz.derecha = eliminarRec(raiz.derecha, raiz.prestamo.cliente.id);
        }
        return raiz;
    }

    // Método auxiliar para encontrar el menor nodo en un árbol
    private Prestamo encontrarMenor(NodoPrestamo raiz) {
        return (raiz.izquierda == null) ? raiz.prestamo : encontrarMenor(raiz.izquierda);
    }
    
    // Método para buscar un préstamo por el ID del cliente y el ID del libro
public Prestamo buscar(int idCliente, int idLibro) {
    return buscarRec(raiz, idCliente, idLibro);
}

private Prestamo buscarRec(NodoPrestamo raiz, int idCliente, int idLibro) {
    if (raiz == null || (raiz.prestamo.cliente.id == idCliente && raiz.prestamo.libro.id == idLibro))
        return (raiz != null) ? raiz.prestamo : null;
    if (raiz.prestamo.cliente.id < idCliente || (raiz.prestamo.cliente.id == idCliente && raiz.prestamo.libro.id < idLibro))
        return buscarRec(raiz.derecha, idCliente, idLibro);
    return buscarRec(raiz.izquierda, idCliente, idLibro);
}

}

public class Main {
    public static void main(String[] args) {
        // Crear el árbol de clientes
        ArbolClientes arbolClientes = new ArbolClientes();

        // Crear el árbol de libros
        ArbolLibros arbolLibros = new ArbolLibros();

        // Crear el árbol de préstamos
        ArbolPrestamos arbolPrestamos = new ArbolPrestamos();

        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "----- Menú -----\n" +
                    "1. Agregar cliente\n" +
                    "2. Buscar cliente\n" +
                    "3. Listar clientes\n" +
                    "4. Eliminar cliente\n" +
                    "5. Agregar libro\n" +
                    "6. Buscar libro\n" +
                    "7. Listar libros\n" +
                    "8. Eliminar libro\n" +
                    "9. Prestar libro\n" +
                    "10. Devolver libro\n" +
                    "11. Listar préstamos\n" +
                    "12. Salir\n" +
                    "Seleccione una opción:"
            ));

            switch (opcion) {
                case 1:
                    agregarCliente(arbolClientes);
                    break;
                case 2:
                    buscarCliente(arbolClientes);
                    break;
                case 3:
                    listarClientes(arbolClientes);
                    break;
                case 4:
                    eliminarCliente(arbolClientes);
                    break;
                case 5:
                    agregarLibro(arbolLibros);
                    break;
                case 6:
                    buscarLibro(arbolLibros);
                    break;
                case 7:
                    listarLibros(arbolLibros);
                    break;
                case 8:
                    eliminarLibro(arbolLibros);
                    break;
                case 9:
                    prestarLibro(arbolClientes, arbolLibros, arbolPrestamos);
                    break;
                case 10:
                    devolverLibro(arbolClientes, arbolLibros, arbolPrestamos);
                    break;
                case 11:
                    listarPrestamos(arbolPrestamos);
                    break;
                case 12:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Inténtelo de nuevo.");
                    break;
            }
        } while (opcion != 12);
    }

    public static void agregarCliente(ArbolClientes arbolClientes) {
        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente:"));
        String telefonoCliente = JOptionPane.showInputDialog("Ingrese el telefono del cliente:");
        arbolClientes.insertar(new Cliente(nombreCliente, idCliente, telefonoCliente));
        JOptionPane.showMessageDialog(null, "Cliente agregado con éxito.");
    }

    public static void buscarCliente(ArbolClientes arbolClientes) {
        int idClienteBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente a buscar:"));
        Cliente clienteBuscado = arbolClientes.buscar(idClienteBuscar);
        if (clienteBuscado != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado:\n" +
                    "Nombre: " + clienteBuscado.nombre + "\nID: " + clienteBuscado.id + "\nTelefono: " + clienteBuscado.telefono);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        }
    }

    public static void listarClientes(ArbolClientes arbolClientes) {
        StringBuilder listaClientes = new StringBuilder("Listado de clientes:\n");
        listarClientesRec(arbolClientes.raiz, listaClientes);
        JOptionPane.showMessageDialog(null, listaClientes.toString());
    }

    private static void listarClientesRec(NodoCliente raiz, StringBuilder listaClientes) {
        if (raiz != null) {
            listarClientesRec(raiz.izquierda, listaClientes);
            listaClientes.append("Nombre: ").append(raiz.cliente.nombre).append(", ID: ").append(raiz.cliente.id).append(", Telefono: ").append(raiz.cliente.telefono).append("\n");
            listarClientesRec(raiz.derecha, listaClientes);
        }
    }

    public static void eliminarCliente(ArbolClientes arbolClientes) {
        int idClienteEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente a eliminar:"));
        arbolClientes.eliminarCliente(idClienteEliminar);
        JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito.");
    }

    public static void agregarLibro(ArbolLibros arbolLibros) {
        String tituloLibro = JOptionPane.showInputDialog("Ingrese el título del libro:");
        String autorLibro = JOptionPane.showInputDialog("Ingrese el autor del libro:");
        int idLibro = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro:"));
        int cantidadDisponible = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de copias disponibles:"));
        arbolLibros.insertar(new Libro(tituloLibro, autorLibro, idLibro, cantidadDisponible));
        JOptionPane.showMessageDialog(null, "Libro agregado con éxito.");
    }

    public static void buscarLibro(ArbolLibros arbolLibros) {
        int idLibroBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro a buscar:"));
        Libro libroBuscado = arbolLibros.buscar(idLibroBuscar);
        if (libroBuscado != null) {
            JOptionPane.showMessageDialog(null, "Libro encontrado:\n" +
                    "Título: " + libroBuscado.titulo + "\nAutor: " + libroBuscado.autor + "\nID: " + libroBuscado.id + "\nDisponibles: " + libroBuscado.cantidadDisponible);
        } else {
            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
        }
    }

    public static void listarLibros(ArbolLibros arbolLibros) {
        StringBuilder listaLibros = new StringBuilder("Listado de libros:\n");
        listarLibrosRec(arbolLibros.raiz, listaLibros);
        JOptionPane.showMessageDialog(null, listaLibros.toString());
    }

    private static void listarLibrosRec(NodoLibro raiz, StringBuilder listaLibros) {
        if (raiz != null) {
            listarLibrosRec(raiz.izquierda, listaLibros);
            listaLibros.append("Título: ").append(raiz.libro.titulo).append(", Autor: ").append(raiz.libro.autor).append(", ID: ").append(raiz.libro.id).append(", Disponibles: ").append(raiz.libro.cantidadDisponible).append("\n");
            listarLibrosRec(raiz.derecha, listaLibros);
        }
    }

    public static void eliminarLibro(ArbolLibros arbolLibros) {
        int idLibroEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro a eliminar:"));
        arbolLibros.eliminarLibro(idLibroEliminar);
        JOptionPane.showMessageDialog(null, "Libro eliminado con éxito.");
    }

    public static void prestarLibro(ArbolClientes arbolClientes, ArbolLibros arbolLibros, ArbolPrestamos arbolPrestamos) {
        int idClientePrestamo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente que desea prestar el libro:"));
        Cliente clientePrestamo = arbolClientes.buscar(idClientePrestamo);
        if (clientePrestamo == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }
        int idLibroPrestamo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro que desea prestar:"));
        Libro libroPrestamo = arbolLibros.buscar(idLibroPrestamo);
        if (libroPrestamo == null) {
            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
            return;
        }
        if (libroPrestamo.cantidadDisponible <= 0) {
            JOptionPane.showMessageDialog(null, "El libro no está disponible para préstamo.");
            return;
        }
        libroPrestamo.cantidadDisponible--;
        Prestamo prestamo = new Prestamo(clientePrestamo, libroPrestamo);
        arbolPrestamos.insertar(prestamo); // Insertamos el préstamo en el árbol de préstamos
        JOptionPane.showMessageDialog(null, "Préstamo realizado con éxito.");
    }

    public static void devolverLibro(ArbolClientes arbolClientes, ArbolLibros arbolLibros, ArbolPrestamos arbolPrestamos) {
        int idClienteDevolucion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente que devolvió el libro:"));
        int idLibroDevolucion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro que desea devolver:"));

        // Buscamos el préstamo por el ID del cliente y el ID del libro
        Prestamo prestamoDevolucion = arbolPrestamos.buscar(idClienteDevolucion, idLibroDevolucion);

        if (prestamoDevolucion == null) {
            JOptionPane.showMessageDialog(null, "El libro no está prestado a este cliente.");
            return;
        }

        prestamoDevolucion.libro.cantidadDisponible++;
        arbolPrestamos.eliminar(idClienteDevolucion, idLibroDevolucion); // Eliminamos el préstamo del árbol de préstamos
        JOptionPane.showMessageDialog(null, "Devolución realizada con éxito.");
    }

    public static void listarPrestamos(ArbolPrestamos arbolPrestamos) {
        StringBuilder listaPrestamos = new StringBuilder("Listado de préstamos:\n");
        listarPrestamosRec(arbolPrestamos.raiz, listaPrestamos);
        JOptionPane.showMessageDialog(null, listaPrestamos.toString());
    }

    private static void listarPrestamosRec(NodoPrestamo raiz, StringBuilder listaPrestamos) {
        if (raiz != null) {
            listarPrestamosRec(raiz.izquierda, listaPrestamos);
            listaPrestamos.append("Cliente: ").append(raiz.prestamo.cliente.nombre).append(", Libro: ").append(raiz.prestamo.libro.titulo).append("\n");
            listarPrestamosRec(raiz.derecha, listaPrestamos);
        }
    }
}
