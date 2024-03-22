package com.tienda.utilities;

import com.tienda.data_access_layer.MySqlConnectionFactory;
import com.tienda.entity.Productos;
import com.tienda.entity.User;
import java.awt.*;
import java.awt.event.*;
import java.security.*;
import java.sql.*;
import java.util.*;
import java.util.function.Function;
import javax.swing.*;

/**
 * Clase base para funcionalidades generales compartidas.
 */
public class CommonUtilities {

    public static final Image IMG;
//    public static final Color COLORERROR = new Color(255, 153, 153);

    static {
        Image tempImg = null;
        try {
            tempImg = Toolkit.getDefaultToolkit().getImage(CommonUtilities.class.getResource("/images/logo.jpg"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        IMG = tempImg;
    }

    // Objeto para mostrar alertas
    public static final AlertsClass alerta = AlertsClass.getAlert();

    /**
     * Método para obtener un icono de un archivo de imagen.
     *
     * @param path Ruta del archivo de imagen.
     * @param width Ancho deseado del icono.
     * @param height Altura deseada del icono.
     * @return Objeto Icon con la imagen escalada.
     */
    public static Icon icono(String path, int width, int height) {
        Image img = new ImageIcon(CommonUtilities.class.getResource(path))
                .getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        Icon ic = new ImageIcon(img);
        return ic;
    }

    /**
     * Método para validar y restringir la entrada de caracteres a letras.
     *
     * @param evt Evento de tecla.
     * @param txt Texto actual en el campo.
     * @param max Número máximo de caracteres permitidos.
     */
    public void ValidarSoloLetras(KeyEvent evt, String txt, short max) {
        int key = evt.getKeyChar();
        boolean uppercase = key >= 65 && key <= 90;
        boolean lowercase = key >= 97 && key <= 122;
        boolean space = key == 32;
        if (!(uppercase || lowercase || space) || txt.length() >= max) {
            evt.consume();
        }
    }

    /**
     * Método para validar y restringir la entrada de caracteres a números.
     *
     * @param evt Evento de tecla.
     * @param txt Texto actual en el campo.
     * @param max Número máximo de caracteres permitidos.
     */
    public void validarSoloNumeros(KeyEvent evt, String txt, short max) {
        int key = evt.getKeyChar();
        boolean numbers = key >= 48 && key <= 57;
        if (!numbers || (txt.length() >= max)) {
            evt.consume();
        }
    }

    /**
     * Método para validar y restringir la entrada de caracteres a números
     * decimales.
     *
     * @param evt Evento de tecla.
     * @param txt Texto actual en el campo.
     */
    public static void validarValorDecimal(KeyEvent evt, String txt) {
        char key = evt.getKeyChar();
        boolean numbers = key >= '0' && key <= '9';
        if (key == '.') {
            if (txt.length() == 0) {
                evt.consume();
            } else {
                int counter = 0;
                for (int i = 0; i < txt.length(); i++) {
                    if (txt.charAt(i) == '.') {
                        counter++;
                    }
                }
                if (counter > 0) {
                    evt.consume();
                }
            }
        } else {
            if (!numbers || (txt.length() >= 11)) {
                evt.consume();
            }
        }
    }

    /**
     * Método para limitar el número máximo de dígitos en un campo.
     *
     * @param evt Evento de tecla.
     * @param txt Texto actual en el campo.
     * @param max Número máximo de dígitos permitidos.
     */
    public void validarLength(KeyEvent evt, String txt, short max) {
        if (txt.length() == max) {
            evt.consume();
        }
    }

    /**
     * Convierte una matriz de bytes en una representación de cadena
     * hexadecimal.
     *
     * @param bytes La matriz de bytes que se va a convertir.
     * @return La representación de cadena hexadecimal de la matriz de bytes.
     */
    public String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Hashea una contraseña utilizando el algoritmo SHA-256 con la salt
     * proporcionada.
     *
     * @param password La contraseña que se va a hashear.
     * @param salt El valor de salt para mejorar la seguridad.
     * @return La contraseña hasheada como una matriz de bytes.
     */
    public byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            return hashedPassword;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Genera una salt aleatoria.
     *
     * @return La salt generada.
     */
    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public void Close(JFrame t) {

        // Eliminar cualquier WindowListener existente
        for (WindowListener wl : t.getWindowListeners()) {
            t.removeWindowListener(wl);
        }

        // Agregar un nuevo WindowListener para confirmar la salida de la aplicación
        t.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (alerta.confirmacion("¿Salir de la aplicación?") == 0) {
                    System.exit(0);
                }
            }
        });

    }

