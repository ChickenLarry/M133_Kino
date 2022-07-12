package ch.bzz.kino.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;

/**
 *
 * Film Class
 * @Author: Noel
 *
 * @Since 1.0.0-SNAPSHOT
 *
 */
@Getter
@Setter
public class Film {

    @FormParam("filmUUID")
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String filmUUID;

    @FormParam("titel")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String titel;

    @FormParam("laenge")
    @NotEmpty
    @Size(min = 1, max = 500)
    private int laenge;

    @FormParam("preis")
    @DecimalMax(value="40.00")
    @DecimalMin(value="19.95")
    private BigDecimal preis;

    @FormParam("hauptdarsteller")
    @NotEmpty
    @Size(min = 2, max = 30)
    private String hauptdarsteller;

    @FormParam("regisseur")
    @NotEmpty
    @Size(min = 2, max = 30)
    private String regisseur;
}