package demo.AdapterLayer.ObjectAdapter.ObjectAdapter;

import demo.AdapterLayer.ObjectAdapter.Model.*;
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
public class ObjectAdapter {
    private final MovieTheatreRepository movieTheatreRepository;
    private final MovieShowRepository movieShowRepository;

    @Autowired
    public ObjectAdapter(MovieTheatreRepository movieTheatreRepository, MovieShowRepository movieShowRepository) {
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

    public MovieModel getMovie(Movie movie, Map<City, List<MovieTheatre>> cityTheatres) {
        return MovieModel.builder()._id(movie.getMovieId()).name(movie.getName()).genre(movie.getGenre()).artists(movie.getArtists()).releaseIn(movie.getReleaseDate().toString()).releaseOut(movie.getReleaseDate().plusDays(10).toString()).duration(movie.getDuration().toString()).criticsRating(movie.getCriticsRating().toString()).storyline(movie.getStoryline()).artistURL(movie.getArtistURL()).wikipediaURL(movie.getWikipediaURL()).trailerURL(movie.getTrailerURL()).posterURL(movie.getPosterURL()).cities(cityTheatres.keySet().stream().map(city -> getCityModel(city, cityTheatres.get(city))).collect(Collectors.toList())).build();
    }

    public CityModel getCityModel(City city, List<MovieTheatre> movieTheatresInCity) {
        return CityModel.builder()._id(city.getCityId()).cityName(city.getCityName()).theatres(movieTheatresInCity.stream().map(this::getTheatreModel).collect(Collectors.toList())).build();
    }

    public TheatreModel getTheatreModel(MovieTheatre movieTheatre) {
        return TheatreModel.builder()._id(movieTheatre.getTheatre().getTheatreId()).theatreName(movieTheatre.getTheatre().getTheatreName()).hindiShow(movieShowRepository.findMovieShowsByMovieTheatre(movieTheatre).stream().filter(movieShow -> movieShow.getShowLanguage().equals("hindi")).collect(Collectors.toList()).stream().map(this::getHindiShowModel).collect(Collectors.toList())).englishShow(movieShowRepository.findMovieShowsByMovieTheatre(movieTheatre).stream().filter(movieShow -> movieShow.getShowLanguage().equals("english")).map(this::getEnglishShowModel).collect(Collectors.toList())).build();
    }

    public HindiShowModel getHindiShowModel(MovieShow movieShow) {
        return HindiShowModel.builder()._id(movieShow.getMovieShowId()).timing(movieShow.getShowTime().toString()).seatsAvailable(movieShow.getSeatsAvailable()).build();
    }

    public EnglishShowModel getEnglishShowModel(MovieShow movieShow) {
        return EnglishShowModel.builder()._id(movieShow.getMovieShowId()).timing(movieShow.getShowTime().toString()).seatsAvailable(movieShow.getSeatsAvailable()).build();
    }
}
