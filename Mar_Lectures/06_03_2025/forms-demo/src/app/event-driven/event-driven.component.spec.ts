import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventDrivenComponent } from './event-driven.component';

describe('EventDrivenComponent', () => {
  let component: EventDrivenComponent;
  let fixture: ComponentFixture<EventDrivenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventDrivenComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventDrivenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
