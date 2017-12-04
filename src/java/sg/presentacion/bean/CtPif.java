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
    private Pif pifSel;
    private List<Pif> lstPif;
    private HttpServletRequest httpServletRequest;
    private FacesContext facesContext;
    private Usuario sessionUsuario;

    public CtPif() {
        objPif = new Pif();
        pifSel = new Pif();
        sessionUsuario = new Usuario();
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
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
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
            objPif.getSessionUsuario();
            //getObjPif().setSessionUsuario(getSessionUsurario());
            //objPif.setSessionUsuario(sessionUsuario);
            String msg = FPif.insertarPif(objPif);
            objPif = new Pif();
            Util.addSuccessMessage(msg);
            resetearDataTable("frmPrincipal:tblPif");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgInsertar').hide()");
            obtenerPif();
        } catch (Exception e) {
            System.out.println("public void insertarPif() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void editarPif() {
        try {
            // PifSel.setSessionUsuario(sessionUsuario);
            String msg = FPif.editarPif(pifSel);
            Util.addSuccessMessage(msg);
            pifSel = new Pif();
            resetearDataTable("frmPrincipal:tblPif");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditar').hide()");
            obtenerPif();
        } catch (Exception e) {
            System.out.println("public void editarPif() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminarPif() {
        try {
            //PifSel.setSessionUsuario(sessionUsuario);
            String msg = FPif.eliminarPif(pifSel);
            Util.addSuccessMessage(msg);
            pifSel = new Pif();
            resetearDataTable("frmPrincipal:tblPif");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
            obtenerPif();
        } catch (Exception e) {
            System.out.println("public void eliminarPif() dice: " + e.getMessage());
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

    public Pif getPifSel() {
        return pifSel;
    }

    public void setPifSel(Pif pifSel) {
        this.pifSel = pifSel;
    }

    public List<Pif> getLstPif() {
        return lstPif;
    }

    public void setLstPif(List<Pif> lstPif) {
        this.lstPif = lstPif;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }
    
    
    
    
    
     }


    