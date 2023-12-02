export const SignUpPage = () => {
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
    </>
  );
};
