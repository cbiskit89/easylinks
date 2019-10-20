import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable, of } from 'rxjs';

export interface EasyLinkAPI {
  items: EasyLink[],
  total_count: number,
}

export interface EasyLink {
  source: string;
  dest: string;
}

@Injectable({
  providedIn: 'root',
})
export class EasyLinkService {
  constructor(private _httpClient: HttpClient) { }

  readonly server = 'http://localhost:8080';
  readonly createURL = `${this.server}/create`;
  readonly deleteURL = `${this.server}/delete`;
  readonly listURL = `${this.server}/list`;
  readonly updateURL = `${this.server}/update`;

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(error);

      return of(result as T);
    }
  }

  createEasyLink(source: string, dest: string): Observable<unknown> {
    const params = new HttpParams().append("source", source).append("dest", dest);
    return this._httpClient.get(this.createURL, { params: params });
  }

  deleteEasyLink(source: string): Observable<unknown> {
    const params = new HttpParams().append("source", source);
    return this._httpClient.get(this.deleteURL, { params: params });
  }

  listEasyLinks(): Observable<EasyLinkAPI> {
    return this._httpClient.get<EasyLinkAPI>(this.listURL);
  }

  updateEasyLink(currentSource: string, update? : { newSource?: string, newDest?: string }): Observable<unknown> {
    let params = new HttpParams();
    if (update.newDest != undefined) {
      params = params.append("newDest", update.newDest);
    }
    if (update.newSource != undefined) {
      params = params.append("newSource", update.newSource);
    }
    params = params.append("currentSource", currentSource);
    return this._httpClient.get(this.updateURL, { params: params });
  }
}