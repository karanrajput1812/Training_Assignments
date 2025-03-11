import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemoService } from './demo.service';
import { DemoCompComponent } from './demo-comp/demo-comp.component';

describe('DemoService', () => {
  let service: DemoService;
  let fixture: ComponentFixture<DemoCompComponent>;
  let component: DemoCompComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DemoService);
    // service = TestBed.get(DemoService);            // Deprecated

    fixture = TestBed.createComponent(DemoCompComponent);
    component = fixture.componentInstance;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should have invoked', ()=> {
    spyOn(service, 'doSomeTask');
    component.method()
    expect(service.doSomeTask).toHaveBeenCalled();
  })
});
