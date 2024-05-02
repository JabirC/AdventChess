import { Component, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mode',
  standalone: true,
  imports: [],
  templateUrl: './mode.component.html',
  styleUrl: './mode.component.scss'
})
export class ModeComponent {
  gameMode: String = "adventure";
  gameTime: Number = 5;

  @ViewChild('adventure') Adventure!: ElementRef;
  @ViewChild('classic') Classic!: ElementRef;

  @ViewChild('button5') button5!: ElementRef;
  @ViewChild('button10') button10!: ElementRef;
  @ViewChild('button20') button20!: ElementRef;
  

  constructor(private router: Router){
  }

  startGame(){
    this.router.navigate(['/game'], {queryParams:{mode: this.gameMode, time: this.gameTime}});
  }

  goBack(){
    this.router.navigate(['/home']);
  }

  changeMode(mode: string){
     if(mode === "adventure"){
      this.Classic.nativeElement.classList.remove('option');
      this.Adventure.nativeElement.classList.add('option')
     }
     else{
      this.Adventure.nativeElement.classList.remove('option')
      this.Classic.nativeElement.classList.add('option');
     }
     this.gameMode = mode;
  }

  changeTime(time: string){
    if(time === "5"){
      this.button10.nativeElement.classList.remove('option');
      this.button20.nativeElement.classList.remove('option');
      this.button5.nativeElement.classList.add('option');
      this.gameTime = 5;
    }
    else if(time === "10"){
      this.button5.nativeElement.classList.remove('option');
      this.button20.nativeElement.classList.remove('option');
      this.button10.nativeElement.classList.add('option');
      this.gameTime = 10;
    }
    else{
      this.button5.nativeElement.classList.remove('option');
      this.button10.nativeElement.classList.remove('option');
      this.button20.nativeElement.classList.add('option');
      this.gameTime = 20;
    }
  }

}
