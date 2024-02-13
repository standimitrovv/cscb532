import { Person } from '../../../models/Person';

export interface User {
  id: number;
  email: string;
  userType: 'EMPLOYEE' | 'CLIENT';
  token: string;
  username: string;
  person: Person | null;
}
