
package servlets;

import enitys.Role;
import enitys.User;
import enitys.UserRoles;
import facades.RoleFacade;
import facades.UserFacade;
import facades.UserRolesFacade;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tools.PasswordProtector;

@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/showLogin",
    "/login",
    "/logout",
    "/showRegistration",
    "/registration"
})
public class LoginServlet extends HttpServlet {
    @EJB UserFacade userFacade;
    @EJB RoleFacade roleFacade;
    @EJB UserRolesFacade userRolesFacade;
    
    @Override
    public void init() throws ServletException {
        super.init();
        if (userFacade.count() > 0) {
            return;
        }
        
        User user = new User();
        user.setFirstName("Dmitrii");
        user.setSureName("Kreivald");
        user.setPhone("+37254897768");
        user.setLogin("admin");
        user.setWallet(0);
        PasswordProtector passwordProtector = new PasswordProtector();
        String salt = passwordProtector.getSalt();
        user.setSalt(salt);
        String password = passwordProtector.getProtectedPassword("12345", salt);
        user.setPassword(password);
        user.setListProducts(new ArrayList<>());
        userFacade.create(user);
        
        Role role = new Role();
        role.setRoleName("CUSTOMER");
        roleFacade.create(role);
        
        UserRoles ur = new UserRoles();
        ur.setRole(role);
        ur.setUser(user);
        userRolesFacade.create(ur);
        
        role = new Role();
        role.setRoleName("SELLER");
        roleFacade.create(role);
        
        ur = new UserRoles();
        ur.setRole(role);
        ur.setUser(user);
        userRolesFacade.create(ur);
        
        role = new Role();
        role.setRoleName("ADMINISTRATOR");
        roleFacade.create(role);
        
        ur = new UserRoles();
        ur.setRole(role);
        ur.setUser(user);
        userRolesFacade.create(ur);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String path = request.getServletPath();
        switch(path) {
            case "/showLogin":
                request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                break;
                
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                
                User authUser = userFacade.findByLogin(login);
                if (authUser == null) {
                    request.setAttribute("info", "Неверный логин или пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                
                String salt = authUser.getSalt();
                PasswordProtector passwordProtector = new PasswordProtector();
                password = passwordProtector.getProtectedPassword(password, salt);
                if (!password.equals(authUser.getPassword())) {
                    request.setAttribute("info", "Неверный логин или пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                
                HttpSession session = request.getSession(true);
                session.setAttribute("authUser", authUser);
                request.setAttribute("info", "Добро пожаловать, " + authUser.getFirstName());
                request.getRequestDispatcher("/listProducts").forward(request, response);
                break;
                
            case "/logout":
                session = request.getSession();
                if (session != null) {
                    session.invalidate();
                    request.setAttribute("info", "Вы вышли");
                }
                request.getRequestDispatcher("/showLogin").forward(request, response);
                break;
                
            case "/showRegistration":
                request.getRequestDispatcher("/showRegistration.jsp").forward(request, response);
                break;
                
            case "/registration":
                String firstName = request.getParameter("firstName");
                String sureName = request.getParameter("sureName");
                String phone = request.getParameter("phone");
                login = request.getParameter("login");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                if (!password1.equals(password2)) {
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("sureName", sureName);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Пароли не совпадают");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                if("".equals(firstName)
                        || "".equals(sureName)
                        || "".equals(phone)
                        || "".equals(login)
                        || "".equals(password1)
                        || "".equals(password2)
                        ){
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("sureName", sureName);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                
                User newUser = new User();
                newUser.setFirstName(firstName);
                newUser.setSureName(sureName);
                newUser.setPhone(phone);
                newUser.setLogin(login);
                passwordProtector = new PasswordProtector();
                salt = passwordProtector.getSalt();
                newUser.setSalt(salt);
                password1 = passwordProtector.getProtectedPassword(password1, salt);
                newUser.setPassword(password1);
                userFacade.create(newUser);
                Role role = roleFacade.findRoleByRoleName("CUSTOMER");
                if(role == null){
                    request.setAttribute("info", "Не найдена роль. Обратитесь к разработчику");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                UserRoles ur = new UserRoles();
                ur.setRole(role);
                ur.setUser(newUser);
                userRolesFacade.create(ur);
                request.setAttribute("info", "Привет, "+newUser.getFirstName()+"! Авторизуйтесь");
                request.getRequestDispatcher("/showLogin").forward(request, response);
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
