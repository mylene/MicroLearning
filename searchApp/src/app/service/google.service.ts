import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

import { IGoogleSearch, IOfflineSearch } from '../app';

@Injectable({
  providedIn: 'root'
})
export class GoogleService {
  private googleUrl = "http://localhost:8081/google/api/search/";

  constructor(private http: HttpClient) { }

  getGoogleSearch(searchKey:any): Observable<IGoogleSearch[]>{
    return this.http.get<IGoogleSearch[]>(this.googleUrl+searchKey).pipe(
      tap(data => console.log(data)),
      catchError(this.handleError)
    )
  }
  getGoogleOffline(searchKey:any): Observable<IOfflineSearch[]>{
    return this.http.get<IOfflineSearch[]>("assets/"+searchKey+".json").pipe(
      tap(data => console.log(data)),
      catchError(this.handleError)
    )
  }

  // Gekopieërd uit Angular - Getting Started 
  // https://github.com/DeborahK/Angular-GettingStarted/blob/master/APM-Final/src/app/products/product.service.ts
  private handleError(err: HttpErrorResponse) {
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }
}
