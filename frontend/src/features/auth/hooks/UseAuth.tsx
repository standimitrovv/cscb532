import { useContext } from 'react';
import { AuthenticationContext } from '../state/AuthenticationProvider';

export const useAuth = () => {
  if (!AuthenticationContext) {
    throw new Error('No Authentication Context nearby');
  }

  return useContext(AuthenticationContext);
};
