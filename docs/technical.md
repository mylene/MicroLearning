---
layout: default
---

## Technical

### Zoeken
Om de resultaten voor een opgegeven onderwerp op te vragen, worden verschillende microservices aangeroepen:
* Google

#### Google
Deze microservice is gebouwd met het [KumuluzEE](https://ee.kumuluz.com/) framework. De beschikbare REST-service roept de Google API aan en geeft maximaal 10 zoekresultaten terug.

##### IntelliJ
Om de microservice te starten, voeg je de volgende 'run' configuratie toe:
1. Bij de optie 'Working directory' verwijs je naar de folder 'googleService'
2. Bij de optie 'Command line' voeg je het volgende toe: clean package kumuluzee:run

De microservice is beschikbaar via volgende url: http://localhost:8081

##### Service
De URL http://localhost:8081/google/api/search/{searchKey} roept de Google API aan en geeft maximaal 10 zoekresultaten terug.
Om via Google, zoekresultaten te bekomen, wijzig je de parameter {searchKey}.
<p>
Als resultaat krijg je de volgende gegevens in JSON-formaat terug:

1. title  
2. link  
3. snippet: enkele regels van de webpagina van de bijhorende link van het gevonden resultaat

##### Beperking
Er is geen betalingsplan bij Google afgesloten en dit betekend dat er maximaal 100 queries / maand uitgevoerd mogen worden.