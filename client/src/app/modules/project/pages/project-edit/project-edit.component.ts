import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs/Subject';
import { debounceTime } from 'rxjs/operator/debounceTime';
import { ProjectService } from '../../project.service';
import { ProjectForm } from '../../project.form';
import { ProjectDTO, ProjectDTOEdit } from '../../models/project.model';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  styleUrls: ['./project-edit.component.css']
})
export class ProjectEditComponent implements OnInit {

  private _message = new Subject<string>();
  projectForm: FormGroup;
  isSubmitting: boolean;
  id: number;
  message: string;
  type: string;

  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.url.subscribe(
      url => this.onRouterChange(url)
    );
    this.projectForm = ProjectForm.newProjectFormEdit();
    this._message.subscribe((message) => this.message = message);
    debounceTime.call(this._message, 4000).subscribe(() => this.message = null);
  }

  onRouterChange(url): void {
    this.id = Number(url[0].path);
    this.projectService.findById(this.id).subscribe(
      res => this.projectForm = ProjectForm.newProjectFormEdit(res),
      err => { console.log(err); this.router.navigate(['404page']); }
    );
  }

  onSubmit(): void {
    if (this.projectForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      // data
      const startDate = this.projectForm.get('startAt').value;
      const finishDate = this.projectForm.get('finishAt').value;
      const project: ProjectDTOEdit = {
        name: this.projectForm.get('name').value,
        startAt: `${startDate['day']}/${startDate['month']}/${startDate['year']}`,
        finishAt: `${finishDate['day']}/${finishDate['month']}/${finishDate['year']}`
      };
      console.log(project);

      // update project
      this.projectService.update(project, this.id).subscribe(
        res => {
          console.log(res);
          this.projectForm.reset();
          this.type = 'success';
          this._message.next(`Update successfully!`);
          this.isSubmitting = false;
        },
        err => {
          console.log(err.message);
          this.type = 'danger';
          this._message.next(`Update fail! Please try again.`);
          this.isSubmitting = false;
        }
      );
    }
  }

}
