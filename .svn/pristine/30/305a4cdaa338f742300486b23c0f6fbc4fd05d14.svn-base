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
import sg.logica.entidades.Pif;
import sg.logica.funciones.FPif;

@ManagedBean
@ViewScoped
public class CtComprarPif implements Serializable {

    private List<Pif> lstPifs;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private Pif pifSel;
    private int bnd;

    public CtComprarPif() {
        pifSel = new Pif();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerPifs();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerSession() {
        try {
            int idUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(idUsuario));
            System.out.println("Id usuario: " + idUsuario
                    + "Usuario session: " + getSessionUsuario().getNick());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public void obtenerPifs() {
        try {
            setLstPifs(FPif.obtenerPifActivas());
            System.out.println("imagen " + lstPifs.get(0).getFoto());
        } catch (Exception e) {
            System.out.println("public void obtenerPifs() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * @return the lstPifs
     */
    public List<Pif> getLstPifs() {
        return lstPifs;
    }

    /**
     * @param lstPifs the lstPifs to set
     */
    public void setLstPifs(List<Pif> lstPifs) {
        this.lstPifs = lstPifs;
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
     * @return the pifSel
     */
    public Pif getPifSel() {
        return pifSel;
    }

    /**
     * @param pifSel the pifSel to set
     */
    public void setPifSel(Pif pifSel) {
        this.pifSel = pifSel;
    }

    /**
     * @return the bnd
     */
    public int getBnd() {
        return bnd;
    }

    /**
     * @param bnd the bnd to set
     */
    public void setBnd(int bnd) {
        this.bnd = bnd;
    }

}
