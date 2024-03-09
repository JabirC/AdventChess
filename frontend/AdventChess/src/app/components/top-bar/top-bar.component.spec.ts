import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopBarComponent } from './top-bar.component';

describe('TopBarComponent', () => {
  let component: TopBarComponent;
  let fixture: ComponentFixture<TopBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TopBarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TopBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a logo', () => {
    const logoElement = fixture.nativeElement.querySelector('.logo');
    expect(logoElement).toBeTruthy();
    expect(logoElement.height).toEqual(40);
  });

  it('should have have a title: AdventChess', () => {
    const titles = fixture.nativeElement.querySelector('h1')?.textContent;
    expect(titles).toContain("AdventChess");
  });

  it('should have have a Login Button', () => {
    const button = fixture.nativeElement.querySelector(".buttons-container button:last-child");
    expect(button.textContent).toEqual("Login");
  });

  it('should have have a Play Button', () => {
    const button = fixture.nativeElement.querySelector(".buttons-container button:first-child");
    expect(button.textContent).toEqual("Play");
  });

});
