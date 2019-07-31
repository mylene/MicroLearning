import { Component, OnInit } from '@angular/core';
import { IGoogleSearch, IOfflineSearch } from './app';
import { GoogleService } from './service/google.service';

@Component({
  selector: 'sa-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})

export class AppComponent implements OnInit{
  title = 'SearchApp';
  errorMessage: string = "";

  searchKey: string = "";
  googleSearch: IGoogleSearch[] = [];
  offlineSearch: IOfflineSearch[] = [];

  // checkbox values welke zoekmachine(s) men wil gebruiken
  search_Offline: boolean = true;
  search_Google:boolean = false;

  constructor(private googleService: GoogleService){}

  // Zoekfuncties aanroepen
  SearchIn(searchKey){
    // console.log(searchKey);
    // console.log("Offline: " + this.search_Offline + " Google: " + this.search_Google);
    if(this.search_Offline === true){searchKey = searchKey.replace(' ', ''); this.getResultOffline(searchKey);}
    if(this.search_Google === true){this.getResultGoogle();}
  }

  getResultGoogle() {
    /* 
      Deze code gaat naar de googleService op http://localhost:8081 
      Het werkt, er zit alleen een restrictie op van 100 queries per maand.
      Daarom wordt functie "getResultOffline" gebruikt om te testen.
      Deze haalt de info uit lokale bestanden.
    */
    this.googleService.getGoogleSearch(this.searchKey).subscribe(
      googleSearch => this.googleSearch = googleSearch,
      error => {
        this.googleSearch = [{title: "404", link:"", snippet:"Sorry, de GoogleService lijkt niet online te zijn. Check localhost:8081/health"}];
        this.errorMessage = <any>error
      }
    )
    
  }
  getResultOffline(searchKey) {
    this.googleService.getGoogleOffline(searchKey).subscribe(
      offlineSearch => this.offlineSearch = offlineSearch,
      error => {
        this.offlineSearch = [{title: "404", snippet:"Sorry, voor die zoekterm is er geen offline bestand. Probeer eens: 'nature', 'java' of 'java developmet'"}];
        this.errorMessage = <any>error;
      }
    )
  }

  ngOnInit(){

  }
}
 