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
    // this.getCategories();
  }

  getCategories() {
    this.categoriesService
      .getCategories()
      .then(categories => this.categories = categories)
      .catch();
  }

  add(category: Category) {
    this.categoriesService.create(category)
      .then(this.onAddSuccess)
      .catch(this.onAddFailure);
  }

  edit(category: Category) {
    this.categoriesService.update(category)
      .then(this.getCategories)
      .catch();
  }

  delete(category: Category) {
    this.categoriesService.delete(category)
      .then(this.getCategories)
      .catch();
  }

  selectCategory(category: Category) {
    this.category = category;
  }

  private onAddSuccess() {
    console.log("Saved category");
  }

  private onAddFailure(error: any) {
    const status = error.status.toString();
    if (status === "409") {
      console.log("A category exists with the same name. Please use a different name.");
    } else {
      console.log("Internal server error occurred while processing your request. Please try again later.");
    }
  }
}
