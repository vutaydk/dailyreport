import { ProjectJSON } from '../interfaces/project.interface';

export const PROJECT_JSON: ProjectJSON[] = [
    {
        id: 1,
        name: 'project 1',
        tasks: [
            {
                taskId: 1,
                taskName: 'Dev',
                timeWork: 1
            },
            {
                taskId: 2,
                taskName: 'Test',
                timeWork: 2
            },
            {
                taskId: 3,
                taskName: 'Search',
                timeWork: 3
            },
            {
                taskId: 1,
                taskName: 'Dev',
                timeWork: 1
            },
            {
                taskId: 2,
                taskName: 'Test',
                timeWork: 2
            },
            {
                taskId: 3,
                taskName: 'Search',
                timeWork: 3
            },
            {
                taskId: 3,
                taskName: 'Search',
                timeWork: 3
            }
        ]
    },
    {
        id: 2,
        name: 'project 2',
        tasks: [
            {
                taskId: 1,
                taskName: 'Dev',
                timeWork: 1
            },
            {
                taskId: 2,
                taskName: 'Test',
                timeWork: 2
            }
        ]
    }
];
