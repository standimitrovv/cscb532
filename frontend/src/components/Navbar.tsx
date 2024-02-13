import { useState } from 'react';
import { useAuth } from '../features/auth/hooks/UseAuth';

export const Navbar = () => {
  const { user, signOut } = useAuth();

  const [isMenuOpen, setIsMenuOpen] = useState<boolean>(false);

  return (
    <nav className='flex justify-between items-center border-b p-2'>
      <div className='font-semibold'>Logistic Company</div>
      <div className='relative inline-block text-left'>
        <div>
          <button
            type='button'
            className='inline-flex w-full justify-center gap-x-1.5 rounded-full bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50'
            id='menu-button'
            aria-expanded='true'
            aria-haspopup='true'
            onClick={() => setIsMenuOpen((prevState) => !prevState)}
          >
            {user?.username}
          </button>
        </div>

        {isMenuOpen && (
          <div
            className='absolute right-0 z-10 mt-2 w-56 origin-top-right divide-y divide-gray-100 rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none'
            role='menu'
            aria-orientation='vertical'
            aria-labelledby='menu-button'
            tabIndex={-1}
          >
            <div className='py-1' role='none'>
              <a
                href='#'
                className='text-gray-700 block px-4 py-2 text-sm'
                role='menuitem'
                id='menu-item-0'
                onClick={signOut}
              >
                Sign out
              </a>
            </div>
          </div>
        )}
      </div>
    </nav>
  );
};
