package sg.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.funciones.FUsuario;
import sg.logica.entidades.EstadoCuenta;

public class FEstadoCuenta {

    public static List<EstadoCuenta> obtenerEstadosCuentaActivas() throws Exception {
        List<EstadoCuenta> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        EstadoCuenta ec;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_social_group.f_obtener_estados_cuenta();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                ec = new EstadoCuenta();
                ec.setIdEstadoCuenta(resultSet.getInt("sr_id_estado_cuenta"));
                ec.setEstado(resultSet.getString("chv_estado"));
                ec.setDescripcion(resultSet.getString("chv_descripcion"));
                ec.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                ec.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                ec.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                ec.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
                lst.add(ec);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
