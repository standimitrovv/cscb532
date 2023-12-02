import { useAuth } from './hooks/UseAuth';

export const SignUpPage = () => {
  const { openSignInPage } = useAuth();

  return (
    <>
      <h2 className='text-3xl font-semibold mb-2'>Sign up</h2>

      <form className='flex flex-col mt-8' onSubmit={(e) => e.preventDefault()}>
        <label htmlFor='username' className='font-medium'>
          Username
        </label>
        <input
          type='text'
          id='username'
          className='rounded-md py-2 px-3 border mt-1 mb-4'
          placeholder='How do you want to be called?'
        />

        <label htmlFor='sign-up-email' className='font-medium'>
          Email
        </label>
        <input
          type='email'
          id='sign-up-email'
          className='rounded-md py-2 px-3 border mt-1 mb-4'
          placeholder='E.g johndoe@gmail.com'
        />

        <label htmlFor='password' className='font-medium'>
          Password
        </label>
        <input
          type='password'
          id='password'
          className='rounded-md py-2 px-3 border mt-1 mb-6'
          placeholder='Enter your password'
        />

        <button
          type='submit'
          className='bg-blue-600 py-2 rounded-md text-white'
        >
          Sign up
        </button>
      </form>

      <div className='flex justify-center mt-2'>
        <span>Already have an account?</span>
        <div
          className='flex items-center cursor-pointer group'
          onClick={openSignInPage}
        >
          <span className='text-blue-600 mx-1'>Sign in</span>
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
