import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AfiliadosSharedModule } from 'app/shared/shared.module';
import { OfertaComponent } from './oferta.component';
import { OfertaDetailComponent } from './oferta-detail.component';
import { OfertaUpdateComponent } from './oferta-update.component';
import { OfertaDeleteDialogComponent } from './oferta-delete-dialog.component';
import { ofertaRoute } from './oferta.route';

@NgModule({
  imports: [AfiliadosSharedModule, RouterModule.forChild(ofertaRoute)],
  declarations: [OfertaComponent, OfertaDetailComponent, OfertaUpdateComponent, OfertaDeleteDialogComponent],
  entryComponents: [OfertaDeleteDialogComponent],
})
export class AfiliadosOfertaModule {}
