/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.20
 * Generated at: 2015-05-18 04:13:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.src;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.User;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/src/navbar.jsp", Long.valueOf(1430117268000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("model.User");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("  <meta charset=\"UTF-8\">\r\n");
      out.write("\r\n");
      out.write("  <title>Login</title>\r\n");
      out.write("\r\n");
      out.write("  <link href=\"lib/css/bootstrap.css\" rel=\"stylesheet\">\r\n");
      out.write("  <link href=\"lib/css/navbar.css\" rel=\"stylesheet\">\r\n");
      out.write("  <link href=\"lib/css/housing.css\" rel=\"stylesheet\">\r\n");
      out.write("  <script src=\"lib/js/jquery-1.11.2.min.js\"> </script>\r\n");
      out.write("  <script src=\"lib/js/bootstrap.js\"> </script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<!-- Make any changes to the nav bar here! -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Creating the Nav Bar -->\r\n");
      out.write("  \t<div class= \"navbar navbar-fixed-top navbar-inverse\">\r\n");
      out.write("\t  \t<div class=\"container\">\r\n");
      out.write("\t  \t\r\n");
      out.write("\t  \t<a href=\"#\" class=\"pull-left navbar-brand\"><img width=\"90px\" height=\"22px\" src=\"/Housing/lib/images/houSCingWhite.png\"></a>  \t\r\n");
      out.write("\t  \t\t<ul class=\"nav navbar-nav\">\r\n");
      out.write("\t  \t\t\t<li id=\"search\">\r\n");
      out.write("\t\t        <a href=\"/Housing/search\"> \r\n");
      out.write("\t\t          <i class=\"glyphicon glyphicon-search\">\r\n");
      out.write("\t\t          </i> Search\r\n");
      out.write("\t\t        </a>\r\n");
      out.write("\t\t      </li>\r\n");
      out.write("\t  \t\t</ul>\r\n");
      out.write("\t  \t\t<ul class=\"nav navbar-nav\">\r\n");
      out.write("\t  \t\t\t<li id=\"home\">\r\n");
      out.write("\t\t        <a href=\"/Housing/src/mapPage.jsp\"> \r\n");
      out.write("\t\t          <i class=\"glyphicon glyphicon-map-marker\">\r\n");
      out.write("\t\t          </i> Map\r\n");
      out.write("\t\t        </a>\r\n");
      out.write("\t\t      </li>\r\n");
      out.write("\t  \t\t</ul>\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\t\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\r\n");
      out.write("\t\t");
 if(session.getAttribute("curr") != null){
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t<ul class=\"nav navbar-nav\">\r\n");
      out.write("\t  \t\t\t<li id=\"chatButtonOnNavbar\">\r\n");
      out.write("\t\t          <a href=\"/Housing/src/chat.jsp\"> \r\n");
      out.write("\t\t          <i class=\"glyphicon glyphicon-flag\"> </i> Chat\r\n");
      out.write("\t\t          </a>\r\n");
      out.write("\t\t      </li>\r\n");
      out.write("\t  \t\t</ul>\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\t  \t\t<ul class=\"nav navbar-nav\">\r\n");
      out.write("\t  \t\t\t<li id=\"chatButtonOnNavbar\">\r\n");
      out.write("\t\t          <a href=\"/Housing/src/friends.jsp\"> \r\n");
      out.write("\t\t          <i class=\"glyphicon glyphicon-heart\"> </i> Friends\r\n");
      out.write("\t\t          </a>\r\n");
      out.write("\t\t      </li>\r\n");
      out.write("\t  \t\t</ul>\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\t\t\t<ul class=\"nav navbar-nav pull-right\">\r\n");
      out.write("\t\t\t<p class=\"navbar-text\">Welcome, ");
      out.print( ((User)(session.getAttribute("curr"))).getFirstName() );
      out.write("</p>\r\n");
      out.write("\t\t\t\t<li class=\"dropdown\">\r\n");
      out.write("          \t\t\t<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-expanded=\"false\">\r\n");
      out.write("          \t\t\t<i class=\"glyphicon glyphicon-user\"></i> <span class=\"caret\"></span></a>\r\n");
      out.write("          \t\t\t<ul class=\"dropdown-menu\" role=\"menu\">\r\n");
      out.write("            \t\t\t<li>\r\n");
      out.write("\t\t        \t\t\t<a href=\"/Housing/profile\"> \r\n");
      out.write("\t\t          \t\t\t<i class=\"glyphicon glyphicon-user\">\r\n");
      out.write("\t\t          \t\t\t</i> My Profile\r\n");
      out.write("\t\t       \t\t\t \t</a>\r\n");
      out.write("\t\t      \t\t\t</li>\r\n");
      out.write("\t\t      \t\t\t<li>\r\n");
      out.write("\t\t        \t\t\t<a href=\"/Housing/top-housing\"> \r\n");
      out.write("\t\t          \t\t\t<i class=\"glyphicon glyphicon-map-marker\">\r\n");
      out.write("\t\t          \t\t\t</i> Top Housing\r\n");
      out.write("\t\t       \t\t\t \t</a>\r\n");
      out.write("\t\t      \t\t\t</li>\r\n");
      out.write("\t\t\t            <li class=\"divider\"></li>\r\n");
      out.write("\t\t\t            <li>\r\n");
      out.write("\t\t\t            \t<a href=\"/Housing/logout\">\r\n");
      out.write("\t\t\t            \t<i class=\"glyphicon glyphicon-off\">\r\n");
      out.write("\t\t          \t\t\t</i> Logout\r\n");
      out.write("\t\t\t            \t</a>\r\n");
      out.write("\t\t\t            </li>\r\n");
      out.write("          \t\t\t</ul>   \r\n");
      out.write("          \t\t</li>   \t\t\t\r\n");
      out.write("          \t</ul>\r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t ");
} else {
      out.write("\r\n");
      out.write("\t\t \r\n");
      out.write("\t\t \t<ul class=\"nav navbar-nav pull-right\">\r\n");
      out.write("\t\t\t\t<li class=\"dropdown\">\r\n");
      out.write("          \t\t\t<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-expanded=\"false\">\r\n");
      out.write("          \t\t\t<i class=\"glyphicon glyphicon-user\"></i> <span class=\"caret\"></span></a>\r\n");
      out.write("          \t\t\t<ul class=\"dropdown-menu\" role=\"menu\">\r\n");
      out.write("            \t\t\t<li>\r\n");
      out.write("\t\t        \t\t\t<a href=\"/Housing/login\"> \r\n");
      out.write("\t\t          \t\t\t<i class=\"glyphicon glyphicon-user\">\r\n");
      out.write("\t\t          \t\t\t</i> Login\r\n");
      out.write("\t\t        \t\t\t</a>\r\n");
      out.write("\t\t      \t\t\t</li>\r\n");
      out.write("\t\t\t            <li class=\"divider\"></li>\r\n");
      out.write("\t\t\t            <li>\r\n");
      out.write("\t\t        \t\t\t<a href=\"/Housing/signup\"> \r\n");
      out.write("\t\t          \t\t\t<i class=\"glyphicon glyphicon-circle-arrow-up\">\r\n");
      out.write("\t\t          \t\t\t</i> Sign Up\r\n");
      out.write("\t\t        \t\t\t</a>\r\n");
      out.write("\t\t      \t\t\t</li>\r\n");
      out.write("          \t\t\t</ul>   \r\n");
      out.write("          \t\t</li>   \t\t\t\r\n");
      out.write("          \t</ul>\r\n");
      out.write("          \r\n");
      out.write("         ");
} 
      out.write("   \r\n");
      out.write("\t\t \r\n");
      out.write("\t  \t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("<!-- Finished creating the Nav Bar -->\r\n");
      out.write("\r\n");
 
//setting the type for the alert box
String alertType = null;
String alertPhrase = null;
if(request.getAttribute("success") != null && (Boolean)request.getAttribute("success") == true){
	alertType = "alert-success";
	alertPhrase = "Success!";
} else if (request.getAttribute("success") != null && (Boolean)request.getAttribute("success") == false) {
	alertType = "alert-danger";
	alertPhrase = "Error!";
}

      out.write("\r\n");
      out.write("\r\n");
 if(alertType != null){ 
      out.write("\r\n");
      out.write("<div class=\"alert ");
      out.print( alertType );
      out.write(" alert-dismissable fade in\">\r\n");
      out.write("    <span class=\"close\" data-dismiss=\"alert\">&times;</span>\r\n");
      out.write("    <strong>");
      out.print( alertPhrase );
      out.write("</strong> ");
      out.print( request.getAttribute("message") );
      out.write("\r\n");
      out.write("</div>\r\n");
 request.setAttribute("success", null); 
      out.write('\r');
      out.write('\n');
 request.setAttribute("message", null); 
      out.write('\r');
      out.write('\n');
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Creating login div -->\r\n");
      out.write("\t<div class=\"form-wrapper\">\r\n");
      out.write("\t\t<form action=\"/Housing/login\" method=\"post\">\r\n");
      out.write("\t\t\t<h2 class=\"title\">Login</h2>\r\n");
      out.write("\t\t\t<h3 class=\"subtitle\">Welcome Back!</h3>\r\n");
      out.write("\t\t\t<input type=\"email\" name=\"email\" placeholder=\"Email\" required/>\r\n");
      out.write("\t\t\t<input type=\"password\" name=\"pass\" placeholder=\"Password\" required/>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<!-- creating the remember-me checkbox -->\r\n");
      out.write("\t\t\t<div class=\"checkbox-container invisible\">\r\n");
      out.write("\t\t\t<label class=\"checkbox-label\">\r\n");
      out.write("\t\t\t\t<input type=\"checkbox\" class=\"checkbox\" value=\"remember\" name=\"remember\"/> Remember me\r\n");
      out.write("\t\t\t</label>\r\n");
      out.write("\t\t\t</div>\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\t<input type=\"submit\" name=\"submit\" class=\"submit action-button\" value=\"Submit\" />\r\n");
      out.write("\t\t\t<a class=\"btn action-button\" href=\"/Housing/signup\">Sign up</a>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("<!-- Finished creating login div -->\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
