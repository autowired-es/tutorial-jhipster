import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOferta, Oferta } from 'app/shared/model/oferta.model';
import { OfertaService } from './oferta.service';

@Component({
  selector: 'jhi-oferta-update',
  templateUrl: './oferta-update.component.html',
})
export class OfertaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    descripcion: [],
    precioHabitual: [null, [Validators.required, Validators.min(0.01)]],
    precioRebajado: [null, [Validators.required, Validators.min(0.1)]],
    link: [null, [Validators.pattern('Server application generated successfully.')]],
  });

  constructor(protected ofertaService: OfertaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ oferta }) => {
      this.updateForm(oferta);
    });
  }

  updateForm(oferta: IOferta): void {
    this.editForm.patchValue({
      id: oferta.id,
      descripcion: oferta.descripcion,
      precioHabitual: oferta.precioHabitual,
      precioRebajado: oferta.precioRebajado,
      link: oferta.link,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const oferta = this.createFromForm();
    if (oferta.id !== undefined) {
      this.subscribeToSaveResponse(this.ofertaService.update(oferta));
    } else {
      this.subscribeToSaveResponse(this.ofertaService.create(oferta));
    }
  }

  private createFromForm(): IOferta {
    return {
      ...new Oferta(),
      id: this.editForm.get(['id'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value,
      precioHabitual: this.editForm.get(['precioHabitual'])!.value,
      precioRebajado: this.editForm.get(['precioRebajado'])!.value,
      link: this.editForm.get(['link'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOferta>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
