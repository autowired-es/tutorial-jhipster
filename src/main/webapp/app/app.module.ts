import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { AfiliadosSharedModule } from 'app/shared/shared.module';
import { AfiliadosCoreModule } from 'app/core/core.module';
import { AfiliadosAppRoutingModule } from './app-routing.module';
import { AfiliadosHomeModule } from './home/home.module';
import { AfiliadosEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    AfiliadosSharedModule,
    AfiliadosCoreModule,
    AfiliadosHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    AfiliadosEntityModule,
    AfiliadosAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class AfiliadosAppModule {}
