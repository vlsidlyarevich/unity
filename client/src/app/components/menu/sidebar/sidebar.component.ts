import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { UserSocial } from "../../../models/user-social.model";
import { ProfileService } from "../../../services/profile.service";
import { ProfileStoreService } from "../../../services/store/profile-store.service";
import { config } from "../../../config/config";
import { ImageService } from "../../../services/image.service";

@Component({
  selector: 'app-side-bar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  @ViewChild("menu")
  menu: ElementRef;

  public imageToShow: any;
  public userSocial: UserSocial;
  private imageId;
  private isImageLoading = true;

  constructor(private profileService: ProfileService,
              private renderer: Renderer2,
              private imageService: ImageService,
              private profileStoreService: ProfileStoreService) {
  }

  ngOnInit() {
    this.profileStoreService.getUserSocial().subscribe((userSocial: any) => {
      this.userSocial = userSocial;
      this.imageId = userSocial.image;
      this.getImageFromService();
    });
  }

  public getUserImageUrl(): string {
    if (this.imageId) {
      return config.imageApi + '/' + this.imageId;
    } else {
      return null;
    }
  }

  getImageFromService() {
    if (this.userSocial.image) {
      this.isImageLoading = true;
      this.imageService.getImage(this.getUserImageUrl())
        .subscribe(data => {
          this.imageToShow = data;
          this.isImageLoading = false;
        }, error => {
          this.isImageLoading = false;
          console.log(error);
        });
    }
  }

  activate(event: Event) {
    //получили айдишник кликнутого элемента меню
    const elementId: string = (event.target as Element).id;
    //получили этот элемент меню
    const element = this.menu.nativeElement.getElementById(elementId);
    //развернули подменю если оно есть
    const ul = element.querySelector("ul");
    this.renderer.addClass(ul, "visible-menu");
    //сделали активным этот элемент меню
    this.renderer.addClass(element, "active");

    //свернули другие меню, сделали неактивными
  }
}
