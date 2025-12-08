package com.learning.ml;

import com.learning.ml.entity.User;
import com.learning.ml.repository.UserRepository;
import com.learning.ml.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
// mvn -Dtest=UserServiceTest test
public class UserServiceTest {

    @Test
    void testSaveUser_withArgumentCaptor() {

        // 1. Mock repository
        UserRepository repo = mock(UserRepository.class);

        // 2. Inject mock into service
        UserService service = new UserService(repo);

        // 3. Call service method
        service.saveUser("buddy");

        // 4. Create captor for User class
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        // 5. Capture argument passed to repo.save()
        verify(repo).save(captor.capture());

        // 6. Retrieve captured User object
        User captured = captor.getValue();
        System.out.println("captured : "+captured);
        System.out.println("captured.getName() : "+captured.getName());

        // 7. Assertions
        assertEquals("BUDDY", captured.getName()); // service uppercased name


    }

    // ------------ Failure Case (Commented for learning) -------------
    /*
    @Test
    void testFailureCase() {
        UserRepository repo = mock(UserRepository.class);
        UserService service = new UserService(repo);

        service.saveUser("buddy");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(repo).save(captor.capture());

        User captured = captor.getValue();

        // This will FAIL (expected lowercase, but service changes to uppercase)
        assertEquals("buddy", captured.getName());

        // Case insensitive check → passes
        //assertTrue("buddy".equalsIgnoreCase(captured.getName()));

    }
    */
}
