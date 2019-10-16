import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { CreateDialogComponent } from './create-dialog/create-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateDialogComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    MatButtonModule,
    MatDialogModule,
    MatIconModule,
    MatInputModule,
    MatTableModule,
    MatToolbarModule,
    ReactiveFormsModule
  ],
  entryComponents: [
    CreateDialogComponent,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