    /**
     * Obtiene un objeto por su ID.
     *
     * @param id ID del objeto a buscar.
     * @param tableName Nombre de la tabla en la base de datos.
     * @return El objeto encontrado.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    public <T> T getByIdGeneric(int id, String tableName) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (Connection connection = new MySqlConnectionFactory().getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Extraer los datos de la fila del ResultSet usando la función de extracción
                    T rowData = (T) getExtractFunctionRow(tableName, connection).apply(resultSet);
                    // Mapear los datos extraídos a un objeto DTO usando la función de mapeo
                    return rowData;
                }
            }
        }
        return null; // Devolver nulo si no se encuentra el objeto
    }

    /**
     * Registra un nuevo objeto en la base de datos.
     *
     * @param <T>
     * @param tableName Nombre de la tabla en la base de datos.
     * @param entity Objeto que se registrará en la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public <T> void Registrar(String tableName, T entity) throws ClassNotFoundException, SQLException {
        try (Connection connection = new MySqlConnectionFactory().getConnection()) {
            String query = generateInsertQuery(tableName, connection); // Generar la consulta de inserción
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Establecer los valores en la consulta preparada utilizando métodos específicos del DTO
                setInsertStatementValues(statement, entity);
                // Ejecutar la consulta de inserción
                statement.executeUpdate();
            }
        }
    }

    /**
     * Obtiene una lista de todos los objetos de una tabla en la base de datos.
     *
     * @param <T>
     * @param tableName Nombre de la tabla en la base de datos.
     * @return Lista de objetos DTO.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public <T> java.util.List<T> Listar(String tableName) throws ClassNotFoundException, SQLException {
        java.util.List<T> lista = new ArrayList<>();
        try (Connection connection = new MySqlConnectionFactory().getConnection()) {
            String query = "SELECT * FROM " + tableName;
            try (PreparedStatement statement = connection.prepareStatement(query); ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    // Extraer los datos de la fila del ResultSet usando la función de extracción
                    T rowData = (T) getExtractFunctionRow(tableName, connection).apply(result);
                    // Agregar el T a la lista
                    lista.add(rowData);
                }
            }
        }
        return lista;
    }

    public <T> void Actualizar(String tableName, int id, T entity) throws ClassNotFoundException, SQLException {
        try (Connection connection = new MySqlConnectionFactory().getConnection()) {
            String query = generateUpdateQuery(tableName, connection);
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Establecer los valores en la consulta preparada
                setUpdateStatementValues(statement, entity);
                // Ejecutar la consulta de actualización
                statement.executeUpdate();
            }
        }
    }
    /**
     * Elimina un objeto de la base de datos.
     *
     * @param tableName Nombre de la tabla en la base de datos.
     * @param id ID del objeto que se eliminará.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void Eliminar(String tableName, int id) throws ClassNotFoundException, SQLException {
        try (Connection connection = new MySqlConnectionFactory().getConnection()) {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id); // Establecer el ID en la condición WHERE
                // Ejecutar la consulta de eliminación
                statement.executeUpdate();
            }
        }
    }

    public Function<ResultSet, ?> getExtractFunctionRow(String nameTable, Connection con) throws SQLException, ClassNotFoundException {
        String[] tableColumns = getTableColumns(nameTable, con);
        return (ResultSet resultSetReceived) -> {
            try {
                Object[] row = new Object[tableColumns.length];
                for (int i = 0; i < tableColumns.length; i++) {
                    row[i] = resultSetReceived.getObject(i + 1);
                }

                switch (nameTable) {
                    case "users":
                        return new User((int) row[0], row[1].toString(), row[2].toString(), (byte[]) row[3], (byte[]) row[4]);
                    default:
                        return null;
                }

            } catch (SQLException e) {
                alerta.manejarErrorConexion(this.getClass(), e);
                return null;
            }
        };
    }

    /**
     * Obtiene los nombres de las columnas de una tabla en la base de datos.
     *
     * @param tableName Nombre de la tabla.
     * @return Array de String con los nombres de las columnas.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    public String[] getTableColumns(String tableName, Connection con) throws SQLException, ClassNotFoundException {
        java.util.List<String> columns = new ArrayList<>();
        DatabaseMetaData metaData = con.getMetaData();

        // Obtener las columnas de la tabla
        try (ResultSet resultSet = metaData.getColumns(con.getCatalog(), null, tableName, null)) {
            while (resultSet.next()) {
                columns.add(resultSet.getString("COLUMN_NAME"));
            }
        }

        return columns.toArray(String[]::new);
    }

    /**
     * Establece los valores en una consulta preparada basándose en un objeto
     * DTO.
     *
     * @param statement Consulta preparada.
     * @param entity Objeto DTO.
     * @throws SQLException Si ocurre un error al establecer los valores en la
     * consulta preparada.
     */
    private <T> void setInsertStatementValues(PreparedStatement statement, T entity) throws SQLException {

        if (entity instanceof User user) {
            statement.setInt(1, 0); //Porque es autoincrementable
            statement.setString(2, user.getNombreCompleto());
            statement.setString(3, user.getUsername());
            statement.setBytes(4, user.getHashed_password());
            statement.setBytes(5, user.getSalt());
        } else if (entity instanceof Productos producto) {

        }
    }
    
