package sg.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.funciones.FUsuario;
import sg.logica.entidades.FormaPago;

public class FFormaPago {

    public static List<FormaPago> obtenerFormasPagoActivas() throws Exception {
        List<FormaPago> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        FormaPago formaPago;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_social_group.f_obtener_formas_pago_activas();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                formaPago = new FormaPago();
                formaPago.setIdFormaPago(resultSet.getInt("sr_id_forma_pago"));
                formaPago.setFormaPago(resultSet.getString("chv_forma_pago"));
                formaPago.setDescripcion(resultSet.getString("chv_descripcion"));
                formaPago.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                formaPago.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                formaPago.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                formaPago.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
                lst.add(formaPago);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
