import {Component, OnInit} from '@angular/core';
import {User} from '../../shared/User';
import {USER_TYPES} from '../../shared/user-types';
import {UsersService} from './users.service';

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

  constructor(private usersService: UsersService) {

  }

  ngOnInit(): void {
    this.createMode = true;
    this.user = new User();
    this.users = new Array();
    this.getUsers();
  }

  getUsers() {
    this.usersService.getUsers().subscribe(
      data => {
        this.users = data.json();
      },
      error => {
        console.log("Failed to receive the list of users. Please try again later.");
      }
    );
  }

  add() {
    this.usersService.create(this.user).subscribe(
      data => {
        console.log("Saved user");
        this.user = new User();
        this.getUsers();
      },
      error => {
        const status = error.status.toString();
        if (status === "409") {
          console.log("A user exists with the same email. Please use a different email.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );
  }

  edit() {
    this.usersService.update(this.user).subscribe(
      data => {
        console.log("Updated user");
        this.user = new User();
        this.getUsers();
      },
      error => {
        console.log("Internal server error occurred while processing your request. Please try again later.");
      }
    );
  }

  delete(user: User) {
    this.usersService.delete(user).subscribe(
      data => {
        console.log("Deleted user");
        this.getUsers();
      },
      error => {
        console.log("Internal server error occurred while processing your request. Please try again later.");
      }
    );
  }

  selectUser(user: User) {
    this.user = user;
    this.createMode = false;
  }
}
