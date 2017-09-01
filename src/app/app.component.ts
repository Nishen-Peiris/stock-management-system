import {Component} from '@angular/core';

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
})
export class AppComponent {
  activeTab: string;

  public setActiveTab(activeTab: string) {
    this.activeTab = activeTab;
  }
}
