
package servlets;

import enitys.Product;
import enitys.User;
import facades.ProductFacade;
import facades.UserRolesFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SellerServlet", urlPatterns = {
    "/showAddProduct",
    "/addProduct",
    "/showEditProduct",
    "/editProduct",
})
public class SellerServlet extends HttpServlet {
    @EJB UserRolesFacade userRolesFacade;
    @EJB ProductFacade productFacade;
    
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
        if(!userRolesFacade.isRole("SELLER", authUser)){
            request.setAttribute("info", "У вас нет прав!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        session.setAttribute("topRole", userRolesFacade.getTopRole(authUser));
        
        String path = request.getServletPath();
        switch(path) {
            case "/showAddProduct":
                request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
                break;
                
            case "/addProduct":
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String size = request.getParameter("size");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");
                
                if (name.isEmpty() || description.isEmpty() || size.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
                    request.setAttribute("name", name);
                    request.setAttribute("desctiption", description);
                    request.setAttribute("size", size);
                    request.setAttribute("price", price);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/showAddProduct").forward(request, response);
                    break;
                }
                
                Product product = new Product();
                product.setTitle(name);
                product.setDescription(description);
                product.setSize(Integer.parseInt(size));
                product.setPrice(Double.parseDouble(price));
                product.setQuantity(Integer.parseInt(quantity));
                
                productFacade.create(product);
                
                request.setAttribute("info", "Товар успешно добавлен");
                request.getRequestDispatcher("/showAddProduct").forward(request, response);
                break;
                
            case "/showEditProduct":
                String productId = request.getParameter("id");
                product = productFacade.find(Long.parseLong(productId));
                request.setAttribute("id", productId);
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/editProduct.jsp").forward(request, response);
                break;
                
            case "/editProduct":
                productId = request.getParameter("id");
                name = request.getParameter("name");
                description = request.getParameter("description");
                size = request.getParameter("size");
                price = request.getParameter("price");
                quantity = request.getParameter("quantity");
                
                if (name.isEmpty() || description.isEmpty() || size.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
                    request.setAttribute("name", name);
                    request.setAttribute("desctiption", description);
                    request.setAttribute("size", size);
                    request.setAttribute("price", price);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/showEditProduct").forward(request, response);
                    break;
                }
                
                product = productFacade.find(Long.parseLong(productId));
                product.setTitle(name);
                product.setDescription(description);
                product.setSize(Integer.parseInt(size));
                product.setPrice(Double.parseDouble(price));
                product.setQuantity(Integer.parseInt(quantity));
                productFacade.edit(product);
                request.setAttribute("info", "Товар успешно обновлен");
                request.getRequestDispatcher("/listProducts").forward(request, response);
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
