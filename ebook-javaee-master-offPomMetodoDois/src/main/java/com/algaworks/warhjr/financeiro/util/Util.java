/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.warhjr.financeiro.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

/**
 *
 * @author DeividNn
 */
@ManagedBean
public class Util {



    public static void criarAviso(String txt) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, txt, txt);
        FacesContext.getCurrentInstance().addMessage(txt, msg);
    }

    public static void criarAvisoErro(String txt) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, txt, txt);
        FacesContext.getCurrentInstance().addMessage(txt, msg);
    }

    public static void resetarFormulario(String id) {
        RequestContext.getCurrentInstance().reset(id);
    }

    public static void executarJavaScript(String comando) {
        RequestContext.getCurrentInstance().execute(comando);
    }

    public static void atualizarForm(String id) {
        RequestContext.getCurrentInstance().update(id);
    }

    public static List<String> atributosClasse(Class classe) {
        List<String> str = new ArrayList<>();
        for (Field atr : classe.getDeclaredFields()) {
            str.add(atr.getName());
        }
        return str;
    }




    public static void criarObjetoDeSessao(Object obj, String nome) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute(nome, obj);
    }

    public static Object pegarObjetoDaSessao(String nomeSessao) {
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return sessao.getAttribute(nomeSessao);
    }

    public static void redirecionarPagina(String pagina) {
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/" + pagina);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static public void zipFolder(String srcFolder, String destZipFile) throws Exception {
        ZipOutputStream zip;
        FileOutputStream fileWriter;

        fileWriter = new FileOutputStream(destZipFile);
        zip = new ZipOutputStream(fileWriter);

        addFolderToZip("", srcFolder, zip);
        zip.flush();
        zip.close();
    }

    static private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
            throws Exception {

        File folder = new File(srcFile);
        if (folder.isDirectory()) {
            addFolderToZip(path, srcFile, zip);
        } else {
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(srcFile);
            zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
            while ((len = in.read(buf)) > 0) {
                zip.write(buf, 0, len);
            }
        }
    }

    static private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
            throws Exception {
        File folder = new File(srcFolder);

        for (String fileName : folder.list()) {
            if (path.equals("")) {
                addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
            } else {
                addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
            }
        }
    }

}
