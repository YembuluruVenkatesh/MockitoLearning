package com.learning.ml;
import com.learning.ml.entity.User;
import com.learning.ml.repository.UserRepository;
import com.learning.ml.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
// mvn -Dtest=InteractionTest test

@ExtendWith(MockitoExtension.class)
public class InteractionTest {


    @Mock
    UserRepository repo;

    @InjectMocks
    UserService service;

    @Test
    void test_save_called_once() {
        service.register("buddy");

        verify(repo).save(any(User.class));           // must be called once
        verifyNoMoreInteractions(repo);               // no extra calls allowed
    }

    @Test
    void test_save_called_twice() {
        service.registerTwice("buddy");

        verify(repo, times(2)).save(any(User.class)); // must be called twice
        verifyNoMoreInteractions(repo);
    }

    @Test
    void test_delete_called_never() {
        service.register("buddy");

        verify(repo).save(any(User.class));
        verify(repo, never()).deleteById(anyLong());  // delete must NOT happen
    }

    @Test
    void test_delete_called_once() {
        service.delete(10L);

        verify(repo).deleteById(10L);                 // must be called once
        verifyNoMoreInteractions(repo);
    }

}
