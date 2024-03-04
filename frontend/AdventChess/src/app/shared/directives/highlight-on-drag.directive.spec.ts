import { TestBed, ComponentFixture } from '@angular/core/testing';
import { Component, DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';
import { HighlightOnDragDirective } from './highlight-on-drag.directive';

@Component({
  template: `<div appHighlightOnDrag></div>`
})
class TestComponent {}

describe('HighlightOnDragDirective', () => {
  let fixture: ComponentFixture<TestComponent>;
  let component: TestComponent;
  let directive: HighlightOnDragDirective;
  let divElement: DebugElement;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TestComponent, HighlightOnDragDirective]
    });

    fixture = TestBed.createComponent(TestComponent);
    component = fixture.componentInstance;
    divElement = fixture.debugElement.query(By.directive(HighlightOnDragDirective));
    directive = divElement.injector.get(HighlightOnDragDirective);
  });

  it('should create an instance', () => {
    expect(directive).toBeTruthy();
  });
});