<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>��ȡ�ͻ��˵�Cookie��Ϣ</title>
  </head>
  
  <body>
    ��ȡ�ͻ��˵�Cookie��Ϣ. <br>
    <%=basePath%>
   <%
     // ��õ�ǰ·���Լ�"ֱ�Ӹ�·��"������Cookie����,���û���κ�Cookie�Ļ�,�򷵻�null   
     Cookie[] cookies = request.getCookies();
   //response.
   	//Cookie aaa = new Cookie("aaa","???");
   
   	//response.
   
     // ��������,��þ����Cookie
     if(cookies == null) {
        out.print("û��Cookie��Ϣ");
     } else {
         for(int i=0; i<cookies.length; i++) {
            // ��þ����Cookie
            Cookie cookie = cookies[i];
            // ���Cookie������
            String name = cookie.getName();
            String value = cookie.getValue();
            //cookie.setMaxAge(0);
            //response.addCookie(cookie);
            out.print(i+". Cookie��:"+name+" &nbsp; Cookieֵ:"+value+"<br>");
            
            
            Cookie cookie_2 = new Cookie(cookies[i].getName(), null);  
            cookie_2.setMaxAge(0);  
            //cookie.setPath(path);//�����㴴��cookie��·��������д      
            response.addCookie(cookie_2); 
         }
     } 

     
  
    %>
  </body>
</html>