import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {DataTableModule} from 'angular2-datatable';

import {AppComponent} from './app.component';
import {UsersComponent} from './stock-management-system/administration/users/users.component';
import {ProductsComponent} from './stock-management-system/administration/products/products.component';
import {CategoriesComponent} from './stock-management-system/administration/categories/categories.component';

import {UsersService} from './stock-management-system/administration/users/users.service';
import {ProductsService} from './stock-management-system/administration/products/products.service';
import {CategoriesService} from './stock-management-system/administration/categories/categories.service';

import {AppRoutingModule} from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent, UsersComponent, ProductsComponent, CategoriesComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, FormsModule, HttpModule, DataTableModule
  ],
  providers: [UsersService, ProductsService, CategoriesService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
