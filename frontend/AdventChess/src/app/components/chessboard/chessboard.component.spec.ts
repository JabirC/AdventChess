import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChessboardComponent } from './chessboard.component';

describe('ChessboardComponent', () => {
  let component: ChessboardComponent;
  let fixture: ComponentFixture<ChessboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChessboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChessboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have 64 squares', () => {
    const squares = fixture.nativeElement.querySelectorAll(".square");
    expect(squares.length).toEqual(64);
  });

  it('should have squares that have 1/8th width and height of the chessboard width', () => {
    const board = fixture.nativeElement.querySelector(".chessboard");
    const boardWidth = board.getBoundingClientRect().width;
    const squares = fixture.nativeElement.querySelectorAll(".square");
    for(const square of squares){
      expect(square.getBoundingClientRect().width).toBeCloseTo(boardWidth/8, 1);
      expect(square.getBoundingClientRect().height).toBeCloseTo(boardWidth/8, 1);
    }
  });
});
