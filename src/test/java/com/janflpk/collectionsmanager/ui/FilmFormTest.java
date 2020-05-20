package com.janflpk.collectionsmanager.ui;

import com.janflpk.collectionsmanager.ui.view.FilmForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmFormTest {

    private FilmForm filmForm = new FilmForm();

    @Test
    public void shouldInitiateBinder() {
        //Given & When
        boolean isBinderValid_1 = filmForm.getBinder().isValid();
        boolean isBinderValid_2 = filmForm.getBinder().validate().isOk();

        //Then
        Assert.assertTrue(isBinderValid_1);
        Assert.assertTrue(isBinderValid_2);
    }
}
