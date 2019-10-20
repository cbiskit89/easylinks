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
  isEditingDest: boolean[];
  isEditingSource: boolean[];

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

  setDestEdit(item: number) {
    this.isEditingDest[item] = !this.isEditingDest[item];
  }

  setSourceEdit(item: number) {
    this.isEditingSource[item] = !this.isEditingSource[item];
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
    this.isEditingDest = new Array<boolean>(this.links.length);
    this.isEditingSource = new Array<boolean>(this.links.length);
  }

  updateLink(item: number, source: string, newDest: string): void {
    this.easyLinkService.updateEasyLink(source, { newDest: newDest }).pipe(
      catchError(this.handleError())
    ).subscribe(_ => this.listLinks());
    this.isEditingDest[item] = !this.isEditingDest[item];
  }

  updateSource(item: number, currentSource: string, newSource: string): void {
    this.easyLinkService.updateEasyLink(currentSource, { newSource: newSource }).pipe(
      catchError(this.handleError())
    ).subscribe(_ => this.listLinks());
    this.isEditingSource[item] = !this.isEditingSource[item];
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(error);

      return of(result as T);
    }
  }
}