import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.web.DAOInterface.RegisterDaoInterface;
import com.web.Entity.Book;
import com.web.Service.serviceImpl.RegisterServiceImpl;

public class RegisterDaoImplTest {

    @Mock
    private RegisterDaoInterface registerDaoMock;

    @InjectMocks
    private RegisterServiceImpl registerService;

    @Before
    public void setUp() throws Exception {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        // Arrange
        Object user = new Object(); // Replace with actual user entity if available
        String userType = "buyer";
        when(registerDaoMock.addUser(user, userType)).thenReturn("User added successfully");

        // Act
        String result = registerService.addUser(user, userType);

        // Print the result
        System.out.println("testAddUser result: " + result);

        // Assert
        assertEquals("User added successfully", result);
        verify(registerDaoMock, times(1)).addUser(user, userType);
    }

    @Test
    public void testAuthenticateUser() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        Object expectedUser = new Object(); // Replace with actual user entity if available
        when(registerDaoMock.authenticateUser(email, password)).thenReturn(expectedUser);

        // Act
        Object result = registerService.authenticateUser(email, password);

        // Print the result
        System.out.println("testAuthenticateUser result: " + result);

        // Assert
        assertEquals(expectedUser, result);
        verify(registerDaoMock, times(1)).authenticateUser(email, password);
    }

    @Test
    public void testRegisterBook() {
        // Arrange
        Book book = new Book(); // Ensure this Book object has the required fields filled
        Long retailerId = 1L;
        when(registerDaoMock.registerBook(book, retailerId)).thenReturn("Book registered successfully");

        // Act
        String result = registerService.registerBook(book, retailerId);

        // Print the result
        System.out.println("testRegisterBook result: " + result);

        // Assert
        assertEquals("Book registered successfully", result);
        verify(registerDaoMock, times(1)).registerBook(book, retailerId);
    }
}
