import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CreateDialogComponent } from './create-dialog/create-dialog.component';
import { EasyLink, EasyLinkService } from './easylink.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Easy Links';

  constructor(
    public dialog: MatDialog,
    private easyLinkService: EasyLinkService,
  ) { }

  ngOnInit() {
    this.listLinks();
  }

  links: EasyLink[] = [];
  displayedColumns: string[] = ['source', 'dest', 'delete'];

  readonly placeholderSource = "Easy Link Name";
  readonly placeholderDest = "Website URL";

  openCreateDialog(): void {
    console.log('Attempting to open dialog.');
    const dialogRef = this.dialog.open(CreateDialogComponent, {
      width: '100px;'
    });
    dialogRef.afterClosed().subscribe(result => {
      this.listLinks()
    })
  }

  deleteLink(source: string): void {
    this.easyLinkService.deleteEasyLink(source).pipe(
      catchError(this.handleError())
    ).subscribe(_ => this.listLinks());
  }

  listLinks(): void {
    this.easyLinkService.listEasyLinks().pipe(
      catchError(this.handleError()),
    ).subscribe(
      (links: EasyLink[]) => this.links = links
    );
  }

  updateLink(source: string, dest: string): void {
    this.easyLinkService.updateEasyLink(source, dest).pipe(
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