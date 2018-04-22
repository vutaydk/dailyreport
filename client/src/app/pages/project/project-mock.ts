import { ProjectJson } from '../../entity/project';

export const PROJECT_JSON: ProjectJson[] = [
    {
        id: 1,
        name: 'project 1',
        tasks: [
            {
                taskId: 1,
                taskName: 'Dev',
                timeWork: 0
            },
            {
                taskId: 2,
                taskName: 'Test',
                timeWork: 8
            },
            {
                taskId: 3,
                taskName: 'Search',
                timeWork: 2
            },
            {
                taskId: 1,
                taskName: 'Dev',
                timeWork: 10
            },
            {
                taskId: 2,
                taskName: 'Test',
                timeWork: 45
            },
            {
                taskId: 3,
                taskName: 'Search',
                timeWork: 24
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
                timeWork: 5
            },
            {
                taskId: 2,
                taskName: 'Test',
                timeWork: 8
            }
        ]
    }
];
