import { SignInPage } from './SignInPage';
import { SignUpPage } from './SignUpPage';
import { useAuth } from './hooks/UseAuth';

export const AuthenticationPage = () => {
  const { isRegistered } = useAuth();

  return (
    <div className='mx-auto max-w-lg mt-20'>
      {isRegistered ? <SignInPage /> : <SignUpPage />}
    </div>
  );
};
