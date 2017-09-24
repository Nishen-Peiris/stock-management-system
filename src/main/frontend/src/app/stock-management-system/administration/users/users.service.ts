import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {User} from "../../shared/User";

@Injectable()
export class UsersService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private usersUrl = 'api/user';

  constructor(private http: Http) {

  }

  getUsers() {
    return this.http.get(this.usersUrl);
  }

  create(user: User) {
    return this.http.post(this.usersUrl, user, {headers: this.headers});
  }

  update(user: User) {
    return this.http.put(this.usersUrl, user, {headers: this.headers});
  }

  delete(user: User) {
    return this.http.delete(this.usersUrl, {headers: this.headers, body: user});
  }
}
