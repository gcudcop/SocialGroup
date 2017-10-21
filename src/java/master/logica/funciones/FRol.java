package master.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import master.logica.entidades.Rol;

public class FRol {

    public static Rol obtenerRolDadoCodigo(int idRol) throws Exception {
        Rol rol = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_admin.f_obtener_rol_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idRol);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rol = new Rol();
                rol.setIdRol(resultSet.getInt("sr_id_rol"));
                rol.setRol(resultSet.getString("chv_rol"));
                rol.setDescripcion(resultSet.getString("chv_descripcion"));
                rol.setIcono(resultSet.getString("chv_icono"));
                rol.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                rol.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                rol.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                rol.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return rol;
    }

}
