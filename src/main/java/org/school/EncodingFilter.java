package org.school;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    private String charsetEncoding = "utf-8";
    private String contentType = "text/html";
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(charsetEncoding);
        response.setContentType(contentType);
        response.setCharacterEncoding(charsetEncoding);
        response.getWriter().append(
                "<html>\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\n" +
                        "    \n" +
                "<title>Szkoła programowania</title>\n" +
                "</head>" +
                "<body>\n");

        filterChain.doFilter(request, response);
        response.getWriter().append(
                "</body>\n" +
                "</html>");
    }
    public void destroy() {}
    public void init(FilterConfig config) throws ServletException {
    }
}