    private <T> void setUpdateStatementValues(PreparedStatement statement, T entity) throws SQLException {

        if (entity instanceof User user) {
            statement.setString(1, user.getNombreCompleto());
            statement.setString(2, user.getUsername());
            statement.setBytes(3, user.getHashed_password());
            statement.setBytes(4, user.getSalt());
            statement.setInt(5, user.getId());
        } else if (entity instanceof Productos producto) {

        }
    }

    /**
     * Genera una consulta de inserción para una tabla específica.
     *
     * @param tableName Nombre de la tabla.
     * @param numValues Número de valores en la consulta.
     * @return Consulta de inserción generada.
     */
    private String generateInsertQuery(String tableName, Connection con) throws SQLException, ClassNotFoundException {
        int numValues = getTableColumns(tableName, con).length;
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
        queryBuilder.append(tableName).append(" VALUES (");
        for (int i = 0; i < numValues; i++) {
            queryBuilder.append("?");
            if (i < numValues - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(")");
        return queryBuilder.toString();
    }

    /**
     * Genera una consulta de actualización para una tabla específica.
     *
     * @param tableName Nombre de la tabla.
     * @return Consulta de actualización generada.
     * @throws SQLException Si ocurre un error al obtener los nombres de las
     * columnas de la tabla.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    private String generateUpdateQuery(String tableName, Connection con) throws SQLException, ClassNotFoundException {
        String[] columnNames = getTableColumns(tableName, con);
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE ").append(tableName).append(" SET ");
        for (int i = 1; i < columnNames.length; i++) { // Comienza desde la segunda columna
            queryBuilder.append(columnNames[i]).append(" = ?");
            if (i < columnNames.length - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(" WHERE id = ?");
        return queryBuilder.toString();
    }

    /**
     * Extrae una fila de datos de un ResultSet y la mapea a un objeto
     * intermedio utilizando una función de mapeo.
     *
     * @param resultSet ResultSet que contiene los datos.
     * @return Objeto intermedio mapeado.
     * @throws SQLException Si ocurre un error al acceder a los datos del
     * ResultSet.
     */
    public Object[] extractRowFromResultSet(ResultSet resultSet, String tableName, Connection con) throws SQLException, ClassNotFoundException {
        int numColumnas = getTableColumns(tableName, con).length;
        Object[] fila = new Object[numColumnas];
        for (int i = 0; i < numColumnas; i++) {
            fila[i] = resultSet.getObject(i + 1);
        }
        return fila;
    }

}
