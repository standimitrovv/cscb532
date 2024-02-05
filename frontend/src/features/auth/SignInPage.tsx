import { useState } from 'react';
import { useAuth } from './hooks/UseAuth';
import { ISignIn } from './state/AuthenticationProvider';

export const SignInPage: React.FunctionComponent = () => {
  const { signIn, openSignUpPage, isProcessing } = useAuth();

  return (
    <>
      <h2 className='text-3xl font-semibold mb-2'>Sign in</h2>
      <span>Hi, welcome back! 👋</span>

      <SignInForm onSubmit={signIn} isProcessing={isProcessing} />

      <div className='flex justify-center mt-2'>
        <span>Not registered yet?</span>
        <div
          className='flex items-center cursor-pointer group'
          onClick={openSignUpPage}
        >
          <span className='text-blue-600 mx-1'>Create an account</span>
          <svg
            className='w-3 h-3 text-blue-600 group-hover:translate-x-1 group-hover:transition-transform'
            aria-hidden='true'
            xmlns='http://www.w3.org/2000/svg'
            fill='none'
            viewBox='0 0 14 10'
          >
            <path
              stroke='currentColor'
              stroke-linecap='round'
              stroke-linejoin='round'
              stroke-width='2'
              d='M1 5h12m0 0L9 1m4 4L9 9'
            />
          </svg>
        </div>
      </div>
    </>
  );
};

const SignInForm: React.FunctionComponent<{
  onSubmit: ISignIn;
  isProcessing: boolean;
}> = ({ onSubmit, isProcessing }) => {
  const [username, setUsername] = useState<string>('');

  const [password, setPassword] = useState<string>('');

  const isSubmitButtonDisabled =
    !username.trim().length || !password.trim().length || isProcessing;

  const handleFormSubmit = () => {
    onSubmit({ username, password });
  };

  return (
    <form className='flex flex-col mt-8' onSubmit={(e) => e.preventDefault()}>
      <label htmlFor='username' className='font-medium'>
        Username
      </label>
      <input
        type='username'
        id='username'
        className='rounded-md py-2 px-3 border mt-1 mb-4'
        placeholder='E.g johndoe'
        disabled={isProcessing}
        required
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />

      <label htmlFor='password' className='font-medium'>
        Password
      </label>
      <input
        type='password'
        id='password'
        className='rounded-md py-2 px-3 border mt-1 mb-6'
        placeholder='Enter your password'
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        disabled={isProcessing}
        required
      />

      <button
        type='submit'
        className='bg-blue-600 py-2 rounded-md text-white'
        onClick={handleFormSubmit}
        disabled={isSubmitButtonDisabled}
      >
        {isProcessing ? 'Loading...' : 'Sign in'}
      </button>
    </form>
  );
};
