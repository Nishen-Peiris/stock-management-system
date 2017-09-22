import {Component, OnInit} from '@angular/core';
import {User} from '../../shared/User';
import {USER_TYPES} from '../../shared/user-types';

@Component({
  templateUrl: './users.component.html'
})

export class UsersComponent implements OnInit {
  createMode: boolean;
  users: User[];
  user: User;
  rowsOnPage = 10;
  sortBy = "name";
  sortOrder = "asc";
  userTypes = USER_TYPES;

  constructor() {

  }

  ngOnInit(): void {
    this.createMode = true;
    this.user = new User();
    this.users = new Array();
    // this.getUsers();
    let user1 = new User();
    user1.firstName = "Nishen";
    user1.lastName = "Peiris";
    user1.phoneNumber = "0775328115";
    user1.email = "nishenkpeiris@gmail.com";
    user1.type = "Administrator";
    this.users[0] = user1;
    let user2 = new User();
    user2.firstName = "Chamith";
    user2.lastName = "Shanaka";
    user2.phoneNumber = "0711234567";
    user2.email = "chamithashanaka@gmail.com";
    user2.type = "Distributor";
    this.users[1] = user2;
  }
}
