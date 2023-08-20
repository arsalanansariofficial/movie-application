package demo.ServiceLayer.Service;

import demo.DatabaseLayer.Entity.City;
import demo.DatabaseLayer.Repository.CityRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City findCityByCityId(String cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("city with cityId '" + cityId + "' not found!"));
    }

    public List<City> findAll() {
        if (cityRepository.findAll().isEmpty()) throw new EntityNotFoundException("no cities in the database!");
        return cityRepository.findAll();
    }

    public City saveCity(City city) {
        if (city.getCityId() == null) city.setCityId(UUID.randomUUID().toString().replaceAll("-", ""));
        return cityRepository.save(city);
    }

    public List<City> saveCities(List<City> cities) {
        cities.forEach(city -> {
            if (city.getCityId() == null) city.setCityId(UUID.randomUUID().toString().replaceAll("-", ""));
        });
        return cityRepository.saveAll(cities);
    }

    public void deleteCityByCityId(String cityId) {
        cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("city with cityId '" + cityId + "' not found!"));
        cityRepository.delete(cityRepository.findCityByCityId(cityId));
    }

    public void deleteAll() {
        cityRepository.deleteAll();
    }

}
