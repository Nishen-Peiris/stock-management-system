import {Injectable} from "@angular/core";
import {Category} from "../../shared/Category";
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class CategoriesService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private categoriesUrl = 'api/category';

  constructor(private http: Http) {

  }

  getCategories() {
    return this.http.get(this.categoriesUrl);
  }

  create(category: Category) {
    return this.http.post(this.categoriesUrl, category, {headers: this.headers});
  }

  update(category: Category) {
    return this.http.put(this.categoriesUrl, category, {headers: this.headers});
  }

  delete(category: Category) {
    return this.http.delete(this.categoriesUrl, {headers: this.headers, body: category});
  }
}
