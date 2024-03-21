package com.tienda.data_access_layer;

import com.tienda.data_transfer_layer.UserDTO;
import com.tienda.entity.User;
import java.sql.*;
import java.util.*;
import java.util.function.Function;

/**
 * Interfaz CRUD para operaciones básicas de acceso a datos.
 *
 * @param <T> Tipo de objeto DTO
 * @param <G> Tipo de objeto Entity
 */
public abstract class CRUD<T, G> extends MySqlConnectionFactory {

    /**
     * Obtiene un objeto por su ID.
     *
     * @param id ID del objeto a buscar.
     * @param tableName Nombre de la tabla en la base de datos.
     * @param extractFunction Función para extraer los datos del ResultSet y
     * mapearlos a un objeto Entity.
     * @param mapFunction Función para mapear el objeto Entity a un objeto DTO.
     * @return El objeto encontrado.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    public T GetById(int id, String tableName, Function<ResultSet, G> extractFunction, Function<G, T> mapFunction) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        connectionFactory = new MySqlConnectionFactory();
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Extraer los datos de la fila del ResultSet usando la función de extracción
                    G rowData = extractFunction.apply(resultSet);
                    // Mapear los datos extraídos a un objeto DTO usando la función de mapeo
                    return mapFunction.apply(rowData);
                }
            }
        }
        return null; // Devolver nulo si no se encuentra el objeto
    }

    /**
     * Registra un nuevo objeto en la base de datos.
     *
     * @param tableName Nombre de la tabla en la base de datos.
     * @param dto Objeto DTO que se registrará en la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public <T> void Registrar(String tableName, T dto) throws ClassNotFoundException, SQLException {
        connectionFactory = new MySqlConnectionFactory();
        try (Connection connection = connectionFactory.getConnection()) {
            String query = generateInsertQuery(tableName, getTableColumns(tableName).length); // Generar la consulta de inserción
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Establecer los valores en la consulta preparada utilizando métodos específicos del DTO
                setStatementValues(statement, dto);
                // Ejecutar la consulta de inserción
                statement.executeUpdate();
            }
        }
    }

    /**
     * Obtiene una lista de todos los objetos de una tabla en la base de datos.
     *
     * @param tableName Nombre de la tabla en la base de datos.
     * @param columns Array de nombres de las columnas de la tabla.
     * @param extractFunction Función para extraer los datos del ResultSet y
     * mapearlos a un objeto Entity.
     * @param mapFunction Función para mapear el objeto Entity a un objeto DTO.
     * @return Lista de objetos DTO.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public <DTO, Entity> List<DTO> Listar(String tableName, Entity entidad) throws ClassNotFoundException, SQLException {
        List<DTO> lista = new ArrayList<>();
        connectionFactory = new MySqlConnectionFactory();
        try (Connection connection = connectionFactory.getConnection()) {
            String query = "SELECT * FROM " + tableName;
            try (PreparedStatement statement = connection.prepareStatement(query); ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Function<ResultSet, Entity> extractFunction = (Function<ResultSet, Entity>) getExtractFunctionRow(tableName, (G) entidad);
                    // Extraer los datos de la fila del ResultSet usando la función de extracción
                    Entity rowData = extractFunction.apply(result);
                    System.out.println(rowData);
                    Function<Entity, DTO> mapFunction = (Function<Entity, DTO>) getMapFunctionRow((G) entidad);
                    // Mapear los datos extraídos a un objeto DTO usando la función de mapeo
                    DTO dto = mapFunction.apply(rowData);
                    // Agregar el DTO a la lista
                    lista.add(dto);
                }
            }
        }
        return lista;
    }

    /**
     * Actualiza un objeto en la base de datos.
     *
     * @param tableName Nombre de la tabla en la base de datos.
     * @param id ID del objeto que se actualizará.
     * @param dto Objeto DTO con los nuevos datos.
     * @param mapFunction Función para mapear el objeto DTO a un array de
     * valores para la consulta preparada.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public <D> boolean Actualizar(String tableName, int id, D dto, Function<D, Object[]> mapFunction) throws ClassNotFoundException, SQLException {
        connectionFactory = new MySqlConnectionFactory();
        try (Connection connection = connectionFactory.getConnection()) {
            String query = generateUpdateQuery(tableName);
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Establecer los valores en la consulta preparada
                Object[] values = mapFunction.apply(dto);
                for (int i = 0; i < values.length; i++) {
                    statement.setObject(i + 1, values[i]);
                }
                statement.setInt(values.length + 1, id); // Establecer el ID en la condición WHERE
                // Ejecutar la consulta de actualización
                int rowsAffected = statement.executeUpdate();
                // Verificar si la actualización fue exitosa
                return rowsAffected > 0;
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
        connectionFactory = new MySqlConnectionFactory();
        try (Connection connection = connectionFactory.getConnection()) {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id); // Establecer el ID en la condición WHERE
                // Ejecutar la consulta de eliminación
                statement.executeUpdate();
            }
        }
    }

    public Function<ResultSet, G> getExtractFunctionRow(String nameTable, G Entity) throws SQLException, ClassNotFoundException {
        String[] tableColumns = getTableColumns(nameTable);
        return (ResultSet resultSet) -> {
            try {
                Object[] row = new Object[tableColumns.length];
                for (int i = 0; i < tableColumns.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                
                if (Entity instanceof User) {
                    return (G) new User((int) row[0], row[1].toString(), row[2].toString(), (byte[]) row[3], (byte[]) row[4]);
                }

            } catch (SQLException e) {
                alerta.manejarErrorConexion(this.getClass(), e);
                return null;
            }
            return null;
        };
    }

    public Function<G, T> getMapFunctionRow(G entity) {

        if (entity instanceof User user) {
            return userDTO -> (T) new UserDTO(user);
        }
        return null;
//        return user -> new UserDTO(user);
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
    public String[] getTableColumns(String tableName) throws SQLException, ClassNotFoundException {
        // Obtener los metadatos de la tabla
        objConnection = new MySqlConnectionFactory().getConnection();
        DatabaseMetaData metaData = objConnection.getMetaData();
        List<String> columns = new ArrayList<>();

        // Obtener las columnas de la tabla
        try (ResultSet resultSet = metaData.getColumns(getBdName(), null, tableName, null)) {
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
     * @param dto Objeto DTO.
     * @throws SQLException Si ocurre un error al establecer los valores en la
     * consulta preparada.
     */
    private <T> void setStatementValues(PreparedStatement statement, T dto) throws SQLException {

        if (dto instanceof UserDTO userDTO) {
            statement.setInt(1, 0); //Porque es autoincrementable
            statement.setString(2, userDTO.getNombreCompleto());
            statement.setString(3, userDTO.getUser());
            statement.setBytes(4, userDTO.getUsuario().getHashed_password());
            statement.setBytes(5, userDTO.getUsuario().getSalt());
        }
    }

