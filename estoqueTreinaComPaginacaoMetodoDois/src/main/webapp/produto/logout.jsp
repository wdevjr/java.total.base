<%
session.invalidate();
response.sendRedirect(request.getContextPath()+"/produto/login.jsp");
%>