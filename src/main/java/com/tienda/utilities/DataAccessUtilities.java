package com.tienda.utilities;

import com.tienda.data_access_layer.MySqlConnectionFactory;
import com.tienda.entity.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.function.Function;

public class DataAccessUtilities {

    public static final AlertsClass alerta = AlertsClass.getAlert();

    public <T> T getByIdGeneric(int id, String tableName) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (Connection connection = new MySqlConnectionFactory().getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    T rowData = (T) getRowGeneric(tableName, connection).apply(resultSet);
                    return rowData;
                }
            }
        }
        return null;
    }

    public <T> void registrarGeneric(String tableName, T entity) throws ClassNotFoundException, SQLException {
        try (Connection connection = new MySqlConnectionFactory().getConnection()) {
            String query = generateInsertQuery(tableName, connection); // Generar la consulta de inserción
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                setInsertStatementValues(statement, entity);
                statement.executeUpdate();
            }
        }
    }

    public <T> java.util.List<T> listarGeneric(String tableName) throws ClassNotFoundException, SQLException {
        java.util.List<T> lista = new ArrayList<>();
        try (Connection connection = new MySqlConnectionFactory().getConnection()) {
            String query = "SELECT * FROM " + tableName;
            try (PreparedStatement statement = connection.prepareStatement(query); ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    T rowData = (T) getRowGeneric(tableName, connection).apply(result);
                    lista.add(rowData);
                }
            }
        }
        return lista;
    }

    public <T> void actualizarGeneric(String tableName, int id, T entity) throws ClassNotFoundException, SQLException {
        try (Connection connection = new MySqlConnectionFactory().getConnection()) {
            String query = generateUpdateQuery(tableName, connection);
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                setUpdateStatementValues(statement, entity);
                statement.executeUpdate();
            }
        }
    }

    public void eliminarGeneric(String tableName, int id) throws ClassNotFoundException, SQLException {
        try (Connection connection = new MySqlConnectionFactory().getConnection()) {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        }
    }

    public Function<ResultSet, ?> getRowGeneric(String nameTable, Connection con) throws SQLException, ClassNotFoundException {
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
                    case "productos":
                        return new Producto((int) row[0], row[1].toString(), (int) row[2], (double) row[3], (double) row[4], row[5].toString());
                    default:
                        return null;
                }

            } catch (SQLException e) {
                alerta.manejarErrorConexion(this.getClass(), e);
                return null;
            }
        };
    }

    public String[] getProperties() {
        Properties propiedades = new Properties();
        FileInputStream entrada = null;
        try {
            entrada = new FileInputStream("src/main/resources/properties/config.properties");
            propiedades.load(entrada);

            String host = propiedades.getProperty("db.host");
            String port = propiedades.getProperty("db.port");
            String database = propiedades.getProperty("db.database");
            String user = propiedades.getProperty("db.user");
            String password = propiedades.getProperty("db.password");

            return new String[]{host, port, database, user, password};
        } catch (IOException ex) {
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    private String[] getTableColumns(String tableName, Connection con) throws SQLException, ClassNotFoundException {
        java.util.List<String> columns = new ArrayList<>();
        DatabaseMetaData metaData = con.getMetaData();

        try (ResultSet resultSet = metaData.getColumns(con.getCatalog(), null, tableName, null)) {
            while (resultSet.next()) {
                columns.add(resultSet.getString("COLUMN_NAME"));
            }
        }

        return columns.toArray(String[]::new);
    }

    private <T> void setInsertStatementValues(PreparedStatement statement, T entity) throws SQLException {

        if (entity instanceof User user) {
            statement.setInt(1, 0); //Porque es autoincrementable
            statement.setString(2, user.getNombreCompleto());
            statement.setString(3, user.getUsername());
            statement.setBytes(4, user.getHashed_password());
            statement.setBytes(5, user.getSalt());
        } else if (entity instanceof Producto producto) {
            statement.setInt(1, 0); //Porque es autoincrementable
            statement.setString(2, producto.getNombre());
            statement.setInt(3, producto.getProveedor());
            statement.setDouble(4, producto.getCantidad());
            statement.setDouble(5, producto.getPrecio());
            statement.setString(6, producto.getMedida());
        }
    }

    private <T> void setUpdateStatementValues(PreparedStatement statement, T entity) throws SQLException {

        if (entity instanceof User user) {
            statement.setString(1, user.getNombreCompleto());
            statement.setString(2, user.getUsername());
            statement.setBytes(3, user.getHashed_password());
            statement.setBytes(4, user.getSalt());
            statement.setInt(5, user.getId());
        } else if (entity instanceof Producto producto) {
            statement.setString(1, producto.getNombre());
            statement.setInt(2, producto.getProveedor());
            statement.setDouble(3, producto.getCantidad());
            statement.setDouble(4, producto.getPrecio());
            statement.setString(5, producto.getMedida());
            statement.setInt(6, producto.getId());
        }
    }

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

}
