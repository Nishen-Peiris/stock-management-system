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
  rowsOnPage = 10;
  sortBy = "name";
  sortOrder = "asc";

  constructor(private categoriesService: CategoriesService) {

  }

  ngOnInit(): void {
    this.createMode = true;
    this.category = new Category();
    this.categories = new Array();
    this.getCategories();
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

  add(category: Category) {
    this.categoriesService.create(this.category).subscribe(
      data => {
        console.log("Saved category");
        this.category = new Category();
        this.getCategories();
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
        console.log("Updated category");
        this.category = new Category();
        this.getCategories();
      },
      error => {
        console.log("Internal server error occurred while processing your request. Please try again later.");
      }
    );
  }

  delete(category: Category) {
    this.categoriesService.delete(category).subscribe(
      data => {
        console.log("Deleted category");
        this.getCategories();
      },
      error => {
        const status = error.status.toString();
        if (status === "400") {
          console.log("There are one or more products associated with this category. First, remove those products and then try again.");
        } else {
          console.log("Internal server error occurred while processing your request. Please try again later.");
        }
      }
    );
  }

  selectCategory(category: Category) {
    this.category = category;
    this.createMode = false;
  }
}
