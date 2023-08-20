package demo;

import demo.ControllerLayer.TheatreController;
import demo.DatabaseLayer.Entity.Theatre;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheatreControllerTest {

    @Autowired
    private TheatreController theatreController;

    @Before
    public void save_theatre_before_each_test() {
        Theatre theatre = Theatre.builder().theatreId("pvr").theatreName("pvr").build();
        theatreController.saveTheatre(theatre);
    }

    @After
    public void delete_theatres_after_each_test() {
        theatreController.deleteAll();
    }

    @Test
    public void should_not_find_theatre_with_invalid_theatre_id() {
        Theatre theatre = Theatre.builder().build();
        assertThrows(Exception.class, () -> theatreController.findTheatreByTheatreId(theatre));
    }

    @Test
    public void should_find_theatre_with_valid_theatre_id() {
        Theatre theatre = Theatre.builder().theatreId("pvr").build();
        assertNotNull(theatreController.findTheatreByTheatreId(theatre).getBody());
    }

    @Test
    public void should_not_save_theatre_without_theatre_name() {
        Theatre theatre = Theatre.builder().theatreId("pvr").build();
        assertThrows(Exception.class, () -> theatreController.saveTheatre(theatre));
    }

    @Test
    public void should_save_theatre_with_valid_fields() {
        Theatre theatre = Theatre.builder().theatreId("waves").theatreName("waves").build();
        assertNotNull(theatreController.saveTheatre(theatre).getBody());
    }

    @Test
    public void should_not_delete_theatre_with_invalid_theatre_id() {
        Theatre theatre = Theatre.builder().build();
        assertThrows(Exception.class, () -> theatreController.deleteTheatreById(theatre));
    }

}
