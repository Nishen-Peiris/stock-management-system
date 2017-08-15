import {Component} from '@angular/core';

import {USER_TYPES} from '../../shared/user-types';

@Component({
  templateUrl: './users.component.html'
})

export class UsersComponent {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  userTypes = USER_TYPES;
}
