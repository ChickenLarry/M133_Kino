package ch.bzz.kino.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

@Getter
@Setter
@NoArgsConstructor
public class Saal {
    @FormParam("saalUUID")
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String saalUUID;

    @FormParam("saalNummer")
    @NotEmpty
    @Size(min = 1, max = 20)
    private int saalNummer;

    @FormParam("plaetze")
    @NotEmpty
    @Size(min = 1, max = 200)
    private int plaetze;

    @FormParam("reihen")
    @NotEmpty
    @Size(min = 1, max = 20)
    private int reihen;

    @FormParam("anzahlPlaetzeProReihe")
    @NotEmpty
    @Size(min = 1, max = 30)
    private int anzahlPlaetzeProReihe;
}