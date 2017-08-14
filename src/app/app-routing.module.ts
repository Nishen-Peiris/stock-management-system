import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {AllPatientsComponent} from './stock-management-system/administration/users/all-patients.component';

const routes: Routes = [
  {path: 'all-patients', component: AllPatientsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
