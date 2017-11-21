package sg.presentacion.bean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.funciones.FUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.ObtenerIPs;
import recursos.Util;
import sg.logica.entidades.Pif;
import sg.logica.funciones.FPif;
import sg.logica.entidades.EstadoCuenta;
import sg.logica.funciones.FEstadoCuenta;

@ManagedBean
@ViewScoped
public class CtPif implements Serializable {
    
    private Pif objPif;
    private Pif PifSel;
    private List<Pif> lstPif;
    private HttpServletRequest httpServletRequest;
    private FacesContext facesContext;
    private Usuario sessionUsurario;
    
public CtPif() {
    objPif = new Pif();
    PifSel = new Pif();
    sessionUsurario = new Usuario();    
    facesContext = FacesContext.getCurrentInstance();
    httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        obtenerPif();

}


  @PostConstruct
    public void init() {
        obtenerSession();
    }
    
 public void obtenerSession() {
        try {
            int idUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuario");
            sessionUsurario = FUsuario.obtenerUsuarioDadoCodigo(idUsuario);
        } catch (Exception e) {
            System.out.println("public void insertarMejora() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

        
  public void obtenerPif() {
        try {
            lstPif = FPif.obtenerPifActivas();
        } catch (Exception e) {
            System.out.println("public void obtenerPif() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertarPif() {
        try {
            objPif.setSessionUsuario(sessionUsurario);
            String msg = FPif.insertarPif(objPif);
            Util.addSuccessMessage(msg);
            objPif = new Pif();
            obtenerPif();
            resetearDataTable("frmPrincipal:tblEstados");
             DefaultRequestContext.getCurrentInstance().execute("PF('dlgInsertar').hide()");
        } catch (Exception e) {
            System.out.println("public void insertarEstadoCuenta() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void editarPif() {
        try {
            PifSel.setSessionUsuario(sessionUsurario);
            String msg = FPif.editarPif(objPif);
            Util.addSuccessMessage(msg);
            PifSel = new Pif();
            obtenerPif();
            resetearDataTable("frmPrincipal:tblEstados");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditar').hide()");
        } catch (Exception e) {
            System.out.println("public void editarEstadoCuenta() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminarPif() {
        try {
            PifSel.setSessionUsuario(sessionUsurario);
            String msg = FPif.eliminarPif(objPif);
            Util.addSuccessMessage(msg);
            PifSel = new Pif();
            obtenerPif();
            resetearDataTable("frmPrincipal:tblEstados");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            System.out.println("public void eliminarEstadoCuenta() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

 public void resetearDataTable(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    public void setObjPif(Pif objPif) {
        this.objPif = objPif;
    }

    public void setPifSel(Pif PifSel) {
        this.PifSel = PifSel;
    }

    public void setLstPif(List<Pif> lstPif) {
        this.lstPif = lstPif;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public void setSessionUsurario(Usuario sessionUsurario) {
        this.sessionUsurario = sessionUsurario;
    }

    public Pif getObjPif() {
        return objPif;
    }

    public Pif getPifSel() {
        return PifSel;
    }

    public List<Pif> getLstPif() {
        return lstPif;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public Usuario getSessionUsurario() {
        return sessionUsurario;
    }

    public CtPif(Pif PifSel, List<Pif> lstPif, HttpServletRequest httpServletRequest, FacesContext facesContext, Usuario sessionUsurario) {
        this.PifSel = PifSel;
        this.lstPif = lstPif;
        this.httpServletRequest = httpServletRequest;
        this.facesContext = facesContext;
        this.sessionUsurario = sessionUsurario;
    }

    
}
