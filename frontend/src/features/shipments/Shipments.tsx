import { useAuth } from '../auth/hooks/UseAuth';
import { User } from '../auth/models/User';
import { ClientView } from './ClientView';
import { EmployeeView } from './EmployeeView';

const view: Record<User['userType'], JSX.Element> = {
  CLIENT: <ClientView />,
  EMPLOYEE: <EmployeeView />,
};

export const Shipments = () => {
  const { user } = useAuth();

  if (!user) {
    return null;
  }

  return <div className='p-2 my-4'>{view[user.userType]}</div>;
};