    /**
     * Genera una consulta de inserción para una tabla específica.
     *
     * @param tableName Nombre de la tabla.
     * @param numValues Número de valores en la consulta.
     * @return Consulta de inserción generada.
     */
    private String generateInsertQuery(String tableName, int numValues) {
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
    private String generateUpdateQuery(String tableName) throws SQLException, ClassNotFoundException {
        String[] columnNames = getTableColumns(tableName);
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
     * @param columnas Nombres de las columnas.
     * @param mapFunction Función de mapeo.
     * @return Objeto intermedio mapeado.
     * @throws SQLException Si ocurre un error al acceder a los datos del
     * ResultSet.
     */
    public <E> E extractRowFromResultSet(ResultSet resultSet, String[] columnas, Function<Object[], E> mapFunction) throws SQLException {
        int numColumnas = columnas.length;
        Object[] fila = new Object[numColumnas];
        for (int i = 0; i < numColumnas; i++) {
            fila[i] = resultSet.getObject(i + 1);
        }
        // Mapear la fila de datos a un objeto intermedio usando la función de mapeo
        return mapFunction.apply(fila);
    }

    /**
     * Extrae datos de un ResultSet y los mapea a un objeto Entity específico.
     *
     * @param resultSet ResultSet que contiene los datos.
     * @return Objeto Entity mapeado.
     * @throws SQLException Si ocurre un error al acceder a los datos del
     * ResultSet.
     */
    public abstract G extractFromResultSet(ResultSet resultSet) throws SQLException;
}
