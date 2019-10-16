import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { EasyLinkService } from '../easylink.service';

export interface DialogData {
  source: string;
  dest: string;
}

@Component({
  selector: 'app-create-dialog',
  templateUrl: './create-dialog.component.html',
  styleUrls: ['./create-dialog.component.scss']
})
export class CreateDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<CreateDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,

    private easyLinkService: EasyLinkService,
    private formBuilder: FormBuilder,
  ) { 
    this.createLinkForm = this.formBuilder.group({
      source: '',
      destination: ''
    })
  }

  createLinkForm;

  requiredField = new FormControl('', [Validators.required]);

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(createLinkData: FormGroup): void {
    this.easyLinkService.createEasyLink(
      createLinkData.get('source').value,
      createLinkData.get('destination').value).pipe(
      catchError(this.handleError())
    ).subscribe(_ => this.dialogRef.close());
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(error);

      return of(result as T);
    }
  }
}
