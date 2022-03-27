
package servlets;

import enitys.Role;
import enitys.User;
import facades.RoleFacade;
import facades.UserFacade;
import facades.UserRolesFacade;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "adminServlet", urlPatterns = {
    "/showChangeRole",
    "/changeRole",
})
public class AdminServlet extends HttpServlet {
    @EJB UserRolesFacade userRolesFacade;
    @EJB UserFacade userFacade;
    @EJB RoleFacade roleFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        if(!userRolesFacade.isRole("ADMINISTRATOR", authUser)){
            request.setAttribute("info", "У вас нет прав!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        session.setAttribute("topRole", userRolesFacade.getTopRole(authUser));
        
        String path = request.getServletPath();
        switch(path) {
            case "/showChangeRole":
                Map<User, String> mapUsers = new HashMap<>();
                List<User> users = userFacade.findAll();
                for (User user : users) {
                    if (user.getId() == 1) {    //Чтобы админ сам себя случайно не выпилил
                        continue;
                    }
                    String topRole = userRolesFacade.getTopRole(user);
                    mapUsers.put(user, topRole);
                }
                request.setAttribute("mapUsers", mapUsers);
                List<Role> roles = roleFacade.findAll();
                request.setAttribute("roles", roles);
                request.getRequestDispatcher("/WEB-INF/changeRole.jsp").forward(request, response);
                break;
                
            case "/changeRole":
                String userId = request.getParameter("selectUser");
                String roleId = request.getParameter("selectRole");
                User u = userFacade.find(Long.parseLong(userId));
                Role r = roleFacade.find(Long.parseLong(roleId));
                userRolesFacade.setRoleToUser(r, u);
                request.setAttribute("info", "Роль назначена");
                request.getRequestDispatcher("/showChangeRole").forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
