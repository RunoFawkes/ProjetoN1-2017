
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Controller
{
    public void init(HttpServletRequest request, HttpServletResponse response);
    public void execute();
    public String getReponsePage();
}
