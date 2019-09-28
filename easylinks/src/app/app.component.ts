import { Component, Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable, merge, of } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';

export interface EasyLinkAPI {
  items: EasyLink[],
  total_count: number,
}

export interface EasyLink {
  source: string;
  dest: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Easy Links';

  constructor(
    private easyLinkService: EasyLinkService,
    private http: HttpClient,
  ) { }

  ngOnInit() {
    this.listLinks();
  }

  links: EasyLink[] = [];
  displayedColumns: string[] = ['source', 'dest', 'delete'];

  readonly placeholderSource = "Easy Link Name";
  readonly placeholderDest = "Website URL";

  readonly server = 'http://localhost:8080';
  readonly createURL = `${this.server}/create`;
  readonly deleteURL = `${this.server}/delete`;
  readonly listURL = `${this.server}/list`;
  readonly updateURL = `${this.server}/update`;

  createLink(source: string, dest: string): void {
    const params = new HttpParams().append("source", source).append("dest", dest);
    this.http.get(this.createURL, { params: params }).pipe(
      tap(_ => console.log("creating an easy link")),
      catchError(this.handleError())
    ).subscribe(_ => this.listLinks());
  }

  deleteLink(source: string): void {
    const params = new HttpParams().append("source", source);
    this.http.get(this.deleteURL, { params: params }).pipe(
      tap(_ => console.log("deleting an easy link")),
      catchError(this.handleError())
    ).subscribe(_ => this.listLinks());
  }

  listLinks(): void {
    this.easyLinkService.listEasyLinks().pipe(
      tap(_ => console.log("listing easy links")),
      catchError(this.handleError()),
    ).subscribe(
      (links: EasyLink[]) => this.links = links
    );
  }

  updateLink(source: string, dest: string): void {
    const params = new HttpParams().append("source", source).append("dest", dest);
    this.http.get(this.updateURL, { params: params }).pipe(
      tap(_ => console.log("updating easy links")),
      catchError(this.handleError())
    ).subscribe(_ => this.listLinks());
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(error);

      return of(result as T);
    }
  }
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

  listEasyLinks(): Observable<EasyLinkAPI> {
    return this._httpClient.get<EasyLinkAPI>(this.listURL);
  }
}