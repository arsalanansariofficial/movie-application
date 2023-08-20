package demo.AdapterLayer.HashMapAdapter.EntityAdapter;

import demo.DatabaseLayer.Entity.City;
import demo.DatabaseLayer.Entity.Movie;
import demo.DatabaseLayer.Entity.MovieShow;
import demo.DatabaseLayer.Entity.MovieTheatre;
import demo.DatabaseLayer.Repository.MovieShowRepository;
import demo.DatabaseLayer.Repository.MovieTheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EntityAdapter {
    private final MovieTheatreRepository movieTheatreRepository;
    private final MovieShowRepository movieShowRepository;

    @Autowired
    public EntityAdapter(MovieTheatreRepository movieTheatreRepository, MovieShowRepository movieShowRepository) {
        this.movieTheatreRepository = movieTheatreRepository;
        this.movieShowRepository = movieShowRepository;
    }

    public Map<City, List<MovieTheatre>> getCityTheatresMap(Movie movie) {
        Map<City, List<MovieTheatre>> cityTheatres = new HashMap<>();
        movieTheatreRepository.findMovieTheatresByMovie(movie).forEach(movieTheatre -> {
            City city = movieTheatre.getTheatre().getCity();
            if (!cityTheatres.containsKey(city)) {
                List<MovieTheatre> movieTheatresInCity = new ArrayList<>();
                movieTheatresInCity.add(movieTheatre);
                cityTheatres.put(city, movieTheatresInCity);
                return;
            }
            cityTheatres.get(city).add(movieTheatre);
        });
        return cityTheatres;
    }

    public Map<String, Object> getMovie(Movie movie, Map<City, List<MovieTheatre>> cityTheatres) {
        Map<String, Object> movieObject = new HashMap<>();
        movieObject.put("movieId", movie.getMovieId());
        movieObject.put("name", movie.getName());
        movieObject.put("genre", movie.getGenre());
        movieObject.put("artists", movie.getArtists());
        movieObject.put("releaseDate", movie.getReleaseDate().toString());
        movieObject.put("duration", movie.getDuration());
        movieObject.put("criticsRating", movie.getCriticsRating());
        movieObject.put("storyline", movie.getStoryline());
        movieObject.put("artistURL", movie.getArtistURL());
        movieObject.put("wikipediaURL", movie.getWikipediaURL());
        movieObject.put("trailerURL", movie.getTrailerURL());
        movieObject.put("posterURL", movie.getPosterURL());
        movieObject.put("cities", cityTheatres.keySet().stream().map(city -> getCity(city, cityTheatres.get(city))).collect(Collectors.toList()));
        return movieObject;
    }

    public Map<String, Object> getCity(City city, List<MovieTheatre> movieTheatresInCity) {
        Map<String, Object> cityObject = new HashMap<>();
        cityObject.put("cityId", city.getCityId());
        cityObject.put("cityName", city.getCityName());
        cityObject.put("theatres", movieTheatresInCity.stream().map(this::getTheatre));
        return cityObject;
    }

    public Map<String, Object> getTheatre(MovieTheatre movieTheatre) {
        Map<String, Object> theatre = new HashMap<>();
        theatre.put("theatreId", movieTheatre.getTheatre().getTheatreId());
        theatre.put("theatreName", movieTheatre.getTheatre().getTheatreName());
        theatre.put("hindiShows", movieShowRepository.findMovieShowsByMovieTheatre(movieTheatre).stream().filter(movieShow -> movieShow.getShowLanguage().equals("hindi")).map(this::getMovieShow));
        theatre.put("englishShows", movieShowRepository.findMovieShowsByMovieTheatre(movieTheatre).stream().filter(movieShow -> movieShow.getShowLanguage().equals("english")).map(this::getMovieShow));
        return theatre;
    }

    public Map<String, Object> getMovieShow(MovieShow movieShow) {
        Map<String, Object> movieShowObject = new HashMap<>();
        movieShowObject.put("movieShowId", movieShow.getMovieShowId());
        movieShowObject.put("showLanguage", movieShow.getShowLanguage());
        movieShowObject.put("showTime", movieShow.getShowTime().toString());
        movieShowObject.put("seatsAvailable", movieShow.getSeatsAvailable());
        return movieShowObject;
    }
}
