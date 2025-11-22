package api.jsonplaceholder.models.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodyLombokModelsUpdatePostTest {
    private int id;
    private String title;
    private String body;
    private int userId;
}
