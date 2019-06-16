package br.com.warhjr.mb;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class CustomViewHandler extends ViewHandler {

  protected ViewHandler baseViewHandler;

  public CustomViewHandler(ViewHandler viewHandler) {
    super();
    this.baseViewHandler = viewHandler;
  }

  public Locale calculateLocale(FacesContext facesContext) {
    return baseViewHandler.calculateLocale(facesContext);
  }

  public String calculateRenderKitId(FacesContext facesContext) {
    return baseViewHandler.calculateRenderKitId(facesContext);
  }

  public UIViewRoot createView(FacesContext facesContext, String arg1) {
    setPostback(facesContext, false);
    return baseViewHandler.createView(facesContext, arg1);
  }

  public String getActionURL(FacesContext facesContext, String arg1) {
    return baseViewHandler.getActionURL(facesContext, arg1);
  }

  public String getResourceURL(FacesContext facesContext, String arg1) {
    return baseViewHandler.getResourceURL(facesContext, arg1);
  }

  public void renderView(FacesContext facesContext, UIViewRoot arg1) throws IOException, FacesException {
    baseViewHandler.renderView(facesContext, arg1);
    
  }

  public UIViewRoot restoreView(FacesContext facesContext, String arg1) {
    setPostback(facesContext, true);
    return baseViewHandler.restoreView(facesContext, arg1);
  }

  public void writeState(FacesContext facesContext) throws IOException
{
    baseViewHandler.writeState(facesContext);
  }
  
  public Map getRequestScope(FacesContext facesContext) {
    return (Map)facesContext.getApplication().createValueBinding("#{requestScope}").getValue(facesContext);
  }
  
  public void setPostback(FacesContext facesContext, boolean value) {
    getRequestScope(facesContext).put("ispostback", new Boolean(value));
  }

}      
