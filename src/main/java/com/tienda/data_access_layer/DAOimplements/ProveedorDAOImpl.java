package com.tienda.data_access_layer.DAOImplements;

import com.tienda.data_access_layer.ProveedorDAO;
import com.tienda.entity.Proveedor;
import com.tienda.utilities.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class ProveedorDAOImpl extends DataAccessUtilities implements ProveedorDAO, Serializable {

    private final Proveedor proveedor;
    private static final String NAMETABLE = "proveedores";

    /**
     * Constructor que inicializa la instancia del DAO con un objeto de usuario.
     *
     * @param proveedor El proveedor asociado al DAO.
     */
    public ProveedorDAOImpl(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public Proveedor getById() throws SQLException, ClassNotFoundException {
        return getByIdGeneric(proveedor.getId(), NAMETABLE);
    }

    @Override
    public boolean registrar() throws ClassNotFoundException, SQLException {
        return registrarGeneric(NAMETABLE, proveedor);
    }

    @Override
    public List<Proveedor> listar() throws ClassNotFoundException, SQLException {
        return listarGeneric(NAMETABLE);
    }

    @Override
    public boolean actualizar() throws ClassNotFoundException, SQLException {
        return actualizarGeneric(NAMETABLE, proveedor.getId(), proveedor);
    }

    @Override
    public boolean eliminar() throws ClassNotFoundException, SQLException {
        return eliminarGeneric(NAMETABLE, proveedor.getId());
    }

}
