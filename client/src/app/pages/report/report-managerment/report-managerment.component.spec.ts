import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportManagermentComponent } from './report-managerment.component';

describe('ReportManagermentComponent', () => {
  let component: ReportManagermentComponent;
  let fixture: ComponentFixture<ReportManagermentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportManagermentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportManagermentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
