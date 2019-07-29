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

##### Commandline
Om de microservice te starten, voer je het volgende uit:
1. Navigeer naar de folder 'googleService' waar je pom.xml bestand aanwezig is
2. Voer het volgende uit: mvn clean package
3. Als laatste voer je het volgende uit: java -jar target/googleService-1.0-SNAPSHOT.jar

De microservice is beschikbaar via volgende url: http://localhost:8081

##### Service
De URL http://localhost:8081/google/api/search/{searchKey} roept de Google API aan en geeft maximaal 10 zoekresultaten terug.
Om via Google, zoekresultaten te bekomen, wijzig je de parameter {searchKey}. Als resultaat krijg je de volgende gegevens in JSON-formaat terug:

* title  
* link  
* snippet: enkele regels van de webpagina van de bijhorende link van het gevonden resultaat

##### Beperking
Er is geen betalingsplan bij Google afgesloten en dit betekend dat er maximaal 100 queries / maand uitgevoerd mogen worden.