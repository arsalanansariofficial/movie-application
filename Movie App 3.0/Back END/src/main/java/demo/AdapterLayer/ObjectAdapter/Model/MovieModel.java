package demo.AdapterLayer.ObjectAdapter.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieModel {
    private String _id;
    private String name;
    private String genre;
    private String artists;
    private String releaseIn;
    private String releaseOut;
    private String duration;
    private String criticsRating;
    private String storyline;
    private String artistURL;
    private String wikipediaURL;
    private String trailerURL;
    private String posterURL;
    private List<CityModel> cities;
}
