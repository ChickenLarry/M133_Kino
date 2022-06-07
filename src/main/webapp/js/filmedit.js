

document.addEventListener("DOMContentLoaded",() => {
    readFilm();
    readKino();
    readSaal();
    showFilm();
    showKino();
    showSaal();
});


function readFilm() {
    const filmUUID = getQueryParam("uuid")
    fetch("./resource/film/read?uuid" + filmUUID)
    .then(function (response) {
        if (response.ok) {
            return response
        } else {
            console.log(response);
        }
    })
        .then(response => response.json())
        .then(data => {
            showFilm(data);
        })
        .catch(function (error) {
            console.log(error);
        })
}

function readKino() {
    const kinoUUID = getQueryParam("uuid")
    fetch("./resource/kino/read?uuid" + kinoUUID)
        .then(function (response) {
            if (response.ok) {
                return response
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showKino(data);
        })
        .catch(function (error) {
            console.log(error);
        })
}

function readSaal() {
    const kinoUUID = getQueryParam("uuid")
    fetch("./resource/saal/read?uuid" + saalUUID)
        .then(function (response) {
            if (response.ok) {
                return response
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showSaal(data);
        })
        .catch(function (error) {
            console.log(error);
        })
}

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
