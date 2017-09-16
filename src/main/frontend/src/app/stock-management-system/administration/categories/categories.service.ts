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

  getCategories(): Promise<Category[]> {
    return this.http.get(this.categoriesUrl)
      .toPromise()
      .then(response => response.json().data as Category[])
      .catch(this.handleError);
  }

  create(category: Category): Promise<Category> {
    return this.http
      .post(this.categoriesUrl, category, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(category: Category): Promise<Category> {
    return this.http
      .put(this.categoriesUrl, JSON.stringify(category), {headers: this.headers})
      .toPromise()
      .then(() => category)
      .catch(this.handleError);
  }

  delete(category: Category): Promise<void> {
    return this.http.delete(this.categoriesUrl, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
