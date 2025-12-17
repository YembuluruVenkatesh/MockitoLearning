package com.learning.ml;

import com.learning.ml.entity.User;
import com.learning.ml.exception.UserNotFoundException;
import com.learning.ml.repository.UserRepository;
import com.learning.ml.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
// mvn -Dtest=ExceptionStubbingTest test
@ExtendWith(MockitoExtension.class)
public class ExceptionStubbingTest {

    @Mock
    UserRepository repo;

    @InjectMocks
    UserService service;

    // ---------------------------------------------------
    // TEST 1 : thenThrow() for methods with return values
    // ---------------------------------------------------
    @Test
    void test_getUser_throws_exception() {

        when(repo.findById(100L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(UserNotFoundException.class,
                () -> service.getUser(100L));
        System.out.println("test_getUser_throws_exception ex: "+ex);
        assertEquals("User not found: 100", ex.getMessage());
    }

    // ---------------------------------------------------
    // TEST 2 : doThrow() for void methods
    // ---------------------------------------------------
    @Test
    void test_deleteUser_throws_exception() {

        doThrow(new RuntimeException("Delete failed"))
                .when(repo)
                .deleteById(10L);

        Exception ex = assertThrows(RuntimeException.class,
                () -> service.deleteUser(10L));
        System.out.println(" test_deleteUser_throws_exception ex: "+ex);
        assertEquals("Delete failed", ex.getMessage());
    }
}
