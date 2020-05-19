package com.janflpk.collectionsmanager.ui;

import com.janflpk.collectionsmanager.ui.view.BookForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookFormTest {

    private BookForm bookForm = new BookForm();

    @Test
    public void shouldValidateInput() {
        //Given
        Integer number = 5;
        String inputValue = "12345";

        //When
        boolean validationResult = bookForm.validateInput(number, inputValue);

        //Then
        Assert.assertTrue(validationResult);
    }
}
