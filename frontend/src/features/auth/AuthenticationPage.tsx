export const AuthenticationPage = () => {
  return (
    <div className='mx-auto max-w-lg mt-20'>
      <h2 className='text-3xl mb-2'>Sign in</h2>
      <span>Hi, welcome back! 👋</span>

      <form className='flex flex-col mt-8' onSubmit={(e) => e.preventDefault()}>
        <label htmlFor='email' className='font-medium'>
          Email
        </label>
        <input
          type='email'
          id='email'
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
          Sign in
        </button>
      </form>

      <div className='flex justify-center mt-2'>
        <span>Not registered yet?</span>
        <span className='text-blue-600 ml-1'>Create an account</span>
      </div>
    </div>
  );
};
