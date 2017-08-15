import {Component} from '@angular/core';

import {PRODUCT_CATEGORIES} from '../../shared/product-categories';

@Component({
  templateUrl: './products.component.html'
})

export class ProductsComponent {
  name: string;
  category: string;
  productCategories = PRODUCT_CATEGORIES;
}
