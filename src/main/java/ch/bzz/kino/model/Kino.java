package ch.bzz.kino.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

@Getter
@Setter
public class Kino {
    @FormParam("kinoUUID")
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String kinoUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min = 5, max = 20)
    private String name;

    @FormParam("ort")
    @NotEmpty
    @Size(min = 10, max = 50)
    private String ort;
}
