import { Directive, ElementRef, Renderer2, HostListener, Input, ViewChild} from '@angular/core';

@Directive({
  selector: '[pieceDrag]',
  standalone: true
})
export class pieceDragDirective {
  @Input() boundary!: { top: number; bottom: number; left: number; right: number; scroll: number; windowSize: number};
  @ViewChild('chessboardContainer') chessboardContainer!: ElementRef;
  prevRow: number = NaN;
  prevCol: number = NaN;
  constructor(private el: ElementRef, private renderer: Renderer2) {}
  private isDragging = false;

  @HostListener('mousedown', ['$event'])
  onMouseDown(event: MouseEvent): void {
    event.preventDefault();
    this.isDragging = true;
    this.renderer.setStyle(event.target, 'z-index', 2);
  }


  @HostListener('mouseup', ['$event'])
  onMouseUp(event: MouseEvent): void {
    event.preventDefault();
    this.isDragging = false;
    this.renderer.setStyle(event.target, 'z-index', 1);

    const className = 'highlighted-element';
    if(!(Number.isNaN(this.prevRow))){
        const prevElement = document.querySelector('.square' + this.prevRow+ this.prevCol);
        this.renderer.removeClass(prevElement, className);
    }
  }

  @HostListener('document:mousemove', ['$event'])
  onMouseMove(event: MouseEvent): void {
    if (this.isDragging) {
      const rect = this.el.nativeElement.getBoundingClientRect();
      const newX = this.clamp(event.clientX - ((this.boundary.windowSize / 2) - rect.width * 3.5), this.boundary.left - rect.width/2, this.boundary.right - rect.width/2);
      const newY = this.clamp(event.clientY - (141 + rect.width/2) + this.boundary.scroll, this.boundary.top - rect.height/2 , this.boundary.bottom - rect.height/2 );
      this.el.nativeElement.style.left = newX + 'px';
      this.el.nativeElement.style.top = newY  + 'px';

      const mouseOnCol= Math.trunc((newX + rect.width/2) / rect.width) == 8? 7 : Math.trunc((newX + rect.width/2) / rect.width);
      const mouseOnRow= Math.trunc((newY + rect.width/2) / rect.width) == 8? 7 : Math.trunc((newY + rect.width/2) / rect.width)

      const className = 'highlighted-element';

      if(!(Number.isNaN(this.prevRow))){
        if(!(mouseOnRow == this.prevRow && mouseOnCol == this.prevCol)){
            const prevElement = document.querySelector('.square' + this.prevRow+ this.prevCol);
            this.renderer.removeClass(prevElement, className);
        }
      }
      const targetElement = document.querySelector('.square' + mouseOnRow + mouseOnCol);
      this.renderer.addClass(targetElement, className);
      this.prevRow = mouseOnRow;
      this.prevCol = mouseOnCol;
            
    //   const squares = document.querySelectorAll('.square');
    //   squares.forEach((element: Element) => {
    //         const square = element as HTMLElement;
    //         const rowString = square.getAttribute("row");
    //         const row = rowString ? parseInt(rowString, 10) : NaN;
    //         const colString = square.getAttribute("col");
    //         const col = colString ? parseInt(colString, 10) : NaN;
    //         if(mouseOnCol != col || mouseOnRow != row){
    //                 this.renderer.removeClass(square, className);
    //         }
    //     });
    }
  }

  @HostListener('touchstart', ['$event'])
  onTouchStart(event: TouchEvent): void {
    event.preventDefault();
    this.isDragging = true;
    this.renderer.setStyle(event.target, 'z-index', 2);
  }

  @HostListener('touchend', ['$event'])
  onTouchEnd(event: TouchEvent): void {
    this.isDragging = false;
    this.renderer.setStyle(event.target, 'z-index', 1);
  }

  @HostListener('document:touchmove', ['$event'])
  onTouchMove(event: TouchEvent): void {
    if (this.isDragging) {
      const rect = this.el.nativeElement.getBoundingClientRect();

      const newX = this.clamp(
        event.touches[0].clientX - ((this.boundary.windowSize / 2) - rect.width * 3.5),
        this.boundary.left - rect.width / 2,
        this.boundary.right - rect.width / 2
      );

      const newY = this.clamp(
        event.touches[0].clientY - (141 + rect.width / 2) + this.boundary.scroll,
        this.boundary.top - rect.height / 2,
        this.boundary.bottom - rect.height / 2
      );

      this.el.nativeElement.style.left = newX + 'px';
      this.el.nativeElement.style.top = newY + 'px';
    }
  }

  private clamp(value: number, min: number, max: number): number {
    return Math.min(Math.max(value, min), max);
  }


}