package sg.presentacion.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.funciones.FUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtCuentasLider implements Serializable {

    private List<Usuario> lstCuentasLider;
    private Usuario objCliente;
    private Usuario cuentaSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtCuentasLider() {
        objCliente = new Usuario();
        sessionUsuario = new Usuario();
        cuentaSel = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerCuentas();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos() + " id: " + getSessionUsuario().getIdUsuario());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void nuevaCuenta() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            String msgBd = FUsuario.registrarUsuarioVisitanteDadoLider(objCliente, intIdUsuario);
            Util.addSuccessMessage(msgBd);
            obtenerCuentas();
            objCliente = new Usuario();
            resetearDataTable("frmPrincipal:tblCuentas");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevaCuenta').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void nuevaCuenta() dice: " + e.getMessage());
            System.out.println("public void nuevaCuenta() dice: " + e.getMessage());
        }
    }

    public void resetearDataTable(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    public void obtenerCuentas() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setLstCuentasLider(FUsuario.obtenerUsuariosDadoLider(intIdUsuario));
            Util.addSuccessMessage(lstCuentasLider.size() + " cuentas registradas.");
        } catch (Exception e) {
            System.out.println("public void obtenerCuentas() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * @return the lstCuentasLider
     */
    public List<Usuario> getLstCuentasLider() {
        return lstCuentasLider;
    }

    /**
     * @param lstCuentasLider the lstCuentasLider to set
     */
    public void setLstCuentasLider(List<Usuario> lstCuentasLider) {
        this.lstCuentasLider = lstCuentasLider;
    }

    /**
     * @return the objCliente
     */
    public Usuario getObjCliente() {
        return objCliente;
    }

    /**
     * @param objCliente the objCliente to set
     */
    public void setObjCliente(Usuario objCliente) {
        this.objCliente = objCliente;
    }

    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the faceContext
     */
    public FacesContext getFaceContext() {
        return faceContext;
    }

    /**
     * @param faceContext the faceContext to set
     */
    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

    /**
     * @return the sessionUsuario
     */
    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    /**
     * @return the cuentaSel
     */
    public Usuario getCuentaSel() {
        return cuentaSel;
    }

    /**
     * @param cuentaSel the cuentaSel to set
     */
    public void setCuentaSel(Usuario cuentaSel) {
        this.cuentaSel = cuentaSel;
    }

}
