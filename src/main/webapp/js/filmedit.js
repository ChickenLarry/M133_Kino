

document.addEventListener("DOMContentLoaded",() => {
    showFilm();
    showKino();
    showSaal();
});

function showFilm(data){
    document.getElementById( "titel").value = data.titel;
    document.getElementById( "laenge").value = data.laenge;
    document.getElementById( "preis").value = data.preis;
    document.getElementById( "hauptdarsteller").value = data.hauptdarsteller;
    document.getElementById( "regisseur").value = data.regisseur;
}

function showKino(data){
    document.getElementById( "name").value = data.name;
    document.getElementById( "ort").value = data.ort;
}

function showSaal(data){
    document.getElementById( "saalNummer").value = data.saalNummer;
    document.getElementById( "plaetze").value = data.plaetze;
    document.getElementById( "reihen").value = data.reihen;
    document.getElementById( "anzahlPlaetzeProReihe").value = data.anzahlPlaetzeProReihe;
}
