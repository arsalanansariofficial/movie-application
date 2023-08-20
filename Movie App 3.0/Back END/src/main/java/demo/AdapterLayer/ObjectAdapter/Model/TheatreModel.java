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
public class TheatreModel {
    private String _id;
    private String theatreName;
    private List<HindiShowModel> hindiShow;
    private List<EnglishShowModel> englishShow;
}
