import {Component} from '@angular/core';

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  activeTab: string;

  public setActiveTab(activeTab: string) {
    this.activeTab = activeTab;
  }

}
