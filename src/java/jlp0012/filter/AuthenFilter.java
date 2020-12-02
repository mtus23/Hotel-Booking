/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jlp0012.dto.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class AuthenFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AuthenFilter.class);
    private static final boolean debug = true;

    private final List<String> guest;
    private final List<String> member;
    private final List<String> admin;
    private final String ERROR_PAGE = "error.jsp";
    private final String SEARCH_PAGE = "search.jsp";
    private final String START_APP = "StartAppController";
    private final int ADMIN_ROLE = 1;
    private final int USER_ROLE = 2;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthenFilter() {
        guest = new ArrayList<>();
        guest.add("login.jsp");
        guest.add("register.jsp");
        guest.add("error.jsp");
        guest.add("search.jsp");
        guest.add("LoginController");
        guest.add("StartAppController");
        guest.add("SignUpController");
        guest.add("LoadHotelDetailController");
        guest.add("detail.jsp");
        guest.add("SearchHotelController");
        guest.add("reset.jsp");
        guest.add("SendMailResetController");
        guest.add("ResetPassController");

        member = new ArrayList<>();
        member.add("LogoutController");
        member.add("StarttAppController");
        member.add("error.jsp");
        member.add("search.jsp");
        member.add("LoadHotelDetailController");
        member.add("detail.jsp");
        member.add("CartController");
        member.add("UpdateCartController");
        member.add("RemoveCartController");
        member.add("SearchHotelController");
        member.add("AddToCartController");
        member.add("CheckOutController");
        member.add("confirm.jsp");
        member.add("ConfirmCheckOutController");
        member.add("history");
        member.add("LoadHistory");
        member.add("CancelBookingController");
        member.add("SearchHistoryController");
        member.add("RateRoomController");

        admin = new ArrayList<>();
        admin.add("StartAppController");
        admin.add("LogoutController");
        admin.add("error.jsp");
        admin.add("search.jsp");
        admin.add("LoadHotelDetailController");
        admin.add("detail.jsp");
        admin.add("SearchHotelcontroller");
        admin.add("CreateDiscountController");
        admin.add("createDiscount.jsp");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String url = START_APP;
            String uri = req.getRequestURI();
            int lastIndex = uri.lastIndexOf("/");
            String resources = uri.substring(lastIndex + 1);
            HttpSession session = req.getSession(false);
            if (session == null) {
            } else {
                if (session.getAttribute("User") == null || session.getAttribute("MapHotel") == null) {
                    //not login
                    if (guest.contains(resources)) {
                        chain.doFilter(request, response);
                        return;
                    } else {
                        resources = ERROR_PAGE;
                    }
                } else {
                    //login
                    UserDTO user = (UserDTO) session.getAttribute("User");
                    if ((user.getRoleId() == ADMIN_ROLE && admin.contains(resources))
                            || ((user.getRoleId() == USER_ROLE && member.contains(resources)))) {
                        chain.doFilter(request, response);
                        return;
                    } else {
                        if (resources.equals("")) {
                            resources = SEARCH_PAGE;
                        } else {
                            resources = ERROR_PAGE;
                        }
                    }
                    if (resources.equals("")) {
                        resources = START_APP;
                    } else {
                        resources = ERROR_PAGE;
                    }
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception e) {
            LOG.error(e.toString());
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthenFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
