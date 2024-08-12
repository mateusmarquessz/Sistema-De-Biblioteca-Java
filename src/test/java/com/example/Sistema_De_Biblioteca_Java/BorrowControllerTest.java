package com.example.Sistema_De_Biblioteca_Java;


import com.example.Sistema_De_Biblioteca_Java.controller.BorrowController;
import com.example.Sistema_De_Biblioteca_Java.entity.Borrow;
import com.example.Sistema_De_Biblioteca_Java.service.BorrowService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = BorrowController.class)
public class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowService borrowService;

    @Test
    void testCreateAndSaveBorrow() throws Exception {
        Long bookId = 1L;
        Long userId = 1L;

        Mockito.doNothing().when(borrowService).createAndSaveBorrow(bookId, userId);

        mockMvc.perform(MockMvcRequestBuilders.post("/borrows/create")
                        .param("bookId", bookId.toString())
                        .param("userId", userId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Borrow created and saved successfully"));
    }

    @Test
    void testReturnBook() throws Exception {
        Long borrowId = 1L;
        Mockito.doNothing().when(borrowService).returnBook(borrowId);

        mockMvc.perform(MockMvcRequestBuilders.post("/borrows/return")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"borrowId\":" + borrowId + " }"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book returned successfully"));
    }

    @Test
    void testGetAllBorrows() throws Exception {
        Borrow borrow = new Borrow();
        Mockito.when(borrowService.getAllBorrows()).thenReturn(Collections.singletonList(borrow));

        mockMvc.perform(MockMvcRequestBuilders.get("/borrows"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetBorrowById() throws Exception {
        Long borrowId = 1L;
        Borrow borrow = new Borrow();
        Mockito.when(borrowService.getBorrowById(borrowId)).thenReturn(Optional.of(borrow));

        mockMvc.perform(MockMvcRequestBuilders.get("/borrows/{id}", borrowId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testGetBorrowById_NotFound() throws Exception {
        Long borrowId = 1L;
        Mockito.when(borrowService.getBorrowById(borrowId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/borrows/{id}", borrowId))
                .andExpect(status().isNotFound());
    }
}
