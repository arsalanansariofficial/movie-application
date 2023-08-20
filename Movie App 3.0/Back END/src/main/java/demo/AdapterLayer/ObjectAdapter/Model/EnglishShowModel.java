package demo.AdapterLayer.ObjectAdapter.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnglishShowModel {
    private String _id;
    private String timing;
    private Integer seatsAvailable;
}
