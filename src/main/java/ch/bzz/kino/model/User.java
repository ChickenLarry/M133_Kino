package ch.bzz.kino.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

@Getter
@Setter
public class User {
    @FormParam("userUUID")
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String userUUID;

}
