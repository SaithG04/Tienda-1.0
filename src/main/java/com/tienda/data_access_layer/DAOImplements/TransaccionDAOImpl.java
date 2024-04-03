package com.tienda.data_access_layer.DAOImplements;

import com.tienda.data_access_layer.TransaccionDAO;
import com.tienda.entity.Transaccion;
import com.tienda.utilities.DataAccessUtilities;
import java.sql.SQLException;
import java.util.List;


public class TransaccionDAOImpl extends DataAccessUtilities implements TransaccionDAO {

    private Transaccion transaccion;
    private static final String NAMETABLE = "transacciones";

    public TransaccionDAOImpl(Transaccion transaccion) {
        this.transaccion = transaccion;
    }
    
    @Override
    public void setEntity(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    @Override
    public Transaccion getById() throws SQLException, ClassNotFoundException {
        return getByIdGeneric(transaccion.getId(), NAMETABLE);
    }

    @Override
    public boolean registrar() throws ClassNotFoundException, SQLException {
        return registrarGeneric(NAMETABLE, transaccion);
    }

    @Override
    public List<Transaccion> listar() throws ClassNotFoundException, SQLException {
        return listarGeneric(NAMETABLE);
    }

    @Override
    public boolean actualizar() throws ClassNotFoundException, SQLException {
        return actualizarGeneric(NAMETABLE, transaccion);
    }

    @Override
    public boolean eliminar() throws ClassNotFoundException, SQLException {
        return eliminarGeneric(NAMETABLE, transaccion.getId());
    }
    
}
