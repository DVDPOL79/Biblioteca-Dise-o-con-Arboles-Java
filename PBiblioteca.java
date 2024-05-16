import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PBiblioteca { 
    private final Libro[] libros;
    private final Usuario[] usuarios;
    private int numLibros;
    private int numUsuarios;
    private final int MAX_LIBROS_PRESTADOS = 5;
    public PBiblioteca() {
        this.libros = new Libro[100]; // Tamaño inicial del array de libros
        this.usuarios = new Usuario[100]; // Tamaño inicial del array de usuarios
        this.numLibros = 0;
        this.numUsuarios = 0;
    }
    // Clase interna Libro
    private static class Libro {
        private final String id;
        private String titulo;
        private String autor;
        private final LocalDateTime fechaRegistro;
        private LocalDateTime fechaPrestamo;
        private boolean disponible;
        public Libro(String id, String titulo, String autor, LocalDateTime fechaRegistro) {
            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
            this.fechaRegistro = fechaRegistro;
            this.fechaPrestamo = null;
            this.disponible = true;
        }
    // Métodos de acceso
    public boolean isDisponible() {
        return disponible;
    }
       //Agregamos un método para establecer la fecha de préstamo cuando se presta el libro
        public void prestar() {
    this.disponible = false;
    this.fechaPrestamo = LocalDateTime.now();
}
        public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }
        public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
        public String getId() {
            return id;
        }
        public String getTitulo() {
            return titulo;
        }
        public String getAutor() {
            return autor;
        }
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }
        public void setAutor(String autor) {
            this.autor = autor;
        }
        @Override
        public String toString() {
            return "ID: " + id + ", Título: " + titulo + ", Autor: " + autor;
        }
    }
    // Clase interna Usuario
    public class Usuario {
    private String idUsuario;
    private String nombre;
    private LocalDateTime fechaRegistro;
    private int numLibrosPrestados;
    private final List<Libro> librosPrestados;
    public Usuario(String idUsuario, String nombre, LocalDateTime fechaRegistro) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
        this.numLibrosPrestados = 0; // Al crear un usuario, no tiene libros prestados
        this.librosPrestados = new ArrayList<>();
    }
        public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }
        // Métodos de acceso
        public int getNumLibrosPrestados() {
        return numLibrosPrestados;
    }
        public void agregarLibroPrestado(Libro libro) {
        librosPrestados.add(libro);
    }
        public void setNumLibrosPrestados(int numLibrosPrestados) {
        this.numLibrosPrestados = numLibrosPrestados;
    }
        public boolean devolverLibro(String id) {
    for (int i = 0; i < librosPrestados.size(); i++) {
        if (librosPrestados.get(i).getId().equals(id)) {
            librosPrestados.remove(i);
            return true;
        }
    }
    return false;
}
        // Getters y setters
        public String getIdUsuario() {
            return idUsuario;
        }
        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public LocalDateTime getFechaRegistro() {
            return fechaRegistro;
        }
        public void setFechaRegistro(LocalDateTime fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
        }
    }
    //registrar libros
    public void registrarLibro(String id, String titulo, String autor, LocalDateTime fechaRegistro) {
        if (buscarLibro(id) == null) {
            if (fechaRegistro == null) {
                fechaRegistro = LocalDateTime.now();
            }
            Libro libro = new Libro(id, titulo, autor, fechaRegistro);
            libros[numLibros++] = libro;
            JOptionPane.showMessageDialog(null, "Libro registrado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Este libro ya está registrado.");
        }
    }
    //listar libros
    public void listarLibros() {
        if (numLibros > 0) {
            StringBuilder mensaje = new StringBuilder("\nLista de libros:\n");
            for (int i = 0; i < numLibros; i++) {
                mensaje.append(libros[i]).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No hay libros registrados en la biblioteca.");
        }
    }
    //actualizar libros
    public void actualizarLibro(String id, String titulo, String autor) {
        Libro libro = buscarLibro(id);
        if (libro != null) {
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            JOptionPane.showMessageDialog(null, "Libro actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Este libro no está registrado.");
        }
    }
    //eliminar libros
    public void eliminarLibro(String id) {
        for (int i = 0; i < numLibros; i++) {
            if (libros[i].getId().equals(id)) {
                for (int j = i; j < numLibros - 1; j++) {
                    libros[j] = libros[j + 1];
                }
                libros[numLibros - 1] = null;
                numLibros--;
                JOptionPane.showMessageDialog(null, "Libro eliminado correctamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Error: Este libro no está registrado.");
    }
    // buscar libros
    private Libro buscarLibro(String id) {
        for (int i = 0; i < numLibros; i++) {
            if (libros[i].getId().equals(id)) {
                return libros[i];
            }
        }
        return null;
    }
    //metodo principal
    public static void main(String[] args) {
        PBiblioteca biblioteca = new PBiblioteca();
        biblioteca.mostrarMenuPrincipal();
    }
    //menu principal
    public void mostrarMenuPrincipal() {
        String menu = """
                      Bienvenido al Sistema de Gestión de Biblioteca
                      
                      Por favor, seleccione una opción:
                      1. Gestionar libros (Registrar, Actualizar, Eliminar, Listar)
                      2. Gestionar usuarios (Registrar, Actualizar, Eliminar, Listar)
                      3. Prestar libro
                      4. Devolver libro
                      5. Mostrar estadísticas
                      6. Salir""";

        boolean continuar = true;
        while (continuar) {
            String opcion = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.PLAIN_MESSAGE);

            if (opcion == null) {
                JOptionPane.showMessageDialog(null, "¡Hasta luego!", "Salir", JOptionPane.INFORMATION_MESSAGE);
                continuar = false;
            } else {

                switch (opcion) {
                    case "1" -> mostrarMenuLibros();
                    case "2" -> mostrarMenuUsuarios();
                    case "3" -> {
                        String id = JOptionPane.showInputDialog(null, "Ingrese el id del libro:");
                        String idUsuario = JOptionPane.showInputDialog(null, "Ingrese el ID del usuario:");
                        prestarLibro(id, idUsuario);
                    }
                    case "4" -> {
                        String id = JOptionPane.showInputDialog(null, "Ingrese el id del libro:");
                        String idUsuario = JOptionPane.showInputDialog(null, "Ingrese el ID del usuario:");
                        devolverLibro(id, idUsuario);
                    }
                    case "5" -> mostrarEstadisticas();
                    case "6" -> {
                        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                        continuar = false;
                        break;
                    }
                    default -> JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
                }
            }
        }
    }
    //submenu para mostrar la gestion de libros CRUD
    public void mostrarMenuLibros() {
        OUTER:
        while (true) {
            String opcion = JOptionPane.showInputDialog(null, """
                                                                      *** Gestionar Libros ***
                                                                      1. Listar libros
                                                                      2. Registrar libro
                                                                      3. Actualizar libro
                                                                      4. Eliminar libro
                                                                      5. Volver al menú principal
                                                                      Ingrese su opción:""");
            switch (opcion) {
                case "1" -> listarLibros();
                case "2" -> {
                    String id = JOptionPane.showInputDialog(null, "Ingrese el id del libro:");
                    String titulo = JOptionPane.showInputDialog(null, "Ingrese el título del libro:");
                    String autor = JOptionPane.showInputDialog(null, "Ingrese el autor del libro:");
                    registrarLibro(id, titulo, autor, null);
                }
                case "3" -> {
                    String id = JOptionPane.showInputDialog(null, "Ingrese el id del libro a actualizar:");
                    String titulo = JOptionPane.showInputDialog(null, "Ingrese el nuevo título del libro:");
                    String autor = JOptionPane.showInputDialog(null, "Ingrese el nuevo autor del libro:");
                    actualizarLibro(id, titulo, autor);
                }
                case "4" -> {
                    String id = JOptionPane.showInputDialog(null, "Ingrese el id del libro a eliminar:");
                    eliminarLibro(id);
                }
                case "5" -> {
                    break OUTER;
                }
                default -> JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }
    //submenu para mostrar la gestion de usuarios CRUD
    public void mostrarMenuUsuarios() {
        OUTER:
        while (true) {
            String opcion = JOptionPane.showInputDialog(null, """
                                                                      *** Gestionar Usuarios ***
                                                                      1. Listar usuarios
                                                                      2. Registrar usuario
                                                                      3. Actualizar usuario
                                                                      4. Eliminar usuario
                                                                      5. Volver al menú principal
                                                                      Ingrese su opción:""");
            switch (opcion) {
                case "1" -> listarUsuarios();
                case "2" -> {
                    String idUsuario = JOptionPane.showInputDialog(null, "Ingrese el ID del usuario:");
                    String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del usuario:");
                    registrarUsuario(idUsuario, nombre, null);
                }
                case "3" -> {
                    String idUsuario = JOptionPane.showInputDialog(null, "Ingrese el ID del usuario a actualizar:");
                    String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del usuario:");
                    actualizarUsuario(idUsuario, nombre);
                }
                case "4" -> {
                    String idUsuario = JOptionPane.showInputDialog(null, "Ingrese el ID del usuario a eliminar:");
                    eliminarUsuario(idUsuario);
                }
                case "5" -> {
                    break OUTER;
                }
                default -> JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }
    //metodo donde se va a realizar el prestamo del libro
    public void prestarLibro(String id, String idUsuario) {
    Libro libro = buscarLibro(id);
    Usuario usuario = buscarUsuario(idUsuario);

    if (libro != null && usuario != null) {
        if (libro.isDisponible()) {
            if (usuario.getNumLibrosPrestados() < MAX_LIBROS_PRESTADOS) {
                libro.setDisponible(false);
                usuario.agregarLibroPrestado(libro);
                libro.prestar();
                JOptionPane.showMessageDialog(null, "El libro '" + libro.getTitulo() + "' ha sido prestado a '" + usuario.getNombre() + "'.");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Este usuario ya ha alcanzado el límite máximo de libros prestados.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Este libro ya está prestado.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Error: Libro o usuario no encontrados.");
    }
}
    //metodo donde se va a realizar la devolucion del libro
    public void devolverLibro(String id, String idUsuario) {
    Usuario usuario = buscarUsuario(idUsuario);
    if (usuario != null) {
        Libro libro = buscarLibro(id);
        if (libro != null) {
            boolean libroDevuelto = usuario.devolverLibro(id);
            if (libroDevuelto) {
                libro.setDisponible(true);
                JOptionPane.showMessageDialog(null, "El libro '" + libro.getTitulo() + "' ha sido devuelto.");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Este libro no fue prestado por este usuario.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Libro no encontrado.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Error: Usuario no encontrado.");
    }
}
    //metodo para ralizar la visualizacion de los libros en total, prestados, y usuarios que tiene los libros
    public void mostrarEstadisticas() {
    int totalLibros = getNumLibros();
    int librosPrestados = 0;
    int librosDisponibles = 0;

    for (Libro libro : libros) {
        if (libro != null) {
            if (!libro.isDisponible()) {
                librosPrestados++;
            } else {
                librosDisponibles++;
            }
        }
    }

    StringBuilder mensaje = new StringBuilder();
    mensaje.append("Estadísticas:\n");
    mensaje.append("Total de libros: ").append(totalLibros).append("\n");
    mensaje.append("Libros prestados: ").append(librosPrestados).append("\n");
    mensaje.append("Libros disponibles: ").append(librosDisponibles).append("\n\n");

    mensaje.append("Usuarios con libros prestados:\n");
    for (Usuario usuario : usuarios) {
        if (usuario != null) {
            List<Libro> librosPrestadosUsuario = usuario.getLibrosPrestados();
            if (!librosPrestadosUsuario.isEmpty()) {
                mensaje.append("Nombre de usuario: ").append(usuario.getNombre()).append("\n");
                mensaje.append("Libros prestados:\n");
                for (Libro libro : librosPrestadosUsuario) {
                    mensaje.append("- Título: ").append(libro.getTitulo()).append(", Autor: ").append(libro.getAutor());
                    mensaje.append(", Fecha de préstamo: ").append(libro.getFechaPrestamo()).append("\n");
                }
            }
        }
    }

    JOptionPane.showMessageDialog(null, mensaje.toString(), "Estadísticas de la biblioteca", JOptionPane.PLAIN_MESSAGE);
}
    //subMetodo para contar el numero de libros
    public int getNumLibros() {
    int count = 0;
    for (Libro libro : libros) {
        if (libro != null) {
            count++;
        }
    }
    return count;
}
    // Métodos restantes para la gestión de usuarios
    public void listarUsuarios() {
    if (numUsuarios > 0) {
        StringBuilder mensaje = new StringBuilder("\nLista de usuarios:\n");
        for (int i = 0; i < numUsuarios; i++) {
            mensaje.append("ID: ").append(usuarios[i].getIdUsuario()).append(", Nombre: ").append(usuarios[i].getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No hay usuarios registrados en la biblioteca.");
    }
}
    public void registrarUsuario(String idUsuario, String nombre, LocalDateTime fechaRegistro) {
    // Verificar si el usuario ya está registrado
    boolean usuarioExistente = false;
    for (int i = 0; i < numUsuarios; i++) {
        if (usuarios[i].getIdUsuario().equals(idUsuario)) {
            usuarioExistente = true;
            break;
        }
    }

    // Si el usuario no está registrado, agregarlo al arreglo de usuarios
    if (!usuarioExistente) {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
        Usuario usuario = new Usuario(idUsuario, nombre, fechaRegistro);
        usuarios[numUsuarios++] = usuario;
        JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
    } else {
        JOptionPane.showMessageDialog(null, "Error: Este usuario ya está registrado.");
    }
}
    public void actualizarUsuario(String idUsuario, String nombre) {
        Usuario usuario = buscarUsuario(idUsuario);
        if (usuario != null) {
            usuario.setNombre(nombre);
            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Este usuario no está registrado.");
        }
    }
    public void eliminarUsuario(String idUsuario) {
        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i].getIdUsuario().equals(idUsuario)) {
                for (int j = i; j < numUsuarios - 1; j++) {
                    usuarios[j] = usuarios[j + 1];
                }
                usuarios[numUsuarios - 1] = null;
                numUsuarios--;
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Error: Este usuario no está registrado.");
    }
    public Usuario buscarUsuario(String idUsuario) {
    for (int i = 0; i < numUsuarios; i++) {
        if (usuarios[i].getIdUsuario().equals(idUsuario)) {
            return usuarios[i];
        }
    }
    return null; // Retorna null si el usuario no se encuentra
}
}
