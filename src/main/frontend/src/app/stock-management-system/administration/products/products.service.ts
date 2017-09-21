import {Injectable} from "@angular/core";
import {Product} from "../../shared/Product";
import {Headers, Http} from "@angular/http";

@Injectable()
export class ProductsService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private productsUrl = 'api/product';

  constructor(private http: Http) {

  }

  getProducts() {
    return this.http.get(this.productsUrl);
  }

  create(product: Product) {
    return this.http.post(this.productsUrl, product, {headers: this.headers});
  }

  update(product: Product) {
    return this.http.put(this.productsUrl, product, {headers: this.headers});
  }

  delete(product: Product) {
    return this.http.delete(this.productsUrl, {headers: this.headers, body: product});
  }
}
