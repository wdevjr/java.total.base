<%@ page import="java.io.FileOutputStream" %><jsp:useBean id="dbBean" scope="page" class="docman.DBBean" /><jsp:useBean id="uploadBean" scope="page" class="com.brainysoftware.web.FileUploadBean" /><%  String userName = dbBean.getCookie(request, "userName");  String password =    dbBean.getCookie(request, "password");  if (userName==null || password==null)    response.sendRedirect("Login.html");  else {    uploadBean.setSavePath(dbBean.getDataPath());    uploadBean.doUpload(request);    String uploadedFilename = uploadBean.getFilename();    String objectId = Integer.toString(dbBean.getLastObjectId() + 1);    String parentId = uploadBean.getFieldValue("parentId");    if (dbBean.hasUploadPermission(parentId, userName, password)) {      uploadBean.forceFilename(objectId);      uploadBean.save();      dbBean.insertObject(parentId, objectId, uploadedFilename);      dbBean.insertPermissions(parentId, objectId);    }    response.sendRedirect("DisplayObjects.jsp?id="      + parentId);  }%>