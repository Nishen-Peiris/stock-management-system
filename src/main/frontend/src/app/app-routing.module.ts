import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {UsersComponent} from './stock-management-system/administration/users/users.component';
import {ProductsComponent} from './stock-management-system/administration/products/products.component';
import {CategoriesComponent} from './stock-management-system/administration/categories/categories.component';
import {HomeComponent} from "./stock-management-system/administration/home/home.component";

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'users', component: UsersComponent},
  {path: 'products', component: ProductsComponent},
  {path: 'categories', component: CategoriesComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
