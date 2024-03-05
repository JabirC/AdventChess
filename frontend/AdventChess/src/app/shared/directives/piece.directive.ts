import { Directive, ElementRef, Renderer2, HostListener, Input, ViewChild, Output, EventEmitter} from '@angular/core';

@Directive({
  selector: '[pieceDrag]',
  standalone: true
})
export class pieceDragDirective {
  @Input() boundary!: { top: number; bottom: number; left: number; right: number; scroll: number; windowSize: number};
  @Input()curRow!: number;
  @Input()curCol!: number;

  @Output() boardChanged: EventEmitter<number[]> = new EventEmitter<number[]>();

  @ViewChild('chessboardContainer') chessboardContainer!: ElementRef;

  prevRow: number = NaN;
  prevCol: number = NaN;
  private isDragging = false;


  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('mousedown', ['$event'])
  onMouseDown(event: MouseEvent): void {
    event.preventDefault();
    this.isDragging = true;
    this.renderer.setStyle(event.target, 'z-index', 2);
  }


  @HostListener('mouseup', ['$event'])
  onMouseUp(event: MouseEvent): void {
    event.preventDefault();
    const rect = this.el.nativeElement.getBoundingClientRect();
    this.isDragging = false;
    this.renderer.setStyle(event.target, 'z-index', 1);

    const className = 'highlighted-element';
    if(!(Number.isNaN(this.prevRow))){
        const prevElement = document.querySelector('.square' + this.prevRow+ this.prevCol);
        this.renderer.removeClass(prevElement, className);      
        if(this.curRow == this.prevRow && this.curCol == this.prevCol){
            this.boardNotChanged();
        }
        else{
            this.boardChanged.emit([this.prevRow,this.prevCol]);
        }
    }
    else{
        this.boardNotChanged();
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
    event.preventDefault();
    const rect = this.el.nativeElement.getBoundingClientRect();
    this.isDragging = false;
    this.renderer.setStyle(event.target, 'z-index', 1);

    const className = 'highlighted-element';
    if(!(Number.isNaN(this.prevRow))){
        const prevElement = document.querySelector('.square' + this.prevRow+ this.prevCol);
        this.renderer.removeClass(prevElement, className);      
        if(this.curRow == this.prevRow && this.curCol == this.prevCol){
            this.boardNotChanged();
        }
        else{
            this.boardChanged.emit([this.prevRow,this.prevCol]);
        }
    }
    else{
        this.boardNotChanged();
    }
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
    }
  }

  private boardNotChanged(){
    const rect = this.el.nativeElement.getBoundingClientRect();
    this.el.nativeElement.style.left = this.curCol * rect.width + 'px';
    this.el.nativeElement.style.top = this.curRow * rect.width + 'px';
  }

  private clamp(value: number, min: number, max: number): number {
    return Math.min(Math.max(value, min), max);
  }


}