<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>读取客户端的Cookie信息</title>
  </head>
  
  <body>
    读取客户端的Cookie信息. <br>
    <%=basePath%>
   <%
     // 获得当前路径以及"直接父路径"的所有Cookie对象,如果没有任何Cookie的话,则返回null   
     Cookie[] cookies = request.getCookies();
   //response.
   	//Cookie aaa = new Cookie("aaa","???");
   
   	//response.
   
     // 遍历数组,获得具体的Cookie
     if(cookies == null) {
        out.print("没有Cookie信息");
     } else {
         for(int i=0; i<cookies.length; i++) {
            // 获得具体的Cookie
            Cookie cookie = cookies[i];
            // 获得Cookie的名称
            String name = cookie.getName();
            String value = cookie.getValue();
            //cookie.setMaxAge(0);
            //response.addCookie(cookie);
            out.print(i+". Cookie名:"+name+" &nbsp; Cookie值:"+value+"<br>");
            
            
            Cookie cookie_2 = new Cookie(cookies[i].getName(), null);  
            cookie_2.setMaxAge(0);  
            //cookie.setPath(path);//根据你创建cookie的路径进行填写      
            response.addCookie(cookie_2); 
         }
     } 

     
  
    %>
  </body>
</html>