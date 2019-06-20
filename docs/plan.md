---
layout: default
---

## Plan

![basic plan](https://github.com/mylene/MicroLearning/blob/master/docs/images/Knipsel.PNG?raw=true "Basic plan") 

Bovenstaand plaatje geeft een absoluut minimale implementatie van het systeem weer:
1. Iemand voert een onderwerp in 
2. Het systeem gaat zoeken
3. Zoekresultaten worden opgeslagen
4. Een pagina (uit die zoekresultaten) wordt getoond

Vanuit deze basis kan eenieder aan het werk gaan. Onderstaand (weerspiegeld in de issues op Github) staat een aantal 
onderwerpen waaruit gekozen kan worden, of die kunnen leiden tot eigen ideeën.

### webpagina
* API
* Framework(s)
* Service Worker
* Security (log in)
* Rollen
* Profiel
* Tags
* Categorieën
* Recommendations
* Mobile first
* Stuur deze pagina door naar ...
* Toon metrics
* Toon health
* Waar zoeken?
* "Waarde" getoonde pagina aangeven
* ...

## zoeken
* Framework(s)
* API
* Google
* Wikipedia
* Twitter
* Documenten (pdf etc.)
* Pagina's en/of URL's en/of metadata
* Hoeveel resultaten?
* ...

## database
* Welke?
* API
* Cachen voor ophalen (CQRS?)
* Hoeveel items opslaan per zoekopdracht
* Onthouden wat getoond
* Wat sla je op? Pagina of link?
* Automatisch x nieuwe items ophalen

## "organisatie" en overig
* Ondersteunend aan bovenstaand
* API
* Slack chatbot


Alles op de backend graag via microservices, Java 12, Maven, Modulair en BDD/TDD.

Verder staat de keuze van microservices framework vrij. Communicatieprotocol staat vrij (REST, GraphQL, gRPC). Wel 
graag goed documenteren. 
Als je iets anders wilt, graag goed documenteren hoe de rest daar gebruik van kan maken.

Iets werkends is belangrijker dan iets perfects (code moet altijd netjes & getest zijn). 
Wel opleveren, en documenteren known issues maar leren is veel belangrijker.