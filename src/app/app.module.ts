import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AllPatientsComponent} from './stock-management-system/administration/users/all-patients.component';

import {AppRoutingModule} from './app-routing.module';

@NgModule({
  imports: [BrowserModule, AppRoutingModule, FormsModule],
  declarations: [AppComponent, AllPatientsComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
