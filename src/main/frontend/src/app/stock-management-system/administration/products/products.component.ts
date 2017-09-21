import {Component, OnInit} from '@angular/core';
import {Product} from "../../shared/Product";
import {ProductsService} from "./products.service";
import {CategoriesService} from "../categories/categories.service";
import {Category} from "../../shared/Category";

@Component({
  templateUrl: './products.component.html'
})

export class ProductsComponent implements OnInit {
  createMode: boolean;
  products: Product[];
  categories: Category[];
  product: Product;
  rowsOnPage = 10;
  sortBy = "product";
  sortOrder = "asc";

  constructor(private productsService: ProductsService, private categoriesService: CategoriesService) {

  }

  ngOnInit(): void {
    this.createMode = true;
    this.product = new Product();
    this.products = new Array();
    this.categories = new Array();
    this.getCategories();
    this.getProducts();
  }

  getProducts() {
    this.productsService.getProducts().subscribe(
      data => {
        this.products = data.json();
      },
      error => {
        console.log("Failed to receive the list of products. Please try again later.");
      }
    );
  }

  add() {
    this.productsService.create(this.product).subscribe(
      data => {
        console.log("Saved product");
        this.product = new Product();
        this.getProducts();
      },
      error => {
        const status = error.status.toString();
        if (status === "409") {
          console.log("A product exists with the same name. Please use a different name.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );
  }

  edit() {
    this.productsService.update(this.product).subscribe(
      data => {
        console.log("Updated product");
        this.product = new Product();
        this.getProducts();
      },
      error => {
        console.log("Internal server error occurred while processing your request. Please try again later.");
      }
    );
  }

  delete(product: Product) {
    this.productsService.delete(product).subscribe(
      data => {
        console.log("Deleted product");
        this.getProducts();
      },
      error => {
        console.log("Internal server error occurred while processing your request. Please try again later.");
      }
    );
  }

  selectProduct(product: Product) {
    this.product = product;
    this.createMode = false;
  }

  getCategories() {
    this.categoriesService.getCategories().subscribe(
      data => {
        this.categories = data.json();
      },
      error => {
        console.log("Failed to receive the list of categories. Please try again later.");
      }
    );
  }

  onChangeCategory(id: number) {
    for (var i = 0; i < this.categories.length; i++) {
      if (id == this.categories[i].id) {
        this.product.category = this.categories[i];
      }
    }
  }
}
