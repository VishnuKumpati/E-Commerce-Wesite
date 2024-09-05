import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.web.Entity.Admin;
import com.web.Entity.Buyer;
import com.web.Entity.Retailer;
import com.web.ServiceInterface.RegisterServiceInterface;
import com.web.Controller.LoginServlet;

public class LoginServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private RegisterServiceInterface registerService;

    @InjectMocks
    private LoginServlet loginServlet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testBlockedUser() throws Exception {
        // Arrange
        when(request.getParameter("email")).thenReturn("blocked@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(registerService.authenticateUser("blocked@example.com", "password123")).thenReturn("blocked");
        when(request.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(request).setAttribute("errorMessage", "Your account has been blocked. Please contact support.");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testInvalidLogin() throws Exception {
        // Arrange
        when(request.getParameter("email")).thenReturn("invalid@example.com");
        when(request.getParameter("password")).thenReturn("wrongpassword");
        when(registerService.authenticateUser("invalid@example.com", "wrongpassword")).thenReturn(null);
        when(request.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(request).setAttribute("errorMessage", "Invalid email or password.");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testAdminLogin() throws Exception {
        // Arrange
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setName("Admin User");

        when(request.getParameter("email")).thenReturn("admin@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(registerService.authenticateUser("admin@example.com", "password123")).thenReturn(admin);
        when(request.getSession()).thenReturn(session);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("user", admin);
        verify(session).setAttribute("userName", admin.getName());
        verify(session).setAttribute("userId", admin.getId());
        verify(response).sendRedirect("adminDashboard.jsp");
    }

    @Test
    public void testBuyerLogin() throws Exception {
        // Arrange
        Buyer buyer = new Buyer();
        buyer.setId(2L);
        buyer.setName("Buyer User");

        when(request.getParameter("email")).thenReturn("buyer@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(registerService.authenticateUser("buyer@example.com", "password123")).thenReturn(buyer);
        when(request.getSession()).thenReturn(session);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("user", buyer);
        verify(session).setAttribute("userName", buyer.getName());
        verify(session).setAttribute("userId", buyer.getId());
        verify(response).sendRedirect("getAllBooksServlet");
    }

    @Test
    public void testRetailerLogin() throws Exception {
        // Arrange
        Retailer retailer = new Retailer();
        retailer.setId(3L);
        retailer.setName("Retailer User");

        when(request.getParameter("email")).thenReturn("retailer@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(registerService.authenticateUser("retailer@example.com", "password123")).thenReturn(retailer);
        when(request.getSession()).thenReturn(session);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("user", retailer);
        verify(session).setAttribute("userName", retailer.getName());
        verify(session).setAttribute("userId", retailer.getId());
        verify(response).sendRedirect("retailerDashBoard.jsp");
    }
}
