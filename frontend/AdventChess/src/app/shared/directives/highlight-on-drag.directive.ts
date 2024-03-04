import { Directive, ElementRef, Renderer2, HostListener } from '@angular/core';

@Directive({
  selector: '[appHighlightOnDrag]',
  standalone: true
})
export class HighlightOnDragDirective {
  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('dragenter', ['$event']) ondragenter(event: Event) {
    this.highlight(true);
  }

  @HostListener('dragleave', ['$event']) onDragLeave(event: Event) {
    this.highlight(false);
  }


  private highlight(shouldHighlight: boolean) {
    const className = 'highlighted-element'; // Add your desired class name
    if (shouldHighlight) {
      this.renderer.addClass(this.el.nativeElement, className);
    } else {
      this.renderer.removeClass(this.el.nativeElement, className);
    }
  }
}