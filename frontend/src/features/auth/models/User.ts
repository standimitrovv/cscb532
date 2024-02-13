export interface User {
  email: string;
  userType: 'EMPLOYEE' | 'CLIENT';
  token: string;
  username: string;
}
