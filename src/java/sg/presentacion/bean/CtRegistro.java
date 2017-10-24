package sg.presentacion.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import master.logica.entidades.TipoPersona;
import master.logica.funciones.FTipoPersona;
import recursos.Util;
import sg.logica.entidades.Cuenta;

@ManagedBean
@ViewScoped
public class CtRegistro implements Serializable {

    private Cuenta objCuenta;
    private List<TipoPersona> lstTiposPersonas;

    public CtRegistro() {
        objCuenta = new Cuenta();
        obtenerTiposPersonas();
    }

    public void obtenerTiposPersonas() {
        try {
            setLstTiposPersonas(FTipoPersona.obtenerTiposPersonaDadoEstado("A"));
        } catch (Exception e) {
            System.out.println("public void obtenerTiposPersonas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    /**
     * @return the objCuenta
     */
    public Cuenta getObjCuenta() {
        return objCuenta;
    }

    /**
     * @param objCuenta the objCuenta to set
     */
    public void setObjCuenta(Cuenta objCuenta) {
        this.objCuenta = objCuenta;
    }

    /**
     * @return the lstTiposPersonas
     */
    public List<TipoPersona> getLstTiposPersonas() {
        return lstTiposPersonas;
    }

    /**
     * @param lstTiposPersonas the lstTiposPersonas to set
     */
    public void setLstTiposPersonas(List<TipoPersona> lstTiposPersonas) {
        this.lstTiposPersonas = lstTiposPersonas;
    }

}
