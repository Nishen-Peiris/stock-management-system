import {Component, OnInit} from '@angular/core';
import {Category} from "../../shared/Category";
import {CategoriesService} from "./categories.service";

@Component({
  templateUrl: './categories.component.html'
})

export class CategoriesComponent implements OnInit {
  createMode: boolean;
  categories: Category[];
  category: Category;

  constructor(private categoriesService: CategoriesService) {

  }

  ngOnInit(): void {
    this.createMode = true;
    this.category = new Category();
    this.categories = new Array();
    // this.getCategories();
    let category0 = new Category();
    let category1 = new Category();
    let category2 = new Category();
    category0.name = "ASDASDASD";
    category1.name = "QWEQWEQWE";
    category2.name = "ZXCZXCZXC";
    this.categories[0] = category0;
    this.categories[1] = category1;
    this.categories[2] = category2;
  }

  getCategories() {
    this.categoriesService.getCategories().subscribe(
      data => {
      },
      error => {
      }
    );
  }

  add(category: Category) {
    this.categoriesService.create(this.category).subscribe(
      data => {
        console.log("Saved category");
        this.category = new Category();
      },
      error => {
        const status = error.status.toString();
        if (status === "409") {
          console.log("A category exists with the same name. Please use a different name.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );
  }

  edit(category: Category) {
    this.categoriesService.update(category).subscribe(
      data => {
      },
      error => {
      }
    );
  }

  delete(category: Category) {
    this.categoriesService.delete(category).subscribe(
      data => {
      },
      error => {
      }
    );
  }

  selectCategory(category: Category) {
    this.category = category;
  }
}
