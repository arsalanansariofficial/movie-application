package demo;

import demo.ControllerLayer.CityController;
import demo.DatabaseLayer.Entity.City;

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
public class CityControllerTest {

    @Autowired
    private CityController cityController;

    @Before
    public void save_city_before_each_test() {
        City city = City.builder().cityId("delhi").cityName("delhi").build();
        cityController.saveCity(city);
    }

    @After
    public void delete_cities_after_each_test() {
        cityController.deleteAll();
    }

    @Test
    public void should_not_find_city_with_invalid_city_id() {
        City city = City.builder().build();
        assertThrows(Exception.class, () -> cityController.findCityByCityId(city));
    }

    @Test
    public void should_find_city_with_valid_city_id() {
        City city = City.builder().cityId("delhi").build();
        assertNotNull(cityController.findCityByCityId(city).getBody());
    }

    @Test
    public void should_not_save_city_without_city_name() {
        City city = City.builder().cityId("delhi").build();
        assertThrows(Exception.class, () -> cityController.saveCity(city));
    }

    @Test
    public void should_not_save_city_with_duplicate_city_name() {
        City city = City.builder().cityName("delhi").build();
        assertThrows(Exception.class, () -> cityController.saveCity(city));
    }

    @Test
    public void should_save_city_with_valid_fields() {
        City city = City.builder().cityId("mumbai").cityName("mumbai").build();
        assertNotNull(cityController.saveCity(city).getBody());
    }

    @Test
    public void should_not_delete_city_with_invalid_city_id() {
        City city = City.builder().build();
        assertThrows(Exception.class, () -> cityController.deleteCityById(city));
    }

}
